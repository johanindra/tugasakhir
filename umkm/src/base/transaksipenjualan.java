/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author HP
 */
public class transaksipenjualan extends javax.swing.JFrame {
    private boolean tambah = false;
//    this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tampilan/icon.png")));
    String Tanggal;
    private DefaultTableModel model;
    DefaultTableModel model2 = new DefaultTableModel();
    
private int tharga;

private void kalender() {
        Date ys = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

        tanggal1.setText(fm.format(ys));

    }
//public void totalBiaya() {
//        int jumlahBaris = DftTblModel_barang.getRowCount();
//        int totalBiaya = 0;
//        int jumlahBarang, hargaBarang;
//        for (int i = 0; i < jumlahBaris; i++) {
//            jumlahBarang = Integer.parseInt(DftTblModel_barang.getValueAt(i, 3).toString());
//            hargaBarang = Integer.parseInt(DftTblModel_barang.getValueAt(i, 4).toString());
//            totalBiaya = totalBiaya + (jumlahBarang * hargaBarang);
//        }
//        totalbayar.setText(String.valueOf(totalBiaya));
//    }


 private String autonumber() {
        String id = "PJ";
        try {
            String sql = " SELECT * FROM transaksi_penjualan ORDER by id_transaksi DESC LIMIT 0,1";
            if (!tambah) {
                Connection coon = koneksi.getConnection();
                Statement s = coon.createStatement();
                ResultSet r = s.executeQuery(sql);
                if (r.next()) {
                    int NoFaktur = Integer.parseInt(r.getString("id_transaksi").substring(2))+1;
                    if (NoFaktur < 10) {
                        id += "000" + NoFaktur;
                    } else if (NoFaktur < 100 && NoFaktur >= 10) {
                        id += "00" + NoFaktur;
                    } else if (NoFaktur < 1000 && NoFaktur >= 100) {
                        id += "0" + NoFaktur;
                    } else if (NoFaktur >= 10000) {
                        id += "" + NoFaktur;
                    }
                    idtransaksi.setText(id);
                }
                r.close();
                s.close();
                return id;
            } else {
                int NoFaktur = Integer.parseInt(idtransaksi.getText().substring(2))+1;
                if (NoFaktur < 10) {
                    id += "000" + NoFaktur;
                } else if (NoFaktur < 100 && NoFaktur >= 10) {
                    id = "00" + NoFaktur;
                } else if (NoFaktur < 1000 && NoFaktur >= 100) {
                    id = "0" + NoFaktur;
                } else if (NoFaktur >= 10000) {
                    id = "" + NoFaktur;
                }
                return id;
            }
        } catch (Exception e) {
            System.out.println("autonumber eror");
            e.printStackTrace();
        }
        
        return "null";
    }


        
    
 private void showTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga Jual");
        model.addColumn("Stok");
        try {
            String sql = "SELECT id_barang,nama_barang,harga_jual,stock FROM barang";
            Connection coon = koneksi.getConnection();
            Statement s = coon.createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                    String data[] = {r.getString("id_barang"),r.getString("nama_barang"),r.getString("harga_jual"),r.getString("stock")};
                    model.addRow(data);
                }
                DftTblModel_barang.setModel(model);
               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
            
    }
 
 
//  private void load_tabelkeranjang(){
//
//        DefaultTableModel model1 = new DefaultTableModel();
//        model1.addColumn("Id Transaksi");
//        model1.addColumn("Nama Pembeli");
//        model1.addColumn("Id Barang");
//        model1.addColumn("harga");
//        model1.addColumn("Jumlah");
//        model1.addColumn("Harga Total Belanja");
//        model1.addColumn("Pembayaran");
//        model1.addColumn("kembalian");
//        model1.addColumn("tanggal");
//
//        //menampilkan data database kedalam tabel
//        try {
//            String sql = "SELECT * FROM transaksi_penjualan ";
//            Connection conn = (Connection) koneksi.getConnection();
//            java.sql.Statement st = conn.createStatement();
//            java.sql.ResultSet res = st.executeQuery(sql);
//            while (res.next()) {
//                model1.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3)
//                        , res.getString(4), res.getString(5), res.getString(6)
//                        , res.getString(7), res.getString(8), res.getString(9)});
//            }
//            this.tabelhasil.setModel(model1);
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e.getMessage());
//        }
//    }

 
private void reestTabel(){
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("Id Transaksi");
        model1.addColumn("Nama Pembeli");
        model1.addColumn("Id Barang");
        model1.addColumn("Harga");
        model1.addColumn("Jumlah");
        model1.addColumn("Harga Total");
        model1.addColumn("Tanggal");
        this.tabelhasil.setModel(model1);
    }

