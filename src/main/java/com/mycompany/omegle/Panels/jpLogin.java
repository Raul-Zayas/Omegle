package com.mycompany.omegle.Panels;

public class jpLogin extends javax.swing.JPanel {

    public jpLogin() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelIniciarSesion = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldPassword = new javax.swing.JTextField();
        jButtonEntrar = new javax.swing.JButton();
        jLabelRegistrar = new javax.swing.JLabel();
        jButtonRegistrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabelIniciarSesion.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabelIniciarSesion.setText("INICIAR SESION");

        jLabelName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelName.setText("NOMBRE DE USUARIO");

        jLabelPassword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPassword.setText("CONTRASEÑA");

        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });

        jButtonEntrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonEntrar.setText("ENTRAR");

        jLabelRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabelRegistrar.setText("¿NO TIENES UNA CUENTA?");

        jButtonRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonRegistrar.setText("REGISTRATE");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/omegleLogo-150x150.png"))); // NOI18N
        jLabel1.setText("ICONO OMEGLE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(288, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelIniciarSesion)
                        .addGap(101, 101, 101)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelPassword)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelName)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(210, 210, 210))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRegistrar)
                        .addGap(377, 377, 377))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(434, 434, 434))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabelIniciarSesion))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel1)))
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelName)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jTextFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addComponent(jButtonEntrar)
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRegistrar)
                    .addComponent(jButtonRegistrar))
                .addContainerGap(127, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed
        
    }//GEN-LAST:event_jTextFieldNameActionPerformed

    public javax.swing.JButton getBtnEntrar() {
        return jButtonEntrar;
    }

    public javax.swing.JButton getBtnRegistrar() {
        return jButtonRegistrar;
    }

    public javax.swing.JTextField getTxtUsuario() {
        return jTextFieldName;
    }

    public javax.swing.JTextField getTxtPassword() {
        return jTextFieldPassword;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEntrar;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelIniciarSesion;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelRegistrar;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPassword;
    // End of variables declaration//GEN-END:variables
}
