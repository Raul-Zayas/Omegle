package com.mycompany.omegle.Client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class RESTClient {

    private final String baseUrl;
    private final CloseableHttpClient httpClient;
    private final Gson gson;
    private String jwtToken;

    public RESTClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.createDefault();
        this.gson = new Gson();
    }

    //Registra un nuevo usuario
    public boolean register(String username, String email, String password, String fullName, String urlImg) {
        try {
            // Codificar parámetros para evitar caracteres ilegales en URL
            String url = baseUrl + "/api/users?username=" + URLEncoder.encode(username, StandardCharsets.UTF_8)
                    + "&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8)
                    + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8)
                    + "&fullName=" + URLEncoder.encode(fullName, StandardCharsets.UTF_8)
                    + "&urlImg=" + URLEncoder.encode(urlImg, StandardCharsets.UTF_8);

            HttpPost post = new HttpPost(url);
            CloseableHttpResponse response = httpClient.execute(post);
            String responseBody = EntityUtils.toString(response.getEntity());

            JsonObject json = gson.fromJson(responseBody, JsonObject.class);
            System.out.println(json);
            System.out.println(json.get("status").getAsString().equals("ok"));
            return json.get("status").getAsString().equals("ok");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Carga la imagen al servidor
    public boolean uploadProfileImage(String username, String imagePath) throws IOException, ParseException {
        String url = baseUrl + "/api/upload-image";
        File imageFile = new File(imagePath);

        // Creacion de la petición multipart
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("username", username);// Parámetro texto
        builder.addBinaryBody("image", imageFile, // Archivo binario
                ContentType.APPLICATION_OCTET_STREAM,
                imageFile.getName());

        // Envía POST al servidor
        HttpPost post = new HttpPost(url);
        post.setEntity(builder.build());

        CloseableHttpResponse response = httpClient.execute(post);
        String responseBody = EntityUtils.toString(response.getEntity());

        // Parsea respuesta JSON
        JsonObject json = gson.fromJson(responseBody, JsonObject.class);
        return json.get("status").getAsString().equals("ok");
    }
    
    // Realiza login y obtiene el token JWT
    public String login(String username, String password) {
        try {
            // Codificar parámetros para evitar caracteres ilegales en URL
            String url = baseUrl + "/api/users?action=login"
                    + "&username=" + URLEncoder.encode(username, StandardCharsets.UTF_8) 
                    + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);
            
            HttpPost post = new HttpPost(url);
            CloseableHttpResponse response = httpClient.execute(post);
            String responseBody = EntityUtils.toString(response.getEntity());
            
            JsonObject json = gson.fromJson(responseBody, JsonObject.class);
            if (json.get("status").getAsString().equals("ok")) {
                this.jwtToken = json.get("token").getAsString();
                return this.jwtToken;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Obtiene la lista de todos los usuarios registrados con su estado de conexión
    public String getAllUsers() {
        try {
            String url = baseUrl + "/api/users?action=list";
            
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(get);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
