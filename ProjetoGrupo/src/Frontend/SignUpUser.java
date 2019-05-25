/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import Backend.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SignUpUser extends javax.swing.JDialog {

    Sistema s;

    public SignUpUser(java.awt.Frame parent, boolean modal, Sistema s) {
        super(parent, modal);
        initComponents();
        this.s = s;
        this.setTitle("Registar Utilizador");

        
        tbUserName.setText("Antonio Leite");
        tbEmail.setText("antleite2001@gmail.com");
        tbPassword.setText("123456");
        tbConfirmPassword.setText("123456");
        
        EnableBtnRegisterUser();

        tbUserName.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

        });

        tbEmail.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

        });

        tbPassword.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

        });

        tbConfirmPassword.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

            @Override
            public void insertUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

            @Override
            public void removeUpdate(DocumentEvent arg0) {
                EnableBtnRegisterUser();
            }

        });

    }

    void EnableBtnRegisterUser() {
        boolean b = true;

        //Check instance od Sistems s
        if (s == null) {
            JOptionPane.showMessageDialog(null, "A variável s não foi instanciada.\nImpossível continuar");
            b = false;
        }

        //Check username cannot be empty
        if (tbUserName.getText().isBlank()) {
            lblInsertNameWarning.setText("Introduza o seu nome");
            b = false;
        } else {
            lblInsertNameWarning.setText("");
        }

        //Check Email is valid 
        if (Validacoes.validaEmail(tbEmail.getText())) {
            if (s.getUsersRepository().UserExists(tbEmail.getText())) {
                lblInsertEmailWarning.setText("Este Email já está registado");
                b = false;
            } else {
                lblInsertEmailWarning.setText("");
            }
        } else {
            lblInsertEmailWarning.setText("Introduza o seu Email");
            b = false;
        }

        //Check Email exists 
        //Check Password blank-------------------------------------------------------------------------------------
        if (tbPassword.getText().isBlank() || tbPassword.getText().length() < 6) {
            lblInsertPasswordWarning.setText("Introduza uma Password (6 caractéres mínimo)");
            lblInsertPasswordConfirmationWarning.setText("Confirme a Password");
            b = false;
        } else if (tbPassword.getText().length() >= 6 && !tbConfirmPassword.getText().equals(tbPassword.getText())) {
            lblInsertPasswordWarning.setText("");
            lblInsertPasswordConfirmationWarning.setText("Confirme a Password");
            b = false;
        } else {
            lblInsertPasswordWarning.setText("");
            lblInsertPasswordConfirmationWarning.setText("");
        }

        btnRegisterUser.setEnabled(b);

    }//EnableBtnRegisterUser

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tbUserName = new javax.swing.JTextField();
        tbEmail = new javax.swing.JTextField();
        btnRegisterUser = new javax.swing.JButton();
        tbPassword = new javax.swing.JPasswordField();
        tbConfirmPassword = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblInsertEmailWarning = new javax.swing.JLabel();
        lblInsertPasswordWarning = new javax.swing.JLabel();
        lblInsertPasswordConfirmationWarning = new javax.swing.JLabel();
        lblInsertNameWarning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registo");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("REGISTO");

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Nome:");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Email:");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Password:");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Confirmar Password:");

        tbUserName.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tbUserName.setForeground(new java.awt.Color(102, 102, 102));
        tbUserName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbUserNameActionPerformed(evt);
            }
        });

        tbEmail.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tbEmail.setForeground(new java.awt.Color(102, 102, 102));
        tbEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbEmailActionPerformed(evt);
            }
        });

        btnRegisterUser.setBackground(new java.awt.Color(51, 110, 123));
        btnRegisterUser.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnRegisterUser.setForeground(new java.awt.Color(255, 255, 255));
        btnRegisterUser.setText("Registar");
        btnRegisterUser.setBorderPainted(false);
        btnRegisterUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterUserActionPerformed(evt);
            }
        });

        tbPassword.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tbPassword.setForeground(new java.awt.Color(102, 102, 102));
        tbPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbPasswordActionPerformed(evt);
            }
        });

        tbConfirmPassword.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        tbConfirmPassword.setForeground(new java.awt.Color(102, 102, 102));
        tbConfirmPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbConfirmPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbConfirmPasswordActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 110, 123));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/58571163_861930590819182_8668803179535663104_n.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("REGISTO");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(5, 5, 5)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel6)
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblInsertEmailWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblInsertEmailWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblInsertEmailWarning.setText("lblInsertEmailWarning");

        lblInsertPasswordWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblInsertPasswordWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblInsertPasswordWarning.setText("lblInsertPasswordWarning");

        lblInsertPasswordConfirmationWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblInsertPasswordConfirmationWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblInsertPasswordConfirmationWarning.setText("lblInsertPasswordConfirmationWarning");

        lblInsertNameWarning.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblInsertNameWarning.setForeground(new java.awt.Color(255, 0, 0));
        lblInsertNameWarning.setText("lblInsertNameWarning");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tbUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInsertPasswordConfirmationWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInsertPasswordWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInsertEmailWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblInsertNameWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnRegisterUser, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tbConfirmPassword, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(47, 47, 47)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tbUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblInsertNameWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(tbEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblInsertEmailWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tbPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblInsertPasswordWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tbConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblInsertPasswordConfirmationWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegisterUser)
                .addGap(0, 13, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbUserNameActionPerformed
        EnableBtnRegisterUser();
    }//GEN-LAST:event_tbUserNameActionPerformed

    private void btnRegisterUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterUserActionPerformed
        s.getUsersRepository().addUser(tbUserName.getText(), tbEmail.getText(), tbPassword.getText());
        JOptionPane.showMessageDialog(null, "O utilizador \"" + tbUserName.getText() + "\"\ncom o Email \""
                + tbEmail.getText() + "\" foi registado com sucesso!");
        this.dispose();
    }//GEN-LAST:event_btnRegisterUserActionPerformed

    private void tbEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbEmailActionPerformed
        EnableBtnRegisterUser();
    }//GEN-LAST:event_tbEmailActionPerformed

    private void tbPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbPasswordActionPerformed
        EnableBtnRegisterUser();
    }//GEN-LAST:event_tbPasswordActionPerformed

    private void tbConfirmPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbConfirmPasswordActionPerformed
        EnableBtnRegisterUser();
    }//GEN-LAST:event_tbConfirmPasswordActionPerformed

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
//            java.util.logging.Logger.getLogger(SignUpUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SignUpUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SignUpUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SignUpUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                /*Registo2 dialog = new Registo2(new javax.swing.JFrame(), true, s);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);*/
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegisterUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblInsertEmailWarning;
    private javax.swing.JLabel lblInsertNameWarning;
    private javax.swing.JLabel lblInsertPasswordConfirmationWarning;
    private javax.swing.JLabel lblInsertPasswordWarning;
    private javax.swing.JPasswordField tbConfirmPassword;
    private javax.swing.JTextField tbEmail;
    private javax.swing.JPasswordField tbPassword;
    private javax.swing.JTextField tbUserName;
    // End of variables declaration//GEN-END:variables
}
