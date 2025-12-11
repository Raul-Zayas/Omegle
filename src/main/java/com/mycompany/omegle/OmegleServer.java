/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.omegle;

import com.mycompany.omegle.REST.UserServlet;
import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 *
 * @author PC
 */
public class OmegleServer {

    private static final int HTTP_PORT = 8080;

    public static void main(String[] args) {
        System.out.println("=== Iniciando Servidor =) ====");

        try {
            Tomcat tomcat = new Tomcat();
            tomcat.setPort(HTTP_PORT);
            tomcat.getConnector();
            
            // Crear contexto de la aplicación
            String contextPath = "";
            String docBase = new File(".").getAbsolutePath();
            Context context = tomcat.addContext(contextPath, docBase);
            
            // Registrar UserServlet para registro
            Tomcat.addServlet(context, "UserServlet", new UserServlet());
            context.addServletMappingDecoded("/api/users/*", "UserServlet");
            
            tomcat.start();
            System.out.println("Servidor HTTP iniciado en puerto " + HTTP_PORT);
            
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            System.err.println("Error iniciando servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
