/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Login extends javax.swing.JDialog implements Serializable {

    private Sistema s;

    public Login(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        s.setCurrentUser(null);
        this.tbEmail.setText("josealvaro@gmail.com");
        this.tbPassword.setText("123456");
        EnablebtnLogin();

        //Events
        tbEmail.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnablebtnLogin();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnablebtnLogin();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnablebtnLogin();
            }
        });

        tbPassword.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnablebtnLogin();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnablebtnLogin();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnablebtnLogin();
            }
        });
    }

    public boolean guardar(String ficheiroDestino) {
        boolean retValue = true;

        try {
            FileOutputStream fileOut = new FileOutputStream(ficheiroDestino);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            retValue = false;
            //JOptionPane.showMessageDialog(null, i.getMessage(), NOME, JOptionPane.ERROR_MESSAGE);
        }

        return retValue;
    }

    void EnablebtnLogin() {
        boolean b = true;

        lblLoginDataWarning.setText("");

        //Email
        if (tbEmail.getText().isBlank()) {
            b = false;
            lblEmailWarning.setText("Introduza um Email");
        } else {
            lblEmailWarning.setText("");
        }

        //Password blank
        if (tbPassword.getText().isBlank()) {
            b = false;
            lblPasswordWarning.setText("Introduza uma Password (Mínimo 6 caractéres)");
        } else {
            lblPasswordWarning.setText("");
        }

        //Password len
        if (tbPassword.getText().length() < 6) {
            b = false;
            lblPasswordWarning.setText("Introduza uma Password (Mínimo 6 caractéres)");
        } else {
            lblPasswordWarning.setText("");
        }
        btnLogin.setEnabled(b);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        minimizar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tbEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tbPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblRegisterNewAccount = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        lblPasswordWarning = new javax.swing.JLabel();
        lblLoginDataWarning = new javax.swing.JLabel();
        lblEmailWarning = new javax.swing.JLabel();
        btnSer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 110, 123));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("GESTOR");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DE");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("PROJETOS");

        minimizar.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        minimizar.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/58571163_861930590819182_8668803179535663104_n.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 72, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(20, 20, 20))))
                    .addComponent(minimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(minimizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(159, 159, 159))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(368, 0, -1, 387));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("LOG IN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 11, -1, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Email:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 67, 300, 22));

        tbEmail.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tbEmail.setForeground(new java.awt.Color(102, 102, 102));
        tbEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        tbEmail.setCaretColor(new java.awt.Color(102, 102, 102));
        jPanel1.add(tbEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 89, 300, 40));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Password:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 177, 300, 22));

        tbPassword.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tbPassword.setForeground(new java.awt.Color(102, 102, 102));
        tbPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel1.add(tbPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 199, 300, 40));

        btnLogin.setBackground(new java.awt.Color(51, 110, 123));
        btnLogin.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Log In");
        btnLogin.setBorder(null);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 297, 300, 46));

        lblRegisterNewAccount.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblRegisterNewAccount.setForeground(new java.awt.Color(102, 102, 102));
        lblRegisterNewAccount.setText("Ainda não tem conta? Registe-se aqui!");
        lblRegisterNewAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRegisterNewAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegisterNewAccountMouseClicked(evt);
            }
        });
        jPanel1.add(lblRegisterNewAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 367, -1, 20));

        lbl_email.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        lbl_email.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(lbl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 200, -1, -1));

        lblPasswordWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblPasswordWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblPasswordWarning.setText("lblPasswordWarning");
        jPanel1.add(lblPasswordWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 239, 300, 20));

        lblLoginDataWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblLoginDataWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblLoginDataWarning.setText("lblLoginDadaWarning");
        lblLoginDataWarning.setToolTipText("");
        jPanel1.add(lblLoginDataWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 277, 300, 20));

        lblEmailWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblEmailWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblEmailWarning.setText("lblEmailWarning");
        lblEmailWarning.setToolTipText("");
        jPanel1.add(lblEmailWarning, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 129, 300, 20));

        btnSer.setBackground(new java.awt.Color(51, 110, 123));
        btnSer.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnSer.setForeground(new java.awt.Color(255, 255, 255));
        btnSer.setText("ser");
        btnSer.setBorder(null);
        btnSer.setBorderPainted(false);
        btnSer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSerActionPerformed(evt);
            }
        });
        jPanel1.add(btnSer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        s.setCurrentUser(null);
        for (User user : s.getUsersRepository().getUsers()) {
            if (tbEmail.getText().toUpperCase().equals(user.getEmail().toUpperCase())) {
                if (tbPassword.getText().equals(user.getUserPassword())) {
                    s.setCurrentUser(user);
                    tbEmail.setText("");
                    tbPassword.setText("");
                    this.dispose();
                }
            }
        }
        lblLoginDataWarning.setText("Dados de Login Inválidos");
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lblRegisterNewAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterNewAccountMouseClicked
        //this.setVisible(false); //disposes form DO NOT USE
        SignUpUser signUpUser = new SignUpUser(null, true, s);
        signUpUser.setLocationRelativeTo(null);
        signUpUser.setVisible(true);
        //this.setVisible(true);

    }//GEN-LAST:event_lblRegisterNewAccountMouseClicked

    private void btnSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSerActionPerformed
        guardar("c:\\temp\\s.ser");
    }//GEN-LAST:event_btnSerActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Login2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                Login2 dialog = new Login2(new javax.swing.JFrame(), true,s);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblEmailWarning;
    private javax.swing.JLabel lblLoginDataWarning;
    private javax.swing.JLabel lblPasswordWarning;
    private javax.swing.JLabel lblRegisterNewAccount;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel minimizar;
    private javax.swing.JTextField tbEmail;
    private javax.swing.JPasswordField tbPassword;
    // End of variables declaration//GEN-END:variables
}