private void updateTable(){
        
    }

 private void tambahBarang(){
        
        DefaultTableModel model1 = (DefaultTableModel)this.tabelhasil.getModel();
        
        String namapembeli = this.nama_pembeli.getText(),
               idbrg = this.idbarang.getText(),
               harga = this.txthargabeli.getText(),
               jml = this.jumlahbeli1.getText(),
               ttlharga = "" + tharga,
               idTr = this.idtransaksi.getText(),
               tanggal = this.tanggal1.getText();
        
        model1.addRow(new Object[]{
            idTr, namapembeli, idbrg, harga, jml, ttlharga, tanggal
        });
        
        this.tabelhasil.setModel(model1);
        this.totalbayar.setText(""+ totalHarga());
        
    }
 
 
 private void hapusBarang(){
        int row = this.tabelhasil.getSelectedRow();
        DefaultTableModel mhps = (DefaultTableModel) this.tabelhasil.getModel();
        mhps.removeRow(row);
        this.tabelhasil.setModel(mhps);
    }
    
 
 private void doTransaksi(){
//       String namapembeli = this.nama_pembeli.getText(),
//               idbrg = this.idbarang.getText(),
//               harga = this.txthargabeli.getText(),
//               jml = this.jumlahbeli1.getText(),
       String  ttlBeli = totalbayar.getText(),
               idTr = this.idtransaksi.getText(),
               tanggal = this.tanggal1.getText();
               
        try{
            Connection c = koneksi.getConnection();
            PreparedStatement p = c.prepareStatement("INSERT INTO transaksi_penjualan VALUES (?, ?, ?)");
            p.setString(1, idTr);
            p.setString(2, ttlBeli);
            p.setString(3, tanggal);
            
            if(p.executeUpdate() > 0){
                this.detailTr();
                JOptionPane.showMessageDialog(this, "Tr Berhasil");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void detailTr(){
        try{
            
            for(int i = 0; i < this.tabelhasil.getRowCount(); i++){
                Connection c = koneksi.getConnection();
                PreparedStatement p = c.prepareStatement("INSERT INTO detail_tr_jual VALUES (?, ?, ?, ?, ?)");
                p.setString(1, this.tabelhasil.getValueAt(i, 0).toString());
                p.setString(2, this.tabelhasil.getValueAt(i, 1).toString());
                p.setString(3, this.tabelhasil.getValueAt(i, 2).toString());
                p.setString(4, this.tabelhasil.getValueAt(i, 3).toString());
                p.setString(5, this.tabelhasil.getValueAt(i, 4).toString());
                p.executeUpdate();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

 private void clear() {
        nama_pembeli.setText("");
        idbarang.setText("");
        jumlahbeli1.setText("");
        txthargabeli.setText("");
        totalbayar.setText("");        
        cari.setText("");
        kalender();
        autonumber();
        showTable();

        
    }
 
 private void clear2(){
        
        idbarang.setText("");
        jumlahbeli1.setText("");
        txthargabeli.setText("");
        
        }
 private void clear3() {
        nama_pembeli.setText("");
        idbarang.setText("");
        jumlahbeli1.setText("");
        txthargabeli.setText("");
        totalbayar.setText("");
 }
 
 private void clearr(){
    reestTabel();
}
 private int totalHarga() {
        int total = 0;
        for (int i = 0; i < this.tabelhasil.getRowCount(); i++) {
            total += Integer.parseInt(this.tabelhasil.getValueAt(i, 3).toString()) * 
                     Integer.parseInt(this.tabelhasil.getValueAt(i, 4).toString());
        }
        return total;
    }


    
    
    
    public transaksipenjualan() {
        initComponents();
        showTable();
        kalender();
        this.setTitle("Transaksi Jual");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tampilan/icon.png")));
        autonumber();
        this.reestTabel();
//        load_tabelkeranjang();
        clear();
        clearr();
        
        this.tanggal1.setEditable(false);
        this.idtransaksi.setEditable(false);
        this.totalbayar.setEditable(false);
        this.idbarang.setEditable(false);
        this.txthargabeli.setEditable(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        DftTblModel_barang = new javax.swing.JTable();
        nama_pembeli = new javax.swing.JTextField();
        idbarang = new javax.swing.JTextField();
        cari = new javax.swing.JTextField();
        jumlahbeli1 = new javax.swing.JTextField();
        totalbayar = new javax.swing.JTextField();
        txthargabeli = new javax.swing.JTextField();
        idtransaksi = new javax.swing.JTextField();
        REFRESH = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        tambah1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelhasil = new javax.swing.JTable();
        tanggal1 = new javax.swing.JTextField();
        dashboard = new javax.swing.JLabel();
        barang = new javax.swing.JLabel();
        supplier = new javax.swing.JLabel();
        pembelian = new javax.swing.JLabel();
        laporan = new javax.swing.JLabel();
        keluar = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DftTblModel_barang.setModel(new javax.swing.table.DefaultTableModel(
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
        DftTblModel_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DftTblModel_barangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(DftTblModel_barang);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 360, 230));

        nama_pembeli.setBackground(new java.awt.Color(255, 255, 255));
        nama_pembeli.setBorder(null);
        getContentPane().add(nama_pembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 191, 145, 29));

        idbarang.setBackground(new java.awt.Color(255, 255, 255));
        idbarang.setBorder(null);
        idbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idbarangActionPerformed(evt);
            }
        });
        getContentPane().add(idbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 252, 145, 29));

        cari.setBackground(new java.awt.Color(255, 255, 255));
        cari.setBorder(null);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        getContentPane().add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 122, 300, 20));

        jumlahbeli1.setBackground(new java.awt.Color(255, 255, 255));
        jumlahbeli1.setBorder(null);
        jumlahbeli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahbeli1ActionPerformed(evt);
            }
        });
        getContentPane().add(jumlahbeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 191, 145, 29));

        totalbayar.setBackground(new java.awt.Color(255, 255, 255));
        totalbayar.setBorder(null);
        getContentPane().add(totalbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 252, 145, 29));

        txthargabeli.setBackground(new java.awt.Color(255, 255, 255));
        txthargabeli.setBorder(null);
        getContentPane().add(txthargabeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 124, 145, 29));

        idtransaksi.setBackground(new java.awt.Color(255, 255, 255));
        idtransaksi.setAutoscrolls(false);
        idtransaksi.setBorder(null);
        getContentPane().add(idtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 124, 145, 29));

        REFRESH.setContentAreaFilled(false);
        REFRESH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REFRESHActionPerformed(evt);
            }
        });
        getContentPane().add(REFRESH, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 104, 112, 44));

        edit.setContentAreaFilled(false);
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        getContentPane().add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 290, 112, 44));

        hapus.setContentAreaFilled(false);
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 227, 113, 43));

        tambah1.setContentAreaFilled(false);
        tambah1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambah1MouseClicked(evt);
            }
        });
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });
        getContentPane().add(tambah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 164, 112, 44));

        tabelhasil.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelhasil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelhasilMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelhasil);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 930, 110));

        tanggal1.setBackground(new java.awt.Color(120, 145, 186));
        tanggal1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tanggal1.setBorder(null);
        tanggal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tanggal1ActionPerformed(evt);
            }
        });
        getContentPane().add(tanggal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 86, 110, 20));

        dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboardMouseClicked(evt);
            }
        });
        getContentPane().add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 70, 70));

        barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                barangMouseClicked(evt);
            }
        });
        getContentPane().add(barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 0, 70, 70));

        supplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplierMouseClicked(evt);
            }
        });
        getContentPane().add(supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 0, 70, 70));

        pembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pembelianMouseClicked(evt);
            }
        });
        getContentPane().add(pembelian, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 0, 70, 70));

        laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanMouseClicked(evt);
            }
        });
        getContentPane().add(laporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 0, 70, 70));

        keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                keluarMouseClicked(evt);
            }
        });
        getContentPane().add(keluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 0, 70, 70));

        background.setForeground(new java.awt.Color(255, 255, 255));
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tampilan/TRPENJUALAN.png"))); // NOI18N
        background.setText("jLabel1");
        background.setAutoscrolls(true);
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jumlahbeli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahbeli1ActionPerformed
        if(this.DftTblModel_barang.getRowCount() > -1){
            int stok = Integer.parseInt(this.DftTblModel_barang.getValueAt(this.DftTblModel_barang.getSelectedRow(), 3).toString());
            if(Integer.parseInt(this.jumlahbeli1.getText()) > stok){
                JOptionPane.showMessageDialog(this, "Jumlah tidak boleh melebihi stok!");
                return;
            }else if (Integer.parseInt(this.jumlahbeli1.getText()) <= 0){
                JOptionPane.showMessageDialog(this, "Jumlah stok harus lebih dari 0!");
                return;                
            }
        }
        
        int harga = Integer.parseInt(txthargabeli.getText());
