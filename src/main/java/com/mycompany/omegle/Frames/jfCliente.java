package com.mycompany.omegle.Frames;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycompany.omegle.Client.RESTClient;
import com.mycompany.omegle.Conexion.dbManager;
import com.mycompany.omegle.Panels.jpChat;
import com.mycompany.omegle.Panels.jpLogin;
import com.mycompany.omegle.Panels.jpMenu;
import com.mycompany.omegle.Panels.jpRegistro;
import com.mycompany.omegle.Renderer.ImgTabla;
import java.awt.CardLayout;
import java.awt.Color;
import static java.awt.EventQueue.invokeLater;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class jfCliente extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(jfCliente.class.getName());

    dbManager db = new dbManager();

    // Instancias de los Paneles
    private jpLogin panelLogin;
    private jpRegistro panelRegistro;
    private jpMenu panelMenu;
    private jpChat panelChat;

    private JTable jTableUsuarios;

    private CardLayout cardLayout;

    // Variables de Lógica
    String clienteip = "";
    String iduser = "";
    String username = "";
    String idfriend = "";
    String friendname = "";

    private String rutaImagen = null;

    private volatile Boolean outchat = true; // Controla si estamos fuera del chat

    //Cliente REST
    private RESTClient restClient;

    private String jwtToken = null;

    public jfCliente() {
        initComponents();
        initCustomLayout();
        initLogic();

        jTableUsuarios = panelMenu.getTableUsuarios();

        // Configurar el renderer de imágenes para la primera columna
        jTableUsuarios.getColumnModel().getColumn(0).setCellRenderer(new ImgTabla());
        // Establecer altura de fila para las imágenes
        jTableUsuarios.setRowHeight(52);
        // Configurar que las celdas no sean editables
        jTableUsuarios.setDefaultEditor(Object.class, null);

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

        // --- EVENTOS DEL MENÚ ---
        panelMenu.getjButtonCerrarSesion().addActionListener(e -> accionCerrarSesion());
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
        // 1. Obtener datos del formulario
        username = panelLogin.getUsuario().getText().trim();
        String password = panelLogin.getPassword().getText();

        // 2. Validar campos no vacíos
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Intentar login con el servidor REST
        try {
            jwtToken = restClient.login(username, password);
            System.out.println("token: " + jwtToken);

            if (jwtToken != null && !jwtToken.isEmpty()) {
                // Login exitoso
                iduser = username;
                ingresoExitoso();        // Cambia a pantalla del menú
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos",
                        "Error de Login", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor x.x \n" + ex.getMessage(),
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionCerrarSesion() {
        // Marcar usuario como offline en la base de datos
        if (username != null && !username.isEmpty()) {
            try {
                db.actualizarEstadoUsuario(username, false);
            } catch (Exception e) {
                System.err.println("Error al actualizar estado: " + e.getMessage());
            }
        }

        // Limpiar variables de sesión
        jwtToken = null;
        username = "";
        iduser = "";

        // Volver al login
        cardLayout.show(jPanelPrincipal, "login");
        JOptionPane.showMessageDialog(this, "Sesión cerrada", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void ingresoExitoso() {
        cardLayout.show(jPanelPrincipal, "menu");
        outchat = true; // No estamos en chat

        // Serán los hilos para actualizar de tablas
        ActualizarTablas();
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

    // =======================================================
    //                      HILOS
    // =======================================================
    private void ActualizarTablas() {
        Thread hilo = new Thread(() -> {
            while (outchat) {
                try {
                    if (panelMenu.isShowing()) { // Solo si el menú está visible
                        MostrarUsuariosOmegle(); // funcion pa mostrar los usuarios en la bd
                    }
                    Thread.sleep(5000); //️ se ejecuta cada 5 segundos
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        hilo.setDaemon(true); //Si el programa finaliza termina el hilo
        hilo.start();
    }

    // Obtener lista de todos los usuarios desde la base de datos con REST
    private void MostrarUsuariosOmegle() {
        try {
            if (restClient != null) {
                String response = restClient.getAllUsers();
                if (response != null) {
                    parseAndUpdateUsuarios(response);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // extrae los datos del json proporcionado por rest
    private void parseAndUpdateUsuarios(String jsonResponse) {
        try {
            JsonObject json = new Gson().fromJson(jsonResponse, JsonObject.class); //convierte el string json a object json
            if (json.get("status").getAsString().equals("ok")) { //Verifica que no haya error
                JsonArray users = json.getAsJsonArray("users"); //extrar un array de usuarios

                invokeLater(() -> { //Encola la actualización de la tabla en el Event Dispatch Thread (EDT).
                    DefaultTableModel model = (DefaultTableModel) panelMenu.getTableUsuarios().getModel();
                    model.setRowCount(0); // Limpiar tabla

                    //ciclo para agregar los usuarios a la tabla (modelo)
                    for (int i = 0; i < users.size(); i++) {

                        JsonObject user = users.get(i).getAsJsonObject();

                        int userId = user.get("userId").getAsInt(); //id del usuario
                        String userName = user.get("username").getAsString(); //usuario del usuarioxd
                        String urlImg = user.has("urlImg") ? user.get("urlImg").getAsString() : ""; //nombre del archivo de la imagen
                        boolean isOnline = user.get("isOnline").getAsBoolean(); //ta conectado?

                        // carga solo los usuarios que no son el que está logeado
                        if (!userName.equals(username)) {
                            ImageIcon scaledIcon = cargarImagenPerfil(user.get("username").getAsString()); //obtiene la imagen
                            String estado = isOnline ? "online" : "offline";
                            model.addRow(new Object[]{scaledIcon, userId, userName, estado}); //agrega el usuario al modelo para ser cargado a la tabla
                        }
                    }
                });
            }
        } catch (Exception e) {
            System.err.println("Error al parsear respuesta de usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // busca la imagen del por el nombre del usuario (de envalde la columna en la bd)
    private ImageIcon cargarImagenPerfil(String username) {
        try {
            URL imgUrl = getClass().getResource("/imagenesPerfil/imgPerfil-" + username + ".jpg");

            // Intentar cargar imagen del usuario
            if (imgUrl != null) {
                ImageIcon icon = new ImageIcon(imgUrl);
                Image raw = icon.getImage();
                if (raw != null && raw.getWidth(null) > 0) { // Verificar que sea válida
                    Image scaled = raw.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaled);
                }
            }

            // Si falla o no hay imagen de usuario, intenta cargar la imagen default
            URL imgUrlDefault = getClass().getResource("/imagenesPerfil/imgPerfil-default.jpg");
            if (imgUrlDefault != null) {
                ImageIcon defaultIcon = new ImageIcon(imgUrlDefault);
                Image rawDefault = defaultIcon.getImage();
                if (rawDefault != null) {
                    Image scaledDefault = rawDefault.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledDefault);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagen de perfil: " + e.getMessage());
            // Retornar placeholder en caso de fallar en ambos casos
            BufferedImage placeholder = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) placeholder.getGraphics();
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, 48, 48);
            g.setColor(java.awt.Color.DARK_GRAY);
            g.drawRect(0, 0, 47, 47);
            g.dispose();
            return new ImageIcon(placeholder);
        }
        return null;
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
