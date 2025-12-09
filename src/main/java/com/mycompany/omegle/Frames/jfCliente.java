package com.mycompany.omegle.Frames;

import com.mycompany.omegle.Panels.jpChat;
import com.mycompany.omegle.Panels.jpLogin;
import com.mycompany.omegle.Panels.jpMenu;
import com.mycompany.omegle.Panels.jpRegistro;
import java.awt.CardLayout;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class jfCliente extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(jfCliente.class.getName());

    // Instancias de los Paneles
    private jpLogin panelLogin;
    private jpRegistro panelRegistro;
    private jpMenu panelMenu;
    private jpChat panelChat;
    
    private CardLayout cardLayout;
    
    // Variables de Lógica
    String clienteip = "";
    String iduser = "";
    String username = "";
    String idfriend = "";
    String friendname = "";
    
    Boolean outchat = true; // Controla si estamos fuera del chat
    
    public jfCliente() {
        initComponents();
        initCustomLayout();
        initLogic();
    }
    
    //Configuracion de los paneles y el cardlayout
    private void initCustomLayout() {
        cardLayout = new CardLayout();
        jPanelPrincipal.setLayout(cardLayout);

        // Inicializar paneles
        panelLogin = new jpLogin();
        panelRegistro = new jpRegistro();
        panelMenu = new jpMenu();
        panelChat = new jpChat();

        // Agregar paneles al contenedor principal con un nombre clave
        jPanelPrincipal.add(panelLogin, "login");
        jPanelPrincipal.add(panelRegistro, "registro");
        jPanelPrincipal.add(panelMenu, "menu");
        jPanelPrincipal.add(panelChat, "chat");

        // Mostrar login al inicio
        cardLayout.show(jPanelPrincipal, "login");
    }
    
    private void initLogic() {
        mostrarIPCliente();
        
        // --- EVENTOS DEL LOGIN ---
        panelLogin.getBtnEntrar().addActionListener(e -> accionLogin());
        panelLogin.getBtnRegistrar().addActionListener(e -> cardLayout.show(jPanelPrincipal, "registro")); // Botón registro de usuario

        // --- EVENTOS DEL REGISTRO ---
        panelRegistro.getBtnRegistrar().addActionListener(e -> accionRegistro());
        panelRegistro.getBtnIniciarSesion().addActionListener(e -> cardLayout.show(jPanelPrincipal, "login")); // Botón volver
    }
    
    // =======================================================
    //                 MÉTODOS DE LÓGICA
    // =======================================================
    private void mostrarIPCliente() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            clienteip = localHost.getHostAddress();
            System.out.println("IP del cliente: " + clienteip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private void accionLogin() {
        username = panelLogin.getTxtUsuario().getText();
        String pass = panelLogin.getTxtPassword().getText();
        
        JOptionPane.showMessageDialog(this, "Aun no modificas la logica del ingreso wey");

        /*if (UserExist && !UserConected) {*/
            ingresoExitoso();
        /*} else {
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión.\nVerifique sus credenciales");
        }*/
    }
    
    private void ingresoExitoso() {
        cardLayout.show(jPanelPrincipal, "menu");
        outchat = true; // No estamos en chat

        // Serán los hilos para actualizar de tablas
        // ActualizarTablas();
    }
    
    private void accionRegistro() {
        // Lógica de registro usando panelRegistro.getTxt...
        JOptionPane.showMessageDialog(this, "Aun no modificas la logica del registro wey");
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new jfCliente().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelPrincipal;
    // End of variables declaration//GEN-END:variables
}
