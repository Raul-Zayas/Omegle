/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omegle.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author PC
 */
public class TCPCliente {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String jwtToken;
    private String username;
    private boolean authenticated = false;

    public TCPCliente(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    // Autentica al cliente al recibir el token
    public boolean authenticate(String token, String username) {
        this.jwtToken = token;
        this.username = username;
        out.println("AUTH:" + token); // Envía comando de autenticación al servidor
        try {
            String response = in.readLine(); // Lee la respuesta del servidor
            if (response != null && response.startsWith("Autenticado:")) { // Verifica si la respuesta confirma autenticación
                this.authenticated = true;
                System.out.println("Autenticado correctamente como: " + username);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; //no se autentic
    }

    // Envia el mensaje al usuario
    public void sendMessage(String toUser, String message) {
        if (!authenticated) {
            System.err.println("Usuario no autenticado -.-");
            return;
        }
        out.println("TO:" + toUser + ":" + message); //envia el mensaje formateado al servidor
    }

    // Retorna el estado de autenticacion del usuario
    public boolean isAuthenticated() {
        return authenticated;
    }

    // Inicia el thread que escucha mensajes del servidor
    public void startListening(MessageListener listener) {
        Thread listenerThread = new Thread(() -> {
            try {
                String line;
                while ((line = in.readLine()) != null) { // Lee continuamente mensajes del servidor
                    listener.onMessageReceived(line); // Notifica al listener sobre el mensaje
                }
            } catch (IOException e) {
                System.err.println("Conexión cerrada: " + e.getMessage());
            }
        });
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    // Interfaz para recibir mensajes del servidor
    public interface MessageListener {
        void onMessageReceived(String message);
    }
}
