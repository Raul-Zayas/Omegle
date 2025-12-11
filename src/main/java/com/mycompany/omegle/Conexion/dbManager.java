package com.mycompany.omegle.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbManager {
    
    // Obtiene todos los datos de un usuario por su username
    public static String[] obtenerUsuario(String username) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = conexion.conectar();
            if (conn != null) {
                String consulta = "SELECT user_id, username, email, full_name, url_img, is_online FROM users WHERE username = ?";
                pst = conn.prepareStatement(consulta);
                pst.setString(1, username);
                rs = pst.executeQuery();

                if (rs.next()) {
                    return new String[]{
                        rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("full_name"),
                        rs.getString("url_img"),
                        String.valueOf(rs.getBoolean("is_online"))
                    };
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener usuario: " + e.getMessage());
        } finally {
            conexion.cerrarRecursos(rs, pst, conn);
        }
        return null;
    }
    
    // Verifica credenciales del usuario
    public static boolean verificarUsuario(String username, String password) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = conexion.conectar();
            if (conn != null) {
                String consulta = "SELECT 1 FROM users WHERE username = ? AND password_hash = ?";
                pst = conn.prepareStatement(consulta);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar usuario: " + e.getMessage());
        } finally {
            conexion.cerrarRecursos(rs, pst, conn);
        }
        return false;
    }
    
    public static String registrarUsuario(String username, String password, String email, String fullName, String urlImg) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = conexion.conectar();
            if (conn != null) {
                // Primero verificar si el usuario ya existe
                String consultaVerificar = "SELECT user_id FROM users WHERE username = ? OR email = ?";
                pst = conn.prepareStatement(consultaVerificar);
                pst.setString(1, username);
                pst.setString(2, email);
                rs = pst.executeQuery();

                if (rs.next()) {
                    System.err.println("Usuario o email ya existen");
                    return null;
                }

                // Insertar nuevo usuario
                String consultaInsertar = "INSERT INTO users (username, email, password_hash, url_img, full_name, is_online) " +
                                        "VALUES (?, ?, ?, ?, ?, false)";
                pst = conn.prepareStatement(consultaInsertar, PreparedStatement.RETURN_GENERATED_KEYS);
                pst.setString(1, username);
                pst.setString(2, email);
                pst.setString(3, password);
                pst.setString(4, urlImg != null ? urlImg : "");
                pst.setString(5, fullName);
                
                int filasAfectadas = pst.executeUpdate();
                
                if (filasAfectadas > 0) {
                    rs = pst.getGeneratedKeys();
                    if (rs.next()) {
                        return String.valueOf(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
        } finally {
            conexion.cerrarRecursos(rs, pst, conn);
        }
        return null;
    }
}