package base;

import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class detaillaporan extends javax.swing.JFrame {


    private void tampilData() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        tblpengeluaran.setModel(model);

//        try {
//            String sql = "SELECT t.id_transaksi, d.id_supplier, "
//                    + "d.nama_supplier,d.id_barang, d.nama_barang, d.harga, d.jumlah, t.total_bayar, t.tanggal "
//                    + "FROM detail_tr_beli AS d "
//                    + "JOIN transaksi_pembelian AS t "
//                    + "ON t.id_transaksi = d.id_transaksi ";
//            
//            System.out.println(sql);
//            
//            Connection conn = (Connection) koneksi.getConnection();
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            int num = 0;
//            while (rs.next()) {
//                model.addRow(
//                        new Object[]{
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6), 
//                            rs.getString(7), rs.getString(8),rs.getString(9)
//                        }
//                );
//                System.out.println(++num);
//            }
//            
//            conn.close();
//            st.close();
//            rs.close();
//            System.out.println("is break?");
//            tblpengeluaran.setModel(model);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error Pada Tabel 'LAPORAN PENGELUARAN'");
//        }
    }
    
    private void tampilData2() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Nama Pembeli");
        model.addColumn("Id Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        tblpemasukan.setModel(model);

//        try {
//            String sql = "SELECT t.id_transaksi, d.nama_pembeli, d.id_barang, d.harga, d.jumlah, t.total_bayar, t.tanggal "
//                    + "FROM detail_tr_jual AS d "
//                    + "JOIN transaksi_penjualan AS t "
//                    + "ON t.id_transaksi = d.id_transaksi ";
//            
//            System.out.println(sql);
//            
//            Connection conn = (Connection) koneksi.getConnection();
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//
//            int num = 0;
//            while (rs.next()) {
//                model.addRow(
//                        new Object[]{
//                            rs.getString(1), rs.getString(2), rs.getString(3),
//                            rs.getString(4), rs.getString(5), rs.getString(6), 
//                            rs.getString(7)
//                        }
//                );
//                System.out.println(++num);
//            }
//            
//            conn.close();
//            st.close();
//            rs.close();
//            System.out.println("is break?");
//            tblpemasukan.setModel(model);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error Pada Tabel 'LAPORAN PENGELUARAN'");
//        }
    }
    private void pengeluaran() {
         
      
         
        String tampilan1 = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan1);
        String tanggal1 = String.valueOf(fm.format(jDateChooser3.getDate()));
        String tanggal2 = String.valueOf(fm.format(jDateChooser4.getDate()));
        
        try {
            String sql = "select  sum(total_bayar) as total_bayar "
                    + "FROM transaksi_pembelian "
                    + "WHERE tanggal BETWEEN '" + tanggal1 +"' AND '" + tanggal2 + "'";
            Connection conn = (Connection) koneksi.getConnection();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                
                pengeluaran.setText(rs.getString("total_bayar"));
                
            }else {
                pengeluaran.setText("0");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }        
    }
    
    
    private void pemasukan(){
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(jDateChooser1.getDate()));
        String tanggal2 = String.valueOf(fm.format(jDateChooser2.getDate()));
        
        try {
            String sql = "select  sum(total_bayar) as total_bayar "
                    + "FROM transaksi_penjualan "
                    + "WHERE tanggal BETWEEN '" + tanggal1 +"' AND '" + tanggal2 + "'";
            Connection conn = (Connection) koneksi.getConnection();
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                
                pemasukan.setText(rs.getString("total_bayar"));
                
            }else {
                pemasukan.setText("0");
            }

        } catch (SQLException  e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        

    }
    
    private void clear(){
        this.pemasukan.setText("");
        this.pengeluaran.setText("");
    }
    
   


    /**
     * Creates new form laporan
     */
    public detaillaporan() {
        initComponents();
        tampilData();
        tampilData2();
        System.out.println("break");
        this.setTitle("Detail Laporan");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tampilan/icon.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpemasukan = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        pemasukan = new javax.swing.JTextField();
        cetak = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        clear = new javax.swing.JButton();
        cari1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpengeluaran = new javax.swing.JTable();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        pengeluaran = new javax.swing.JTextField();
        cetak1 = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        jlabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        home1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblpemasukan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblpemasukan);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 880, 190));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 82, 190, 40));
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 82, 190, 40));

        pemasukan.setBorder(null);
        jPanel1.add(pemasukan, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 366, 220, 29));

        cetak.setContentAreaFilled(false);
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });
        jPanel1.add(cetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(343, 360, 105, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tampilan/detaailPemasukan.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, -1, -1));

        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 3, 120, 50));

        cari1.setText("jButton1");
        cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari1ActionPerformed(evt);
            }
        });
        jPanel1.add(cari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 100, 50));

        jTabbedPane2.addTab("Laporan Pemasukan", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblpengeluaran.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblpengeluaran);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 880, 190));
        jPanel2.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 82, 190, 40));
        jPanel2.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 82, 190, 40));

        pengeluaran.setBorder(null);
        jPanel2.add(pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 366, 220, 29));

        cetak1.setContentAreaFilled(false);
        cetak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetak1ActionPerformed(evt);
            }
        });
        jPanel2.add(cetak1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, 105, 40));

        cari.setContentAreaFilled(false);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        jPanel2.add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 85, 100, 42));

        jlabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tampilan/detailPengeluaran.png"))); // NOI18N
        jlabel3.setText("jLabel3");
        jPanel2.add(jlabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, -1, -1));

        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 5, 120, 50));

        jTabbedPane2.addTab("Laporan Pengeluaran", jPanel2);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 880, 440));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tampilan/LAPORAN.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        home1.setText("dasboard");
        home1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home1ActionPerformed(evt);
            }
        });
        getContentPane().add(home1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 0, -1, 80));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void home1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home1ActionPerformed
        this.setVisible(false);
        new laporan().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_home1ActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Id Supplier");
        model.addColumn("Nama Supplier");
        model.addColumn("Id Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        tblpengeluaran.setModel(model);
        
        
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(jDateChooser3.getDate()));
        String tanggal2 = String.valueOf(fm.format(jDateChooser4.getDate()));
        
        
        try{ 
        String sql = "SELECT * FROM detail_tr_beli AS d "
                + "join transaksi_pembelian AS t "
                + "ON t.id_transaksi = d.id_transaksi "
                + "WHERE DATE(t.tanggal) BETWEEN '"+tanggal1+"' AND '"+tanggal2+"'" ;
        Connection conn = (Connection) koneksi.getConnection();
        java.sql.Statement st = conn.createStatement();
        java.sql.ResultSet rs = st.executeQuery(sql);
        //System.out.println(sql);
        
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString
                (2), rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6)
                        , rs.getString(7),rs.getString(9),rs.getString(10)});
            }
            tblpengeluaran.setModel(model);
            pengeluaran();
           
           
        
       }catch(SQLException  e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       }
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari1ActionPerformed
         DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Transaksi");
        model.addColumn("Nama Pembeli");
        model.addColumn("Id Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        tblpemasukan.setModel(model);
        
        
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal1 = String.valueOf(fm.format(jDateChooser1.getDate()));
        String tanggal2 = String.valueOf(fm.format(jDateChooser2.getDate()));
        
        
        try{ 
        String sql = "SELECT * FROM detail_tr_jual AS d "
                + "join transaksi_penjualan AS t "
                + "ON t.id_transaksi = d.id_transaksi "
                + "WHERE DATE(t.tanggal) BETWEEN '"+tanggal1+"' AND '"+tanggal2+"'" ;
            System.out.println("Query : " + sql);
        Connection conn = (Connection) koneksi.getConnection();
        java.sql.Statement st = conn.createStatement();
        java.sql.ResultSet rs = st.executeQuery(sql);
        //System.out.println(sql);
        
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString
                (2), rs.getString(3), rs.getString(4)
                        ,rs.getString(5), rs.getString(7)
                        , rs.getString(8)});
            }
            tblpemasukan.setModel(model);
            pemasukan();
           
           
        
       }catch(SQLException  e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       }
        // TODO add your handling code here:
    }//GEN-LAST:event_cari1ActionPerformed

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
            try {
            // set header dan footer
            MessageFormat header = new MessageFormat("Detail Pemasukan");
            MessageFormat footer = new MessageFormat("Halaman {0,number,integer}");
            // cek tabel kosong atau tidak
            if (this.tblpemasukan.getRowCount() > 0) {
                // print tabel
                this.tblpemasukan.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                JOptionPane.showMessageDialog(this, "Tabel berhasil dicetak");
            } else {
                JOptionPane.showMessageDialog(this, "Tidak ada data yang dicetak!");
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Tabel gagal dicetak");
}
    }//GEN-LAST:event_cetakActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        tampilData2();
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_clearActionPerformed

    private void cetak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetak1ActionPerformed
        try {
            // set header dan footer
            MessageFormat header = new MessageFormat("Detail Pengeluaran");
            MessageFormat footer = new MessageFormat("Halaman {0,number,integer}");
            // cek tabel kosong atau tidak
            if (this.tblpengeluaran.getRowCount() > 0) {
                // print tabel
                this.tblpengeluaran.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                JOptionPane.showMessageDialog(this, "Tabel berhasil dicetak");
            } else {
                JOptionPane.showMessageDialog(this, "Tidak ada data yang dicetak!");
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Tabel gagal dicetak");
}
        // TODO add your handling code here:
    }//GEN-LAST:event_cetak1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    tampilData();
    clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked
        tampilData();
        tampilData2();
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MouseClicked

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
            java.util.logging.Logger.getLogger(detaillaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(detaillaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(detaillaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(detaillaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new detaillaporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cari;
    private javax.swing.JButton cari1;
    private javax.swing.JButton cetak;
    private javax.swing.JButton cetak1;
    private javax.swing.JButton clear;
    private javax.swing.JButton home1;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel jlabel3;
    private javax.swing.JTextField pemasukan;
    private javax.swing.JTextField pengeluaran;
    private javax.swing.JTable tblpemasukan;
    private javax.swing.JTable tblpengeluaran;
    // End of variables declaration//GEN-END:variables
}