int barang = Integer.parseInt(jumlahbeli1.getText());

int jumlah = (harga* barang) + this.totalHarga();

 totalbayar.setText(String.valueOf(jumlah));

    }//GEN-LAST:event_jumlahbeli1ActionPerformed

    private void idbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idbarangActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_idbarangActionPerformed

    
    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed

            
            doTransaksi();
            clearr();
            clear();
            clear2();
            showTable();
//             String namapembeli = this.nama_pembeli.getText(),
//                    idbarang = this.idbarang.getText(),
//                    harga = this.txthargabeli.getText(),
//                    jumlah = this.jumlahbeli1.getText(),
//                    bayar = this.totalbayar.getText(),
//                    bayar1 = this.bayar1.getText(),
//                    kembalian = this.kembalian1.getText(),
//                    tanggal = tanggal1.getText(),
//                    transaksi_penjualan = idtransaksi.getText();
//        try {
//            String sql = ("UPDATE transaksi_penjualan SET nama_pembeli = '"+namapembeli+"', id_barang = '"+idbarang+"',harga = '"
//                    +harga+"', jumlah = '"+jumlah+"', harga_total_belanja = '"+bayar+"', pembayaran = '"+bayar1+"', kembalian = '"
//                    +kembalian+"', tanggal = '"+tanggal+"' WHERE id_transaksi = '"+transaksi_penjualan+"'");
//             Connection conn = koneksi.getConnection();
//            Statement stat = conn.createStatement();
//            stat.executeUpdate(sql);
//            clear();
////            load_tabelkeranjang();
//            JOptionPane.showMessageDialog(this, "data berhasil terupdate");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage()); 
//        }
//   
    }//GEN-LAST:event_editActionPerformed


    private void DftTblModel_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DftTblModel_barangMouseClicked
