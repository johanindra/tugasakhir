/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author HP
 */
public class dashboard1 extends javax.swing.JFrame {

    private boolean isAlive = true;
    
    /**
     * Creates new form dashboard1
     */
    public dashboard1() {
        initComponents();
        JamRealTime();
        tampilGrafik();
        tampilGrafik2();
        pengeluaran();
        pemasukan();
        penjualan();
        this.setTitle("Dashboard");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tampilan/icon.png")));
    }
    
    private void JamRealTime() {
        new Thread() {
            @Override
            public void run() {
                int waktumulai = 0;
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
        System.gc();
    }
    
    
    private double getPendapatan(int bulan){
        try{
            Connection c = (Connection)koneksi.getConnection();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT SUM(total_bayar) FROM transaksi_penjualan"
                    + " WHERE MONTH(tanggal) = " + bulan);
            
            if(r.next()){
               return r.getDouble(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

           
   public void tampilGrafik() {
        
//        double b12 = this.getPendapatan(12);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.setValue(0.10000000, "Contribution Amount", "");
        dataset.setValue(this.getPendapatan(1), "Contribution Amount", "JANUARI");
        dataset.setValue(this.getPendapatan(2), "Contribution Amount", "FEBRUARI");
        dataset.setValue(this.getPendapatan(3), "Contribution Amount", "MARET");
        dataset.setValue(this.getPendapatan(4), "Contribution Amount", "APRIL");
        dataset.setValue(this.getPendapatan(5), "Contribution Amount", "MEI"); 
        dataset.setValue(this.getPendapatan(6), "Contribution Amount", "JUNI");
        dataset.setValue(this.getPendapatan(7), "Contribution Amount", "JULI");
        dataset.setValue(this.getPendapatan(8), "Contribution Amount", "AGUSTUS");
        dataset.setValue(this.getPendapatan(9), "Contribution Amount", "SEPTEMBER");
        dataset.setValue(this.getPendapatan(10), "Contribution Amount", "OKTOBER");
        dataset.setValue(this.getPendapatan(11), "Contribution Amount", "NOVEMBER");
        dataset.setValue(this.getPendapatan(12), "Contribution Amount", "DESEMBER");
        
        JFreeChart barChart = ChartFactory.createBarChart3D("Grafik penjualan", "Bulan", 
                "Tingkat ", dataset, PlotOrientation.HORIZONTAL, false, true, false);
        CategoryPlot p = barChart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.RED);
        
        ChartPanel barPanel = new ChartPanel(barChart);
        panel2.removeAll();
        panel2.add(barPanel,BorderLayout.CENTER);
        panel2.validate();
        
}
   
   private double getPengeluaran(int bulan){
        try{
            Connection c = (Connection)koneksi.getConnection();
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT SUM(total_bayar) FROM transaksi_pembelian"
                    + " WHERE MONTH(tanggal) = " + bulan);
            
            if(r.next()){
               return r.getDouble(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

           
   public void tampilGrafik2() {
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(this.getPengeluaran(1), "Contribution Amount", "JANUARI");
        dataset.setValue(this.getPengeluaran(2), "Contribution Amount", "FEBRUARI");
        dataset.setValue(this.getPengeluaran(3), "Contribution Amount", "MARET");
        dataset.setValue(this.getPengeluaran(4), "Contribution Amount", "APRIL");
        dataset.setValue(this.getPengeluaran(5), "Contribution Amount", "MEI"); 
        dataset.setValue(this.getPengeluaran(6), "Contribution Amount", "JUNI");
        dataset.setValue(this.getPengeluaran(7), "Contribution Amount", "JULI");
        dataset.setValue(this.getPengeluaran(8), "Contribution Amount", "AGUSTUS");
        dataset.setValue(this.getPengeluaran(9), "Contribution Amount", "SEPTEMBER");
        dataset.setValue(this.getPengeluaran(10), "Contribution Amount", "OKTOBER");
        dataset.setValue(this.getPengeluaran(11), "Contribution Amount", "NOVEMBER");
        dataset.setValue(this.getPengeluaran(12), "Contribution Amount", "DESEMBER");
        
        JFreeChart barChart = ChartFactory.createBarChart3D("Grafik pembelian", "Bulan", 
                "Tingkat ", dataset, PlotOrientation.HORIZONTAL, false, true, false);
        CategoryPlot p = barChart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.RED);
        
        ChartPanel barPanel = new ChartPanel(barChart);
        panel1.removeAll();
        panel1.add(barPanel,BorderLayout.CENTER);
        panel1.validate();
        
}
   
   
   private void pengeluaran() {
        try {
            String sql = "SELECT sum(total_bayar) as harga, tanggal as tgl "
                    + "FROM transaksi_pembelian "
                    + "group by tgl "
                    + "having date(tanggal) = DATE(NOW());" ;
            System.out.println(sql);
            Connection conn = (Connection) koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                pengeluaran.setText(rs.getString("harga"));
            } else {
                pengeluaran.setText("0"); }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            }
   }
   
    private void pemasukan() {
        try {
            String sql = "SELECT sum(total_bayar) as harga, tanggal as tgl "
                    + "FROM transaksi_penjualan "
                    + "group by tgl "
                    + "having date(tanggal) = DATE(NOW());" ;
            System.out.println(sql);
            Connection conn = (Connection) koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                pemasukan.setText(rs.getString("harga"));
            } else {
                pemasukan.setText("0"); }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            }
   }
    
    private void penjualan() {
        try {
            String sql = "SELECT sum(jumlah) as pembeli, tanggal as tgl "
                    + "FROM transaksi_penjualan AS t "
                    + "JOIN detail_tr_jual AS d "
                    + "ON t.id_transaksi = d.id_transaksi "
                    + "group by tgl "
                    + "having date(tanggal) = DATE(NOW());" ;
            System.out.println(sql);
            Connection conn = (Connection) koneksi.getConnection();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                this.totalproduk.setText(rs.getString("pembeli"));
            } else {
                this.totalproduk.setText("0"); }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            }
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labeljamreal = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        panel1 = new javax.swing.JPanel();
        panel2 = new javax.swing.JPanel();
        pengeluaran = new javax.swing.JLabel();
        pemasukan = new javax.swing.JLabel();
        totalproduk = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labeljamreal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labeljamreal.setText("JAM");
        getContentPane().add(labeljamreal, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 100, 160, -1));

        jTabbedPane2.setForeground(new java.awt.Color(204, 204, 204));

        panel1.setLayout(new java.awt.BorderLayout());
        jTabbedPane2.addTab("Grafik Pengeluaran", panel1);

        panel2.setLayout(new java.awt.BorderLayout());
        jTabbedPane2.addTab("Grafik Pemasukan", panel2);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 580, 320));

        pengeluaran.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        pengeluaran.setText("Rp. ");
        getContentPane().add(pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 321, 120, 28));

        pemasukan.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        pemasukan.setText("Rp. ");
        getContentPane().add(pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 196, 120, 30));

        totalproduk.setFont(new java.awt.Font("Calibri", 2, 18)); // NOI18N
        getContentPane().add(totalproduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 447, 120, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tampilan/DASHBOARD.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, -1));

        jButton1.setText("barang");
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, 90));

        jButton2.setText("jButton2");
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, 90));

        jButton3.setText("jButton3");
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, 90));

        jButton4.setText("jButton4");
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 90, 90));

        jButton5.setText("jButton5");
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, -1, 90));

        jButton6.setText("jButton6");
        jButton6.setContentAreaFilled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, -1, 90));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        new supplier().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new barang1().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        new transaksipembelian().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        new transaksipenjualan().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        new laporan().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.setVisible(false);
        new login1().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(dashboard1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dashboard1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel labeljamreal;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JLabel pemasukan;
    private javax.swing.JLabel pengeluaran;
    private javax.swing.JLabel totalproduk;
    // End of variables declaration//GEN-END:variables
}
