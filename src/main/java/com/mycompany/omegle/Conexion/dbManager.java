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
    
}