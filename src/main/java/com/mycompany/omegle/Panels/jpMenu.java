package com.mycompany.omegle.Panels;

import javax.swing.JButton;

public class jpMenu extends javax.swing.JPanel {

    public jpMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabelUsuarios = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jTextFieldUsuarioBsqUsuarios = new javax.swing.JTextField();
        jLabelUsuarioBsqUsuarios = new javax.swing.JLabel();
        jButtonAgregarAmigo = new javax.swing.JButton();
        jLabelAmigos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAmigos = new javax.swing.JTable();
        jTextFieldUsuarioBsqAmigos = new javax.swing.JTextField();
        jLabelUsuarioBsqAmigos = new javax.swing.JLabel();
        jButtonBuscarAmigo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableSolicitudes = new javax.swing.JTable();
        jLabelSolicitudes = new javax.swing.JLabel();
        jButtonCerrarSesion = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        setPreferredSize(new java.awt.Dimension(1100, 700));

        jLabelUsuarios.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabelUsuarios.setText("USUARIOS EN OMEGLE");

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IMAGEN", "ID", "USUARIO", "ACCION"
            }
        ));
        jScrollPane2.setViewportView(jTableUsuarios);
        if (jTableUsuarios.getColumnModel().getColumnCount() > 0) {
            jTableUsuarios.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableUsuarios.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jLabelUsuarioBsqUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelUsuarioBsqUsuarios.setText("USUARIO");

        jButtonAgregarAmigo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonAgregarAmigo.setText("BUSCAR");
        jButtonAgregarAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarAmigoActionPerformed(evt);
            }
        });

        jLabelAmigos.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabelAmigos.setText("AMIGOS");

        jTableAmigos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IMAGEN", "ID", "USUARIO", "ESTADO"
            }
        ));
        jScrollPane1.setViewportView(jTableAmigos);
        if (jTableAmigos.getColumnModel().getColumnCount() > 0) {
            jTableAmigos.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableAmigos.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jLabelUsuarioBsqAmigos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelUsuarioBsqAmigos.setText("BUSCAR AMIGO");

        jButtonBuscarAmigo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButtonBuscarAmigo.setText("BUSCAR");

        jTableSolicitudes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "IMAGEN", "ID", "USUARIO", "ACCION"
            }
        ));
        jScrollPane3.setViewportView(jTableSolicitudes);
        if (jTableSolicitudes.getColumnModel().getColumnCount() > 0) {
            jTableSolicitudes.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableSolicitudes.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jLabelSolicitudes.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabelSolicitudes.setText("SOLICITUDES DE AMISTAD");

        jButtonCerrarSesion.setText("CERRAR SESION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabelAmigos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSolicitudes)
                .addGap(129, 129, 129))
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUsuarioBsqAmigos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldUsuarioBsqAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButtonBuscarAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonAgregarAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelUsuarioBsqUsuarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldUsuarioBsqUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelUsuarios)
                        .addGap(144, 144, 144))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                            .addComponent(jScrollPane3))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelSolicitudes))
                    .addComponent(jButtonCerrarSesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUsuarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsuarioBsqAmigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUsuarioBsqAmigos)
                    .addComponent(jTextFieldUsuarioBsqUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUsuarioBsqUsuarios))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonBuscarAmigo)
                    .addComponent(jButtonAgregarAmigo))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarAmigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarAmigoActionPerformed
        
    }//GEN-LAST:event_jButtonAgregarAmigoActionPerformed

    public javax.swing.JTable getTableAmigos() {
        return jTableAmigos;
    }

    public javax.swing.JTable getTableUsuarios() {
        return jTableUsuarios;
    }

    public javax.swing.JButton getBtnBuscarAmigo() {
        return jButtonBuscarAmigo;
    }

    public javax.swing.JButton getBtnAgregarAmigo() {
        return jButtonAgregarAmigo;
    }

    public javax.swing.JTextField getTxtBuscarUsuario() {
        return jTextFieldUsuarioBsqUsuarios;
    }

    public JButton getjButtonCerrarSesion() {
        return jButtonCerrarSesion;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregarAmigo;
    private javax.swing.JButton jButtonBuscarAmigo;
    private javax.swing.JButton jButtonCerrarSesion;
    private javax.swing.JLabel jLabelAmigos;
    private javax.swing.JLabel jLabelSolicitudes;
    private javax.swing.JLabel jLabelUsuarioBsqAmigos;
    private javax.swing.JLabel jLabelUsuarioBsqUsuarios;
    private javax.swing.JLabel jLabelUsuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableAmigos;
    private javax.swing.JTable jTableSolicitudes;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextField jTextFieldUsuarioBsqAmigos;
    private javax.swing.JTextField jTextFieldUsuarioBsqUsuarios;
    // End of variables declaration//GEN-END:variables
}
