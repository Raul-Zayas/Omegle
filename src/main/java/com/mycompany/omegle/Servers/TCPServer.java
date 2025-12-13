/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omegle.Servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author PC
 */
public class TCPServer {
    private static final int PORT = 5000;
    private static final int MAX_CLIENTS = 10;
    private ServerSocket serverSocket;
    private ExecutorService clientPool;
    
    public TCPServer() throws IOException {
        serverSocket = new ServerSocket(PORT);
        clientPool = Executors.newFixedThreadPool(MAX_CLIENTS);
    }
    
    // Lista de clientes conectados
    private static final List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    // Permite buscar un cliente por username
    public static ClientHandler getClientByUsername(String username) {
        for (ClientHandler ch : clients) {
            if (ch.getUsername() != null && ch.getUsername().equals(username)) {
                return ch;
            }
        }
        return null;
    }

    public void start() {
        System.out.println("Servidor iniciado en el puerto " + PORT);
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket, clients);
                clients.add(handler);
                clientPool.execute(handler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new TCPServer().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}