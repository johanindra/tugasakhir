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
public class transaksipembelian extends javax.swing.JFrame {
private boolean tambah = false;
    String Tanggal;
   private DefaultTableModel model; 
    DefaultTableModel model2 = new DefaultTableModel();
    
    private int tharga;

private void kalender() {
        Date ys = new Date();
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");

        tanggal1.setText(fm.format(ys));
}
        private String autonumber() {
        String id = "PB";
        try {
            String sql = " SELECT * FROM transaksi_pembelian ORDER by id_transaksi DESC LIMIT 0,1";
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
        model.addColumn("ID SUPPLIER");
        model.addColumn("Nama SUPPLIER");
        model.addColumn("NO TELP");
        model.addColumn("ALAMAT");
//        model.addColumn("NAMA BARANG");
//        model.addColumn("Harga");
        try {
            String sql = "SELECT id_supplier,nama_supplier, no_telp_supplier, alamat_supplier FROM supplier";
            Connection coon = koneksi.getConnection();
            Statement s = coon.createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                    String data[] = {r.getString("id_supplier"),r.getString("nama_supplier"),
                        r.getString("no_telp_supplier"),r.getString("alamat_supplier")};
                    model.addRow(data);
                }
                tabelsupplier.setModel(model);
               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
            
    }
        
        private void tabelbarang(){
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID BARANG");
            model.addColumn("NAMA BARANG");
            model.addColumn("HARGA BELI");
            model.addColumn("JUMLAH");
             try {
            String sql = "SELECT id_barang,nama_barang, harga_beli, stock FROM barang";
            Connection coon = koneksi.getConnection();
            Statement s = coon.createStatement();
            ResultSet r = s.executeQuery(sql);
            while(r.next()){
                    String data[] = {r.getString("id_barang"),r.getString("nama_barang"),
                        r.getString("harga_beli"),r.getString("stock")};
                    model.addRow(data);
                }
                tabelbarang.setModel(model);
               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
        
       
        private void clear() {
        idsupplier.setText("");
        jumlah.setText("");
        namasupplier.setText("");
        namabarang.setText("");
        harga1.setText("");
        totalbayar.setText("");
        cari.setText("");
        kalender();
        autonumber();
        showTable();
        reestTabel();

        
    }
       

        private void clear2(){
//        idsupplier.setText("");
//        namasupplier.setText("");
        idbarang.setText("");
        namabarang.setText("");
        jumlah.setText("");
        harga1.setText("");
        }
        private void clear3(){
        idsupplier.setText("");
        jumlah.setText("");
        namasupplier.setText("");
        namabarang.setText("");
        harga1.setText("");
        totalbayar.setText("");
        }
        
        
    private void reestTabel(){
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("Id Transaksi");
        model1.addColumn("Id Supplier");
        model1.addColumn("Nama Supplier");
        model1.addColumn("Id Barang");
        model1.addColumn("Nama Barang");
        model1.addColumn("Harga Beli");
        model1.addColumn("Jumlah");
        model1.addColumn("Harga Total");
        model1.addColumn("tanggal");
        this.tabelhasil.setModel(model1);
    }
        
    private void updateTable(){
        
    }
    
    private void tambahBarang(){
        
        DefaultTableModel model1 = (DefaultTableModel)this.tabelhasil.getModel();
        
        String idSupplier = this.idsupplier.getText(),
               namaSupplier = this.namasupplier.getText(),
               idbarang = this.idbarang.getText(),
               namaBarang = this.namabarang.getText(),
               harga = this.harga1.getText(),
               jml = this.jumlah.getText(),
               ttlharga = Integer.toString(tharga),
               idTr = this.idtransaksi.getText(),
               tanggal = this.tanggal1.getText();
        
        model1.addRow(new Object[]{
            idTr, idSupplier, namaSupplier, idbarang, namaBarang, harga, jml, ttlharga, tanggal
        });
        
        this.tabelhasil.setModel(model1);
        this.totalbayar.setText(""+ tharga);
        
    }
    

    private void hapusBarang(){
        int row = this.tabelhasil.getSelectedRow();
        DefaultTableModel mhps = (DefaultTableModel) this.tabelhasil.getModel();
        mhps.removeRow(row);
        this.tabelhasil.setModel(mhps);
    }
    
    /**
     * Creates new form transaksipenjulan
     */
    public transaksipembelian() {
        initComponents();
        this.setTitle("Transaksi Beli");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tampilan/icon.png")));
        kalender();
        autonumber();
        showTable();
        tabelbarang();
//        load_tabelkeranjang();
        this.reestTabel();
        clear();
        this.tanggal1.setEditable(false);
        this.idtransaksi.setEditable(false);
        this.totalbayar.setEditable(false);
        this.idsupplier.setEditable(false);
        this.idbarang.setEditable(false);
        this.namasupplier.setEditable(false);
        this.namabarang.setEditable(false);
        this.harga1.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelsupplier = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelhasil = new javax.swing.JTable();
        back = new javax.swing.JButton();
        tanggal1 = new javax.swing.JTextField();
        harga1 = new javax.swing.JTextField();
        idsupplier = new javax.swing.JTextField();
        namasupplier = new javax.swing.JTextField();
        idbarang = new javax.swing.JTextField();
        namabarang = new javax.swing.JTextField();
        refresh = new javax.swing.JButton();
        tambah1 = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        jumlah = new javax.swing.JTextField();
        totalbayar = new javax.swing.JTextField();
        idtransaksi = new javax.swing.JTextField();
        cari = new javax.swing.JTextField();
        cari1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelbarang = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabelsupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelsupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelsupplierMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelsupplier);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 153, 360, 100));

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
        jScrollPane2.setViewportView(tabelhasil);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 940, 110));

        back.setContentAreaFilled(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 80, 70));

        tanggal1.setBackground(new java.awt.Color(120, 145, 186));
        tanggal1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tanggal1.setBorder(null);
        getContentPane().add(tanggal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 110, 20));

        harga1.setBackground(new java.awt.Color(255, 255, 255));
        harga1.setBorder(null);
        getContentPane().add(harga1, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 195, 145, 29));

        idsupplier.setBackground(new java.awt.Color(255, 255, 255));
        idsupplier.setBorder(null);
        getContentPane().add(idsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 195, 145, 29));

        namasupplier.setBackground(new java.awt.Color(255, 255, 255));
        namasupplier.setBorder(null);
        getContentPane().add(namasupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 257, 145, 29));

        idbarang.setBackground(new java.awt.Color(255, 255, 255));
        idbarang.setBorder(null);
        getContentPane().add(idbarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 319, 145, 29));

        namabarang.setBackground(new java.awt.Color(255, 255, 255));
        namabarang.setBorder(null);
        getContentPane().add(namabarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 128, 145, 29));

        refresh.setContentAreaFilled(false);
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        getContentPane().add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 109, 110, 41));

        tambah1.setContentAreaFilled(false);
        tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambah1ActionPerformed(evt);
            }
        });
        getContentPane().add(tambah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 170, 110, 41));

        hapus.setContentAreaFilled(false);
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });
        getContentPane().add(hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 232, 110, 43));

        simpan.setContentAreaFilled(false);
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });
        getContentPane().add(simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(812, 295, 112, 43));

        jumlah.setBackground(new java.awt.Color(255, 255, 255));
        jumlah.setBorder(null);
        jumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahActionPerformed(evt);
            }
        });
        getContentPane().add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 257, 145, 29));

        totalbayar.setBackground(new java.awt.Color(255, 255, 255));
        totalbayar.setBorder(null);
        getContentPane().add(totalbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 319, 145, 29));

        idtransaksi.setBackground(new java.awt.Color(255, 255, 255));
        idtransaksi.setBorder(null);
        getContentPane().add(idtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 128, 145, 29));

        cari.setBackground(new java.awt.Color(255, 255, 255));
        cari.setBorder(null);
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        getContentPane().add(cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 124, 290, 20));

        cari1.setBackground(new java.awt.Color(255, 255, 255));
        cari1.setBorder(null);
        cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari1ActionPerformed(evt);
            }
        });
        getContentPane().add(cari1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 262, 290, 20));

        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 60, 70));

        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 0, 70, 80));

        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 70, 80));

        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(655, 0, 70, 80));

        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 80, 80));

        tabelbarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelbarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelbarangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelbarang);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 360, 110));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tampilan/TRPEMBELIAN.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 958, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
