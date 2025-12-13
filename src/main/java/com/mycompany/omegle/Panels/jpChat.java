package com.mycompany.omegle.Panels;

public class jpChat extends javax.swing.JPanel {

    public jpChat() {
        initComponents();
    }

    // Establece el nombre del amigo
    public void setNombreAmigo(String nombre) {
        jLabelNombreAmigo.setText("Chateando con: " + nombre);
    }

    //Establece el nombre del usuario
    public void setUsuarioActual(String username) {
        jLabelBienvenidaUsuario.setText("Bienvenido " + username);
    }

    //Agrega el mensaje a la conversacion
    public void agregarMensaje(String mensaje) {
        jTextAreaConversacion.append(mensaje + "\n");
        jTextAreaConversacion.setCaretPosition(jTextAreaConversacion.getDocument().getLength());
    }

    //Limpia el chat
    public void limpiarChat() {
        jTextAreaConversacion.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelBienvenidaUsuario = new javax.swing.JLabel();
        jTextFieldMensaje = new javax.swing.JTextField();
        jScrollPaneConversacion = new javax.swing.JScrollPane();
        jTextAreaConversacion = new javax.swing.JTextArea();
        jPanelCamaraPropia = new javax.swing.JPanel();
        jPanelCamaraAmigo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonRegresar = new javax.swing.JButton();
        jButtonLlamar = new javax.swing.JButton();
        jLabelNombreAmigo = new javax.swing.JLabel();
        jButtonEnviarMensaje = new javax.swing.JButton();

        jLabelBienvenidaUsuario.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabelBienvenidaUsuario.setText("Bienvenido USUARIO #XX");

        jTextAreaConversacion.setColumns(20);
        jTextAreaConversacion.setRows(5);
        jScrollPaneConversacion.setViewportView(jTextAreaConversacion);

        jPanelCamaraPropia.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelCamaraPropiaLayout = new javax.swing.GroupLayout(jPanelCamaraPropia);
        jPanelCamaraPropia.setLayout(jPanelCamaraPropiaLayout);
        jPanelCamaraPropiaLayout.setHorizontalGroup(
            jPanelCamaraPropiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );
        jPanelCamaraPropiaLayout.setVerticalGroup(
            jPanelCamaraPropiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        jPanelCamaraAmigo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelCamaraAmigoLayout = new javax.swing.GroupLayout(jPanelCamaraAmigo);
        jPanelCamaraAmigo.setLayout(jPanelCamaraAmigoLayout);
        jPanelCamaraAmigoLayout.setHorizontalGroup(
            jPanelCamaraAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 436, Short.MAX_VALUE)
        );
        jPanelCamaraAmigoLayout.setVerticalGroup(
            jPanelCamaraAmigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/omegleLogo-60x60.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonRegresar.setText("REGRESAR");
        jButtonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegresarActionPerformed(evt);
            }
        });

        jButtonLlamar.setText("LLAMAR");

        jLabelNombreAmigo.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabelNombreAmigo.setText("AMIGO #XX");

        jButtonEnviarMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonEnviarMensaje.setText("->");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanelCamaraAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanelCamaraPropia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabelBienvenidaUsuario))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButtonRegresar)
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(jButtonLlamar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldMensaje)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonEnviarMensaje))
                            .addComponent(jScrollPaneConversacion, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNombreAmigo)
                        .addGap(222, 222, 222))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabelNombreAmigo)
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPaneConversacion, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 23, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jButtonRegresar))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButtonLlamar)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanelCamaraAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jPanelCamaraPropia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelBienvenidaUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonEnviarMensaje)))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegresarActionPerformed

    }//GEN-LAST:event_jButtonRegresarActionPerformed

    public javax.swing.JTextArea getAreaConversacion() {
        return jTextAreaConversacion;
    }

    public javax.swing.JTextField getMensaje() {
        return jTextFieldMensaje;
    }

    public javax.swing.JButton getBtnEnviar() {
        return jButtonEnviarMensaje;
    }

    public javax.swing.JButton getBtnRegresar() {
        return jButtonRegresar;
    }

    public javax.swing.JButton getBtnLlamar() {
        return jButtonLlamar;
    }

    public javax.swing.JLabel getLblNombreAmigo() {
        return jLabelNombreAmigo;
    }

    public javax.swing.JLabel getLblBienvenida() {
        return jLabelBienvenidaUsuario;
    }

    public javax.swing.JPanel getPanelCamaraAmigo() {
        return jPanelCamaraAmigo;
    }

    public javax.swing.JPanel getPanelCamaraPropia() {
        return jPanelCamaraPropia;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviarMensaje;
    private javax.swing.JButton jButtonLlamar;
    private javax.swing.JButton jButtonRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBienvenidaUsuario;
    private javax.swing.JLabel jLabelNombreAmigo;
    private javax.swing.JPanel jPanelCamaraAmigo;
    private javax.swing.JPanel jPanelCamaraPropia;
    private javax.swing.JScrollPane jScrollPaneConversacion;
    private javax.swing.JTextArea jTextAreaConversacion;
    private javax.swing.JTextField jTextFieldMensaje;
    // End of variables declaration//GEN-END:variables
}
