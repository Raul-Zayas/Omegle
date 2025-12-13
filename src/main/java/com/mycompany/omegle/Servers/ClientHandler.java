/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omegle.Servers;

import com.mycompany.omegle.Conexion.dbManager;
import com.mycompany.omegle.Security.JwtUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author PC
 */
public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private java.util.List<ClientHandler> clients;
    private PrintWriter out;
    private String username;
    private dbManager db = new dbManager();
    private JwtUtil jwt = new JwtUtil();

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.clientSocket = socket;
        this.clients = clients;
        this.username = null;
    }
    
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter outWriter = new PrintWriter(clientSocket.getOutputStream(), true)) {
            
            this.out = outWriter;
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                
                // Autenticación por token: "AUTH:<token>"
                if (inputLine.startsWith("AUTH:")) {
                    String[] parts = inputLine.split(":", 2);
                    if (parts.length == 2) {
                        String token = parts[1];
                        if (jwt.validateToken(token)) {
                            String userFromToken = jwt.getUsernameFromToken(token);
                            this.username = userFromToken;
                            
                            // Marcar usuario como online
                            db.actualizarEstadoUsuario(username, true);
                            
                            outWriter.println("Autenticado: " + username); //se autenticó
                        } else {
                            outWriter.println("Token inválido");//nop
                        }
                    }
                }
                
                // Mensaje directo: "TO:<username>:<mensaje>"
                else if (inputLine.startsWith("TO:")) {
                    String[] parts = inputLine.split(":", 3);
                    
                    if (parts.length == 3) {
                        String toUser = parts[1];
                        String msg = parts[2];
                        
                        // Buscar destinatario por username
                        ClientHandler destinatario = null;
                        for (ClientHandler ch : clients) {
                            if (ch.getUsername() != null && ch.getUsername().equals(toUser)) {
                                destinatario = ch;
                                break;
                            }
                        }
                        if (destinatario != null) {
                            destinatario.sendMessage("FROM:" + username + ":" + msg); //envia el mensaje al destinatario
                        } else {
                            outWriter.println("Usuario no conectado: " + toUser);//no lo encontró
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Marcar usuario como offline al desconectarse (cierre forzado)
            if (username != null && !username.isEmpty()) {
                try {
                   db.actualizarEstadoUsuario(username, false);
                    System.out.println("Usuario " + username + " marcado como offline");
                } catch (Exception e) {
                    System.err.println("Error al marcar usuario offline: " + e.getMessage());
                }
            }
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clients.remove(this);
        }
    }
    
    public void sendMessage(String msg) {
        if (out != null) {
            out.println(msg);
        }
    }

    public String getUsername() {
        return username;
    }
}
