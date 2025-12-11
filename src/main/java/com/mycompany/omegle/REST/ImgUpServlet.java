package com.mycompany.omegle.REST;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "ImageUploadServlet", urlPatterns = {"/api/upload-image"})
@MultipartConfig(maxFileSize = 5242880) // 5MB max
public class ImgUpServlet extends HttpServlet {

    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            // Obtener el username del multipart
            Part usernamePart = req.getPart("username");
            String username = null;

            if (usernamePart != null) { //si recibe correctamente lee todos los datos como flujo de bytes
                InputStream is = usernamePart.getInputStream();
                username = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }

            if (username == null || username.isEmpty()) { //si no recibe el nombre de usuario correctamente
                JsonObject response = new JsonObject();
                response.addProperty("status", "error");
                response.addProperty("message", "Username requerido");
                out.print(gson.toJson(response));
                out.flush();
                return;
            }

            // Obtener la imagen del multipart
            Part filePart = req.getPart("image");

            if (filePart == null) { //si no hay datos
                JsonObject response = new JsonObject();
                response.addProperty("status", "error");
                response.addProperty("message", "No se recibió ninguna imagen");
                out.print(gson.toJson(response));
                out.flush();
                return;
            }

            // Determinar la ruta donde guardar la imagen (en el directorio del proyecto)
            String projectPath = System.getProperty("user.dir");
            String uploadPath = projectPath + "/src/main/resources/imagenesPerfil/";
            File uploadDir = new File(uploadPath);

            // Nombre del archivo
            String fileName = "imgPerfil-" + username + ".jpg";
            File file = new File(uploadDir, fileName);

            // Guardar el archivo usando streams
            try (InputStream inputStream = filePart.getInputStream(); FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                System.out.println("Imagen guardada en servidor: " + file.getAbsolutePath());
            }

            JsonObject response = new JsonObject();
            response.addProperty("status", "ok");
            response.addProperty("message", "Imagen guardada exitosamente");
            response.addProperty("filename", fileName);
            out.print(gson.toJson(response));

        } catch (Exception e) {
            System.err.println("Error al subir imagen: " + e.getMessage());
            e.printStackTrace();

            JsonObject response = new JsonObject();
            response.addProperty("status", "error");
            response.addProperty("message", "Error al procesar la imagen: " + e.getMessage());
            out.print(gson.toJson(response));
        }
        out.flush();
    }
}