int baris = DftTblModel_barang.getSelectedRow();
idbarang.setText(DftTblModel_barang.getValueAt(baris, 0).toString());
txthargabeli.setText(DftTblModel_barang.getValueAt(baris, 2).toString());
    }//GEN-LAST:event_DftTblModel_barangMouseClicked

    private void tambah1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambah1MouseClicked

    }//GEN-LAST:event_tambah1MouseClicked


    
    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
        if(this.DftTblModel_barang.getRowCount() > -1){
            int stok = Integer.parseInt(this.DftTblModel_barang.getValueAt(this.DftTblModel_barang.getSelectedRow(), 3).toString());
            if(Integer.parseInt(this.jumlahbeli1.getText()) > stok){
                JOptionPane.showMessageDialog(this, "Jumlah tidak boleh melebihi stok!");
                return;
            }else if (Integer.parseInt(this.jumlahbeli1.getText()) <= 0){
                JOptionPane.showMessageDialog(this, "Jumlah stok harus lebih dari 0!");
                return;                
            }
        }
 if(this.jumlahbeli1.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "isi data terlebih dahulu!");
            return;
        }else{
            int harga = Integer.parseInt(txthargabeli.getText());
            int barang = Integer.parseInt(jumlahbeli1.getText());
            int jml = (harga * barang)+ this.totalHarga();
            tharga = jml;
            }
        
        this.tambahBarang();
        clear2();
//if(this.nama_pembeli.getText().isEmpty()){
//            JOptionPane.showMessageDialog(this, "isi nama pembeli terlebih dahulu!");
//            return;
//}else if(this.jumlahbeli1.getText().isEmpty()){
//            JOptionPane.showMessageDialog(this, "isi jumlah terlebih dahulu!");
//            return;
//        }else{
//            int harga = Integer.parseInt(txthargabeli.getText());
//            int barang = Integer.parseInt(jumlahbeli1.getText());
//
//            int jml = (harga* barang) + this.totalHarga();
//            tharga = jml;
//            }
        
//}else{
//        try {
//            Connection conn = koneksi.getConnection();
//            PreparedStatement stmt = conn.prepareStatement("insert into transaksi_penjualan(id_transaksi, nama_pembeli, id_barang, harga, jumlah, harga_total_belanja, pembayaran, kembalian, tanggal) values(?,?,?,?,?,?,?,?,?)");
//            stmt.setString(1, idtransaksi.getText());
//            stmt.setString(2, nama_pembeli.getText());
//            stmt.setString(3, idbarang.getText());
//            stmt.setString(4, txthargabeli.getText());
//            stmt.setString(5, jumlahbeli1.getText());
//            stmt.setString(6, totalbayar.getText());
//            stmt.setString(7, bayar1.getText());
//            stmt.setString(8, kembalian1.getText());
//            stmt.setString(9, tanggal1.getText());
//            stmt.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
//            clear();
//            load_tabelkeranjang();
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        } 
//}
    }//GEN-LAST:event_tambah1ActionPerformed

    private void REFRESHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REFRESHActionPerformed
        showTable();
