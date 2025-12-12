package com.mycompany.omegle.REST;

import com.mycompany.omegle.Conexion.conexion;
import com.mycompany.omegle.Conexion.dbManager;
import com.mycompany.omegle.Security.JwtUtil;
import com.mycompany.omegle.Security.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", urlPatterns = {"/api/users/*"})
public class UserServlet extends HttpServlet {

    dbManager db = new dbManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        
        // Si action=list -> obtener todos los usuarios
        if ("list".equalsIgnoreCase(action)) {
            try (Connection conn = conexion.conectar()) {
                String sql = "SELECT user_id, username, url_img, is_online FROM users ORDER BY user_id";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                StringBuilder json = new StringBuilder("{\"status\":\"ok\",\"users\":[");
                boolean first = true;
                while (rs.next()) {
                    if (!first) {
                        json.append(",");
                    }
                    json.append(String.format("{\"userId\":%d,\"username\":\"%s\",\"urlImg\":\"%s\",\"isOnline\":%b}",
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("url_img") != null ? rs.getString("url_img") : "",
                            rs.getBoolean("is_online")));
                    first = false;
                }
                json.append("]}");
                out.print(json.toString());
            } catch (SQLException e) {
                System.err.println("Error SQL al listar usuarios: " + e.getMessage());
                e.printStackTrace();
                out.print("{\"status\":\"error\",\"message\":\"Error de base de datos\"}");
            }
            out.flush();
            return;
        }
    }

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

        // Si action = login -> intentar autenticación y devolver JWT
        if ("login".equalsIgnoreCase(action)) {
            try (Connection conn = conexion.conectar()) {
                String sql = "SELECT password_hash FROM users WHERE username = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String stored = rs.getString("password_hash");
                    if (stored != null && PasswordUtil.verify(password, stored)) {
                        db.actualizarEstadoUsuario(username, true);
                        String token = JwtUtil.generateToken(username);
                        out.print(String.format("{\"status\":\"ok\",\"token\":\"%s\"}", token));
                    } else {
                        out.print("{\"status\":\"error\",\"message\":\"Credenciales inválidas\"}");
                    }
                } else {
                    out.print("{\"status\":\"error\",\"message\":\"Usuario no encontrado\"}");
                }
            } catch (SQLException e) {
                out.print("{\"status\":\"error\",\"message\":\"Error de base de datos\"}");
            }
            out.flush();
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
