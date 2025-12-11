package com.mycompany.omegle.REST;

import com.mycompany.omegle.Conexion.conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Registrar usuario o login
        String action = req.getParameter("action");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullName = req.getParameter("fullName");
        String urlImg = req.getParameter("urlImg");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        if (username == null) {
            out.print("{\"status\":\"error\",\"message\":\"Username requerido\"}");
            out.flush();
            return;
        }

        // Si action=login -> intentar autenticación y devolver JWT
        if ("login".equalsIgnoreCase(action)) {
            //Logica para el login
            return;
        }

        // Si no es login -> registrar usuario
        if (email == null || password == null || fullName == null) {
            out.print("{\"status\":\"error\",\"message\":\"Datos incompletos\"}");
            out.flush();
            return;
        }
        try (Connection conn = conexion.conectar()) {
            String sql = "INSERT INTO users (username, email, password_hash, full_name, url_img) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, com.mycompany.omegle.Security.PasswordUtil.hash(password));
            ps.setString(4, fullName);
            ps.setString(5, urlImg);
            ps.executeUpdate();
            out.print("{\"status\":\"ok\",\"message\":\"Usuario registrado\"}");
        } catch (SQLException e) {
            // Log detallado del error
            System.err.println("Error SQL al registrar usuario: " + e.getMessage());
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
            out.print("{\"status\":\"error\",\"message\":\"Error al registrar usuario: " + e.getMessage() + "\"}");
        }
        out.flush();
    }
}