//        load_tabelkeranjang();
        clear();
        // TODO add your handling code here:
    }//GEN-LAST:event_REFRESHActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        DefaultTableModel l_barang = new DefaultTableModel();
        l_barang.addColumn("ID BARANG");
        l_barang.addColumn("NAMA BARANG");
        l_barang.addColumn("HARGA JUAL");
        l_barang.addColumn("STOK");
        DftTblModel_barang.setModel(l_barang);
       Connection conn = koneksi.getConnection();
        try {
            String key = cari.getText();
            java.sql.Statement stmt = conn.createStatement();
            String SQL = "select *  from barang WHERE id_barang LIKE '%"+key+"%' "
                    + "OR nama_barang LIKE '%"+key+"%' "
                    + "or harga_jual LIKE '%"+key+"%' or stock LIKE '%"+key+"%' ";
            
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                l_barang.addRow(new Object[]{
                    res.getString("id_barang"),
                    res.getString("nama_barang"),
                    res.getString("harga_jual"),
                    res.getString("stock")
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_cariActionPerformed

    private void tabelhasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelhasilMouseClicked
int baris = tabelhasil.getSelectedRow();

            idtransaksi.setText(tabelhasil.getValueAt(baris, 0).toString());
            nama_pembeli.setText(tabelhasil.getValueAt(baris, 1).toString());
            idbarang.setText(tabelhasil.getValueAt(baris, 2).toString());
            txthargabeli.setText(tabelhasil.getValueAt(baris, 3).toString());
            jumlahbeli1.setText(tabelhasil.getValueAt(baris, 4).toString());
            totalbayar.setText(tabelhasil.getValueAt(baris, 5).toString());
            tanggal1.setText(tabelhasil.getValueAt(baris, 6).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelhasilMouseClicked

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        this.hapusBarang();
        clear3();
        
//        String id = tabelhasil.getValueAt(this.tabelhasil.getSelectedRow(),0).toString();
//        // TODO add your handling code here:
//        try {
//            String sql = ("DELETE FROM transaksi_penjualan WHERE id_transaksi = '"+id+"'");
//        Connection conn = koneksi.getConnection();
//         Statement stat = conn.createStatement();
//         stat.executeUpdate(sql);
////         load_tabelkeranjang();
//            clear();
//             JOptionPane.showMessageDialog(this, "data berhasil di hapus ");
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage());
//        }
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void tanggal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tanggal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggal1ActionPerformed

    private void dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboardMouseClicked
        this.setVisible(false);
        new dashboard1().setVisible(true);
        dispose();
    }//GEN-LAST:event_dashboardMouseClicked

    private void barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barangMouseClicked
        this.setVisible(false);
        new barang1().setVisible(true);
    }//GEN-LAST:event_barangMouseClicked

    private void supplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierMouseClicked
        this.setVisible(false);
        new supplier().setVisible(true);
    }//GEN-LAST:event_supplierMouseClicked

    private void pembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pembelianMouseClicked
        this.setVisible(false);
        new transaksipembelian().setVisible(true);
    }//GEN-LAST:event_pembelianMouseClicked

    private void laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanMouseClicked
        this.setVisible(false);
        new laporan().setVisible(true);
        dispose();
    }//GEN-LAST:event_laporanMouseClicked

    private void keluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keluarMouseClicked
        this.setVisible(false);
        new login1().setVisible(true);
    }//GEN-LAST:event_keluarMouseClicked

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
            java.util.logging.Logger.getLogger(transaksipenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksipenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksipenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksipenjualan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksipenjualan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DftTblModel_barang;
    private javax.swing.JButton REFRESH;
    private javax.swing.JLabel background;
    private javax.swing.JLabel barang;
    private javax.swing.JTextField cari;
    private javax.swing.JLabel dashboard;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField idbarang;
    private javax.swing.JTextField idtransaksi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jumlahbeli1;
    private javax.swing.JLabel keluar;
    private javax.swing.JLabel laporan;
    private javax.swing.JTextField nama_pembeli;
    private javax.swing.JLabel pembelian;
    private javax.swing.JLabel supplier;
    private javax.swing.JTable tabelhasil;
    private javax.swing.JButton tambah1;
    private javax.swing.JTextField tanggal1;
    private javax.swing.JTextField totalbayar;
    private javax.swing.JTextField txthargabeli;
    // End of variables declaration//GEN-END:variables
}
