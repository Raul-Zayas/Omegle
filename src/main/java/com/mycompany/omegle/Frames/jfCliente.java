package com.mycompany.omegle.Frames;

import com.mycompany.omegle.Client.RESTClient;
import com.mycompany.omegle.Panels.jpChat;
import com.mycompany.omegle.Panels.jpLogin;
import com.mycompany.omegle.Panels.jpMenu;
import com.mycompany.omegle.Panels.jpRegistro;
import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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

    private String rutaImagen = null;

    Boolean outchat = true; // Controla si estamos fuera del chat

    //Cliente REST
    private RESTClient restClient;

    public jfCliente() {
        initComponents();
        initCustomLayout();
        initLogic();

        //Inicializar cliente REST 
        restClient = new RESTClient("http://localhost:8080");
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
        panelLogin.getBtnEntrar().addActionListener(e -> accionLogin()); //Boton para iniciar sesion
        panelLogin.getBtnRegistrar().addActionListener(e -> cardLayout.show(jPanelPrincipal, "registro")); //Boton para cambiar al jPanel del registro

        // --- EVENTOS DEL REGISTRO ---
        panelRegistro.getBtnRegistrar().addActionListener(e -> accionRegistro()); //Boton para registrar el usuario
        panelRegistro.getBtnIniciarSesion().addActionListener(e -> cardLayout.show(jPanelPrincipal, "login")); // Botón volver al jPanel de inicio de sesion
        panelRegistro.getBtnSubirImg().addActionListener(e -> accionSeleccionarImagen()); //Boton para seleccionar la imagen
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
        String username = panelRegistro.getjTextUserName().getText().trim();
        String fullName = panelRegistro.getjTextFullName().getText().trim();
        String email = panelRegistro.getjTextCorreo().getText().trim();
        String password = new String(panelRegistro.getjPassword().getPassword());
        String confPassword = new String(panelRegistro.getjPasswordConfirm().getPassword());
        String urlImg = "";

        System.out.println("Usuario: " + username);
        System.out.println("Nombre: " + fullName);
        System.out.println("Correo: " + email);
        System.out.println("Contraseña: " + password);
        System.out.println("ConfContr: " + confPassword);

        // Validar campos
        if (username.isEmpty() || fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confPassword)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Enviar imagen de perfil al servidor si se seleccionó una
        if (rutaImagen != null) {
            try {
                System.out.println("Se esta subiendo la imagen ^.^");
                boolean imagenSubida = restClient.uploadProfileImage(username, rutaImagen); // Verifica si se envió la imagen

                if (imagenSubida) {
                    urlImg = "imgPerfil-" + username + ".jpg"; // url automatica para ser guardada sin duplicados
                    System.out.println("Imagen enviada al servidor =) ");
                } else {
                    System.err.println("No se pudo enviar la imagen al servidor u.u");
                }
            } catch (Exception e) {
                System.err.println("Error al enviar la imagen x.x : " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Llamar al servicio REST de registro usando RESTClient
        boolean success = restClient.register(username, email, password, fullName, urlImg);

        if (success) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente\nYa puedes iniciar sesión.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos
            panelRegistro.getjTextUserName().setText("");
            panelRegistro.getjTextFullName().setText("");
            panelRegistro.getjTextCorreo().setText("");
            panelRegistro.getjPassword().setText("");
            panelRegistro.getjPasswordConfirm().setText("");

            // Limpiar imagen seleccionada
            rutaImagen = null;
            panelRegistro.getjLabelImgPerfil().setIcon(null);
            panelRegistro.getjLabelImgPerfil().setText("IMAGEN DE PERFIL");

            // Volver a la pantalla de login
            cardLayout.show(jPanelPrincipal, "login");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Despliega el navegador de archivos para seleccionar una imagen
    private void accionSeleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen de perfil");

        // Filtro para solo permitir imágenes
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            rutaImagen = archivoSeleccionado.getAbsolutePath();

            // Mostrar vista previa de la imagen en el label
            try {
                ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

                panelRegistro.getjLabelImgPerfil().setText(""); // borramos el texto
                panelRegistro.getjLabelImgPerfil().setIcon(iconoEscalado); //establece la imagen como el icono de la etiqueta

                System.out.println("Imagen seleccionada: " + rutaImagen);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "error al cargar", "Error", JOptionPane.ERROR_MESSAGE);
                rutaImagen = null;
            }
        }
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
