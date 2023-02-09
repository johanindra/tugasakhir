/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

//import java.awt.*;
import java.awt.Toolkit;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class login1 extends javax.swing.JFrame {

    private int waktumulai = 0;
    
    private  boolean isAlive = true;

    /**
     * Creates new form login1
     */
    public login1() {
        initComponents();
        this.setTitle("Login");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tampilan/icon.png")));
        this.Login.setOpaque(false);
        this.Login.setContentAreaFilled(false);
        this.Login.setBorderPainted(false);
        JamRealTime();
    }

    private void JamRealTime() {

        new Thread() {
            @Override
            public void run() {
                while (isAlive) {
                    Calendar kalender = new GregorianCalendar();
                    int jam = kalender.get(Calendar.HOUR);
                    int menit = kalender.get(Calendar.MINUTE);
                    int detik = kalender.get(Calendar.SECOND);
                    int AMPM = kalender.get(Calendar.AM_PM);
                    String SiangMalam;
                    if (AMPM == 1) {
                        SiangMalam = "PM";
                    } else {
                        SiangMalam = "AM";
                    }
                    String JamRealTime = jam + ":" + menit + ":" + detik + "" + SiangMalam;
                    labeljamreal.setText("Jam " + JamRealTime);
                }
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labeljamreal = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        buat = new javax.swing.JButton();
        Login = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labeljamreal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labeljamreal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeljamreal.setText("JAM");
        getContentPane().add(labeljamreal, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 220, -1));

        txtUsername.setBackground(new java.awt.Color(252, 253, 255));
        txtUsername.setBorder(null);
        txtUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 243, 303, 30));

        txtPassword.setBackground(new java.awt.Color(252, 253, 255));
        txtPassword.setBorder(null);
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(492, 304, 303, 30));

        buat.setContentAreaFilled(false);
        buat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buatActionPerformed(evt);
            }
        });
        getContentPane().add(buat, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 418, 40, 10));

        Login.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Login.setContentAreaFilled(false);
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        getContentPane().add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(494, 368, 299, 35));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tampilan/Login.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String dataUsername = null;
        String dataPassword = null;
        String sql = "SELECT username, password FROM login WHERE username = '" + username + "'";
        try {
            Connection conn = koneksi.getConnection();
            java.sql.Statement stmt = conn.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                dataUsername = rs.getString("username");
                dataPassword = rs.getString("password");
            }
            System.out.println(dataUsername);
            System.out.println(dataPassword);
            if (username.equals(dataUsername)) {
                if (password.equals(dataPassword)) {
                    JOptionPane.showMessageDialog(rootPane, "Selamat Anda Berhasil Login");
                    new dashboard1().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Password Salah");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Username salah ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_LoginActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        LoginActionPerformed(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void buatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buatActionPerformed
        new register().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_buatActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.isAlive = false;
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Login;
    private javax.swing.JButton buat;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labeljamreal;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
