/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omegle;

import com.mycompany.omegle.Frames.jfCliente;
import javax.swing.UIManager.LookAndFeelInfo;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;

/**
 *
 * @author PC
 */
public class ClienteApp {
    public static void main(String[] args) {
        // Establecer look and feel del sistema
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error al establecer Look and Feel: " + ex.getMessage());
        }
        
        // Crear y mostrar la ventana principal
        java.awt.EventQueue.invokeLater(() -> {
            jfCliente cliente = new jfCliente();
            cliente.setTitle("Omegle - Cliente");
            cliente.setLocationRelativeTo(null);
            cliente.setVisible(true);
        });
        System.out.println("Servidor: http://localhost:8080");
    }
}