DefaultTableModel l_supplier = new DefaultTableModel();
        l_supplier.addColumn("ID SUPPLIER");
        l_supplier.addColumn("Nama SUPPLIER");
        l_supplier.addColumn("NO TELP");
        l_supplier.addColumn("ALAMAT");
//        l_supplier.addColumn("NAMA BARANG");
//        l_supplier.addColumn("Harga");
        tabelsupplier.setModel(l_supplier);
       Connection conn = koneksi.getConnection();
        try {
            String key = cari.getText();
            java.sql.Statement stmt = conn.createStatement();
            String SQL = "select *  from supplier WHERE id_supplier LIKE '%"+key+"%' "
                    + "OR nama_supplier LIKE '%"+key+"%' or no_telp_supplier LIKE '%"+key+"%'"
                    + " or alamat_supplier LIKE '%"+key+"%'";
            
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                l_supplier.addRow(new Object[]{
                    res.getString("id_supplier"),
                    res.getString("nama_supplier"),
                    res.getString("no_telp_supplier"),
                    res.getString("alamat_supplier"),
//                    res.getString("nama_barang"),
//                    res.getString("harga")
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        

        
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void tabelsupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelsupplierMouseClicked
int baris = tabelsupplier.getSelectedRow();
idsupplier.setText(tabelsupplier.getValueAt(baris, 0).toString());
namasupplier.setText(tabelsupplier.getValueAt(baris, 1).toString());
//namabarang.setText(tabelsupplier.getValueAt(baris, 4).toString());
//harga1.setText(tabelsupplier.getValueAt (baris,5).toString());

        // TODO add your handling code here:
    }//GEN-LAST:event_tabelsupplierMouseClicked

    private void jumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahActionPerformed
//            int harga = Integer.parseInt(harga1.getText());
//            int barang = Integer.parseInt(jumlah.getText());
//            
//            System.out.println("Harga : " + harga);
//            System.out.println("Barang : " + barang);
//
//            int jml = (harga* barang) + this.totalHarga();
//            tharga += jml;
//            System.out.println("Total Harga : " + jml);
            

    int harga = Integer.parseInt(harga1.getText());
    int barang = Integer.parseInt(jumlah.getText());

    int jumlah = (harga* barang) + this.totalHarga();

     totalbayar.setText(String.valueOf(jumlah));

        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahActionPerformed

    private void tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambah1ActionPerformed
//        if(this.jumlah.getText().isEmpty()){
//            JOptionPane.showMessageDialog(this, "isi jumlah terlebih dahulu!");
//            return;
//        }else{
//            int harga = Integer.parseInt(harga1.getText());
//            int barang = Integer.parseInt(jumlah.getText());
//            
//            System.out.println("Harga : " + harga);
//            System.out.println("Barang : " + barang);
//
//            int jml = harga* barang;
//            tharga += jml;
//            System.out.println("Total Harga : " + jml);
//            }
//        int harga = Integer.parseInt(harga1.getText());
//        int barang = Integer.parseInt(jumlah.getText());
//
//        int jumlah = (harga* barang) + this.totalHarga();
//
//        totalbayar.setText(String.valueOf(jumlah));
 if(this.jumlah.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "isi data terlebih dahulu!");
            return;
        }else{
            int harga = Integer.parseInt(harga1.getText());
            int barang = Integer.parseInt(jumlah.getText());
            int jml = (harga * barang)+ this.totalHarga();
            tharga = jml;
            }
        this.tambahBarang();
        clear2();
//        try {
//            Connection conn = koneksi.getConnection();
//            PreparedStatement stmt = conn.prepareStatement("insert into transaksi_pembelian(id_transaksi,id_supplier"
//                    + ", nama_supplier, nama_barang,harga, jumlah, total_bayar, bayar, kembalian, tanggal) "
//                    + "values(?,?,?,?,?,?,?,?,?,?)");
//            stmt.setString(1, idtransaksi.getText());
//            stmt.setString(2, idsupplier.getText());
//            stmt.setString(3, namasupplier.getText());
//            stmt.setString(4, namabarang.getText());
//            stmt.setString(5, harga1.getText());
//            stmt.setString(6, jumlah.getText());
//            stmt.setString(7, totalbayar.getText());
//            stmt.setString(8,bayar.getText());
//            stmt.setString(9,kembalian.getText());
//            stmt.setString(10,tanggal1.getText());
//            stmt.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
//            clear();
//            load_tabelkeranjang();
//            
//        } catch (SQLException e) {
//             JOptionPane.showMessageDialog(null, e.getMessage());
//        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tambah1ActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        clear();
        clear2();
        clear3();
        reestTabel();
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        this.setVisible(false);
        new dashboard1().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_backActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        this.hapusBarang();
        clear3();
//        }
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void tabelhasilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelhasilMouseClicked
        int baris = tabelhasil.getSelectedRow();

            idtransaksi.setText(tabelhasil.getValueAt(baris, 0).toString());
            idsupplier.setText(tabelhasil.getValueAt(baris, 1).toString());
            namasupplier.setText(tabelhasil.getValueAt(baris, 2).toString());
            namabarang.setText(tabelhasil.getValueAt(baris, 3).toString());
            harga1.setText(tabelhasil.getValueAt(baris, 4).toString());
            jumlah.setText(tabelhasil.getValueAt(baris, 5).toString());
            totalbayar.setText(tabelhasil.getValueAt(baris, 6).toString());
            tanggal1.setText(tabelhasil.getValueAt(baris, 7).toString());
            
            
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelhasilMouseClicked

    private int totalHarga() {
        int total = 0;
        for (int i = 0; i < this.tabelhasil.getRowCount(); i++) {
            total += Integer.parseInt(this.tabelhasil.getValueAt(i, 5
            ).toString()) * 
                     Integer.parseInt(this.tabelhasil.getValueAt(i, 6).toString());
        }
        return total;
    }

    
    private void doTransaksi(){
        String 
//idSupplier = this.idsupplier.getText(),
//               namaSupplier = this.namasupplier.getText(),
//               namaBarang = this.namabarang.getText(),
//               harga = this.harga1.getText(),
//               jml = this.jumlah.getText(),
               ttlharga = "" + totalHarga(),
               idTr = this.idtransaksi.getText(),
               tanggal = this.tanggal1.getText();
        try{
            Connection c = koneksi.getConnection();
            PreparedStatement p = c.prepareStatement("INSERT INTO transaksi_pembelian VALUES (?, ?, ?)");
            p.setString(1, idTr);
            p.setInt(2, tharga);
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
                PreparedStatement p = c.prepareStatement("INSERT INTO detail_tr_beli VALUES (?, ?, ?, ?, ?, ?, ?)");
                p.setString(1, this.tabelhasil.getValueAt(i, 0).toString());
                p.setString(2, this.tabelhasil.getValueAt(i, 1).toString());
                p.setString(3, this.tabelhasil.getValueAt(i, 2).toString());
                p.setString(4, this.tabelhasil.getValueAt(i, 3).toString());
                p.setString(5, this.tabelhasil.getValueAt(i, 4).toString());
                p.setString(6, this.tabelhasil.getValueAt(i, 5).toString());
                p.setString(7, this.tabelhasil.getValueAt(i, 6).toString());
                p.executeUpdate();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed

        doTransaksi();
        clear();
        tabelbarang();
//        String idsupplier = this.idsupplier.getText(),
//                    namasupplier = this.namasupplier.getText(),
//                    namabarang = this.namabarang.getText(),
//                    harga = this.harga1.getText(),
//                    jumlah = this.jumlah.getText(),
//                    total_bayar = this.totalbayar.getText(),
//                    bayar = this.bayar.getText(),
//                    kembalian = this.kembalian.getText(),
//                    tanggal = tanggal1.getText(),
//                    transaksi_pembelian = idtransaksi.getText();
//        try {
//            String sql = ("UPDATE transaksi_pembelian SET id_supplier = '"+idsupplier+"', nama_supplier = '"+namasupplier+"',nama_barang = '"
//                    +namabarang+"',harga = '"+harga+"', jumlah = '"+jumlah+"', total_bayar = '"+total_bayar+"', bayar = '"+bayar+"', kembalian = '"
//                    +kembalian+"', tanggal = '"+tanggal+"' WHERE id_transaksi = '"+transaksi_pembelian+"'");
//             Connection conn = koneksi.getConnection();
//            Statement stat = conn.createStatement();
//            stat.executeUpdate(sql);
//            clear();
//            load_tabelkeranjang();
//            JOptionPane.showMessageDialog(this, "data berhasil terupdate");
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage()); 
//        }
        // TODO add your handling code here:
    }//GEN-LAST:event_simpanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        new barang1().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        new supplier().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        new transaksipenjualan().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.setVisible(false);
        new laporan().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.setVisible(false);
        new login1().setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tabelbarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelbarangMouseClicked
int baris = tabelbarang.getSelectedRow();
idbarang.setText(tabelbarang.getValueAt(baris, 0).toString());
namabarang.setText(tabelbarang.getValueAt(baris, 1).toString());
harga1.setText(tabelbarang.getValueAt(baris, 2).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelbarangMouseClicked

    private void cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari1ActionPerformed
        DefaultTableModel l_barang = new DefaultTableModel();
        l_barang.addColumn("ID BARANG");
        l_barang.addColumn("NAMA BARANG");
        l_barang.addColumn("HARGA BELI");
        l_barang.addColumn("STOCK");
//        l_supplier.addColumn("NAMA BARANG");
//        l_supplier.addColumn("Harga");
        tabelbarang.setModel(l_barang);
       Connection conn = koneksi.getConnection();
        try {
            String key = cari1.getText();
            java.sql.Statement stmt = conn.createStatement();
            String SQL = "select *  from barang WHERE id_barang LIKE '%"+key+"%' "
                    + "OR nama_barang LIKE '%"+key+"%' or harga_beli LIKE '%"+key+"%'"
                    + " or stock LIKE '%"+key+"%'";
            
            java.sql.ResultSet res = stmt.executeQuery(SQL);
            while (res.next()) {
                l_barang.addRow(new Object[]{
                    res.getString("id_barang"),
                    res.getString("nama_barang"),
                    res.getString("harga_beli"),
                    res.getString("stock"),
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cari1ActionPerformed

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
            java.util.logging.Logger.getLogger(transaksipembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksipembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksipembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksipembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksipembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextField cari;
    private javax.swing.JTextField cari1;
    private javax.swing.JButton hapus;
    private javax.swing.JTextField harga1;
    private javax.swing.JTextField idbarang;
    private javax.swing.JTextField idsupplier;
    private javax.swing.JTextField idtransaksi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField namabarang;
    private javax.swing.JTextField namasupplier;
    private javax.swing.JButton refresh;
    private javax.swing.JButton simpan;
    private javax.swing.JTable tabelbarang;
    private javax.swing.JTable tabelhasil;
    private javax.swing.JTable tabelsupplier;
    private javax.swing.JButton tambah1;
    private javax.swing.JTextField tanggal1;
    private javax.swing.JTextField totalbayar;
    // End of variables declaration//GEN-END:variables
}
