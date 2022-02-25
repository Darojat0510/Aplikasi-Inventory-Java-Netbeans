/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TOSHIBA DC
 */
public class keluar extends javax.swing.JInternalFrame {
     Connection conn;
    Statement st;
    ResultSet rs;
     String tgl;

    /**
     * Creates new form murid
     */
    public keluar() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
    siapIsi(false);
    tgl();
        tombolNormal();
        tampil();
        otomatis();
        xBT.hide();
       
    }
    private void tgl(){
    Date date = new Date();
        tglTF.setDate(date);
}
    
    public String Kober, Nabar, Stok;

    public String Koba() {
        return Kober;
    }

    ;
    public String Namaba() {
        return Nabar;
    }

    ;
    public String stock() {
        return Stok;
    }

    public String Kosupp,Namsupp;

    public String Kodesup() {
        return Kosupp;
    }

    ;
    
    public String Namasup() {
        return Namsupp;
    }
    
    private void bersih(){
        noTF.setText("");
        tglTF.setDate(null);
        kosuppTF.setText("");
         nasuppTF.setText("");
          koberTF.setText("");
           nabarTF.setText("");
        
        jumlahTF.setText("");
        ketCB.setSelectedItem("");
       
    }

    private void siapIsi(boolean a){
        noTF.setEnabled(a);
        tglTF.setEnabled(a);
        kosuppTF.setEnabled(a);
        nasuppTF.setEnabled(a);
        koberTF.setEnabled(a);
          nabarTF.setEnabled(a);
            jumlahTF.setEnabled(a);
              ketCB.setEnabled(a);
       
    }
    
    private void tombolNormal(){
        tambahBT.setEnabled(true);
        simpanBT.setEnabled(false);
         hapusBT.hide();
         editBT.hide();
    }
    
    private void otomatis(){
        try {
            setKoneksi();
            String sql="select right (notransaksi,2)+1 from keluar";
            ResultSet rs=st.executeQuery(sql);
            
           
           
            if(rs.next()){
                rs.last();
                String no=rs.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    noTF.setText("K"+no);}
                }
            else
            {
                noTF.setText("K001"); 
            }
            } catch (Exception e) 
            {
        }
    }
    
   private void simpan(){
        Integer a = Integer.parseInt(stokTF.getText());
        Integer b = Integer.parseInt(jumlahTF.getText());
        Integer c = a-b;
        stokTF.setText(String.valueOf(c));
        
            try{
            setKoneksi();
            String sql="update barang set stok='"+stokTF.getText()+"' where kodebarang='"+koberTF.getText()+"'";
            st.executeUpdate(sql);
           
        } 
        catch(Exception e){
        
        }
       finally{
                
            }
        
        try{
            setKoneksi();
            String sql="insert into keluar values('"+ noTF.getText() +"','" + tgl+"','" + kosuppTF.getText()+"','" + nasuppTF.getText()+"','" +
                    koberTF.getText()+ "','" + nabarTF.getText()+ "','" + jumlahTF.getText()+ "','" + ketCB.getSelectedItem()+"')";
            st.executeUpdate(sql); 
            JOptionPane.showMessageDialog(null,"Simpan data berhasil");
            }
            catch (Exception e) {
        }
        tampil();
    }
    
    private void perbarui(){   
        try{
            setKoneksi();
            String sql="update keluar set tgltransaksi='"+tgl+"',kodesupplier='"+kosuppTF.getText()+"',namasupplier='"+nasuppTF.getText()+"',kodebarang='"+koberTF.getText()+"',kodesupplier='"+kosuppTF.getText()+
                    "',namabarang='"+nabarTF.getText()+"',jumlahkeluar='"+jumlahTF.getText()+"',keterangan='"+ketCB.getSelectedItem()+
                    "' where notransaksi='"+noTF.getText()+"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Edit data berhasil","CV Anugerah Berkah",JOptionPane.INFORMATION_MESSAGE);
        } 
        catch(Exception e){
        }
        tampil();
    }
    
    public void tampil(){
        Object header[]={"notransaksi","tgltransaksi","kodesupplier","namasupplier","kodebarang","namabarang","jumlahkeluar","keterangan"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
        setKoneksi();
        String sql="select*from keluar";
        try {
            ResultSet rs=st.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);
                 String kolom6=rs.getString(6);
                  String kolom7=rs.getString(7);
                   String kolom8=rs.getString(8);
                  
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
    }
    
    private void cekstock(){
        try{
            setKoneksi();
            rs=st.executeQuery("SELECT *from barang where kodebarang='" + koberTF.getText() + "'");
            
            if (rs.next());
            koberTF.setText(rs.getString("kodebarang"));
            
            stokTF.setText(rs.getString("Stok"));
             } catch (Exception e) {
            
             }

        }
    
    private void hapus(){
        cekstock();
        Integer a = Integer.parseInt(stokTF.getText());
        Integer b = Integer.parseInt(jumlahTF.getText());
        Integer c = a + b;
        stokTF.setText(String.valueOf(c));
        try{
            setKoneksi();
            String sql="update barang set stok='"+stokTF.getText()+"' where kodebarang='"+koberTF.getText()+"'";
            st.executeUpdate(sql);
           
        } 
        catch(Exception e){
        
        }
       finally{
         
         }
           
        try{
            String sql="delete from keluar where notransaksi='"+ noTF.getText() +"'";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Hapus data sukses");
            }
            catch (Exception e) {
            }
        tampil();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cariBT1 = new javax.swing.JButton();
        cariBT2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        kosuppTF = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        nasuppTF = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jumlahTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        ketCB = new javax.swing.JComboBox<>();
        koberTF = new javax.swing.JTextField();
        nabarTF = new javax.swing.JTextField();
        cariBT3 = new javax.swing.JButton();
        cariBT4 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        xBT = new javax.swing.JButton();
        cariTF = new javax.swing.JTextField();
        tambahBT = new javax.swing.JButton();
        simpanBT = new javax.swing.JButton();
        hapusBT = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        noTF = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        tglTF = new com.toedter.calendar.JDateChooser();
        stokTF = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        editBT = new javax.swing.JButton();

        cariBT1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cariBT1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        cariBT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariBT1MouseEntered(evt);
            }
        });
        cariBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBT1ActionPerformed(evt);
            }
        });

        cariBT2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cariBT2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        cariBT2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariBT2MouseEntered(evt);
            }
        });
        cariBT2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBT2ActionPerformed(evt);
            }
        });

        setPreferredSize(new java.awt.Dimension(1080, 680));
        setVerifyInputWhenFocusTarget(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Input Data Barang Masuk", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Kode Supplier      :");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, 28));

        kosuppTF.setBorder(null);
        kosuppTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kosuppTFActionPerformed(evt);
            }
        });
        jPanel2.add(kosuppTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 150, 28));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 320, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nama supplier  :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        nasuppTF.setBorder(null);
        nasuppTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nasuppTFActionPerformed(evt);
            }
        });
        jPanel2.add(nasuppTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 200, 30));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 320, 10));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("kode Barang    :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 110, -1));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 320, 10));

        jumlahTF.setBorder(null);
        jumlahTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlahTFActionPerformed(evt);
            }
        });
        jumlahTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jumlahTFKeyReleased(evt);
            }
        });
        jPanel2.add(jumlahTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 210, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nama Barang   :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 110, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 182, 320, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("  Jumlah Keluar  :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 320, 10));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Keterangan     :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, 30));

        ketCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baik", "Rusak" }));
        ketCB.setBorder(null);
        jPanel2.add(ketCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 210, 40));

        koberTF.setBorder(null);
        koberTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                koberTFActionPerformed(evt);
            }
        });
        jPanel2.add(koberTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 150, 30));

        nabarTF.setBorder(null);
        nabarTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nabarTFActionPerformed(evt);
            }
        });
        jPanel2.add(nabarTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 210, 30));

        cariBT3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cariBT3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        cariBT3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariBT3MouseEntered(evt);
            }
        });
        cariBT3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBT3ActionPerformed(evt);
            }
        });
        jPanel2.add(cariBT3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, -1, 30));

        cariBT4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cariBT4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Cari2.png"))); // NOI18N
        cariBT4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cariBT4MouseEntered(evt);
            }
        });
        cariBT4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBT4ActionPerformed(evt);
            }
        });
        jPanel2.add(cariBT4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, 30));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 224, 320, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 360, 290));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "Table Data Barang", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setForeground(new java.awt.Color(51, 153, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 620, 280));

        xBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        xBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Hapus2.png"))); // NOI18N
        xBT.setContentAreaFilled(false);
        xBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xBTMouseEntered(evt);
            }
        });
        xBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xBTActionPerformed(evt);
            }
        });
        jPanel3.add(xBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(589, 40, 40, 50));

        cariTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTFKeyReleased(evt);
            }
        });
        jPanel3.add(cariTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 250, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 650, 390));

        tambahBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tambahBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Tambah.png"))); // NOI18N
        tambahBT.setText("TAMBAH");
        tambahBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tambahBTMouseEntered(evt);
            }
        });
        tambahBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBTActionPerformed(evt);
            }
        });
        jPanel1.add(tambahBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 120, 40));

        simpanBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        simpanBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Simpan.png"))); // NOI18N
        simpanBT.setText("SIMPAN");
        simpanBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                simpanBTMouseEntered(evt);
            }
        });
        simpanBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanBTActionPerformed(evt);
            }
        });
        jPanel1.add(simpanBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, 120, 40));

        hapusBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hapusBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/trash.png"))); // NOI18N
        hapusBT.setText("HAPUS");
        hapusBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hapusBTMouseEntered(evt);
            }
        });
        hapusBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBTActionPerformed(evt);
            }
        });
        jPanel1.add(hapusBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 480, 120, 40));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("No transaksi               :");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 120, 28));

        noTF.setBorder(null);
        noTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noTFActionPerformed(evt);
            }
        });
        jPanel6.add(noTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 200, 28));
        jPanel6.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 320, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("tanggal Transaksi  :");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel6.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 320, -1));

        tglTF.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglTFPropertyChange(evt);
            }
        });
        jPanel6.add(tglTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 190, 30));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 360, 125));
        jPanel1.add(stokTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(447, 412, -1, 0));

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DATA TRANSAKSI BARANG KELUAR");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, 38));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1070, 50));

        editBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        editBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Edit.png"))); // NOI18N
        editBT.setText("EDIT");
        editBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBTActionPerformed(evt);
            }
        });
        jPanel1.add(editBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 480, 120, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cariBT1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBT1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBT1MouseEntered

    private void cariBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBT1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBT1ActionPerformed

    private void noTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noTFActionPerformed

    private void hapusBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBTActionPerformed
        // TODO add your handling code here:
        int pesan=JOptionPane.showConfirmDialog(null, "Yakin data akan dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pesan==JOptionPane.YES_OPTION){
            if(pesan==JOptionPane.YES_OPTION){
                hapus();
                bersih();
                siapIsi(false);
                tombolNormal();
            } else{
                JOptionPane.showMessageDialog(null, "Hapus data gagal");
            }
            tampil();
        }
    }//GEN-LAST:event_hapusBTActionPerformed

    private void hapusBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hapusBTMouseEntered
        // TODO add your handling code here:
        hapusBT.setBackground(new Color(204,145,226));
        hapusBT.setForeground(Color.black);
    }//GEN-LAST:event_hapusBTMouseEntered

    private void simpanBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBTActionPerformed
        // TODO add your handling code here:
        if(kosuppTF.getText().isEmpty() ||nasuppTF.getText().isEmpty()||koberTF.getText().isEmpty()||nabarTF.getText().isEmpty()|jumlahTF.getText().isEmpty()|ketCB.getSelectedItem().equals("")){
             JOptionPane.showMessageDialog(null, "Lengkapi inputan data","CV ",JOptionPane.INFORMATION_MESSAGE);
        } else{
            if(tambahBT.getText().equalsIgnoreCase("batal")){
                 JOptionPane.showMessageDialog(null, "Simpan data gagal, periksa kembali","CV PERDANA",JOptionPane.INFORMATION_MESSAGE);
                    if(tambahBT.getText().equalsIgnoreCase("batal")){
                    simpan();
                } else{
               }
            }
            if(editBT.getText().equalsIgnoreCase("batal")){
                if(editBT.getText().equalsIgnoreCase("batal")){
                    perbarui();
                } else{
                    JOptionPane.showMessageDialog(null, "Edit data gagal, periksa kembali","CV PERDANA",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            bersih();
            siapIsi(false);
            tambahBT.setText("Tambah");
            editBT.setText("Edit");
            tombolNormal();
        }
    }//GEN-LAST:event_simpanBTActionPerformed

    private void simpanBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpanBTMouseEntered
        // TODO add your handling code here:
        simpanBT.setBackground(new Color(204,145,226));
        simpanBT.setForeground(Color.black);
    }//GEN-LAST:event_simpanBTMouseEntered

    private void tambahBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBTActionPerformed
        // TODO add your handling code here:
        if(tambahBT.getText().equalsIgnoreCase("tambah")){
            tambahBT.setText("Batal");
            //bersih();
            siapIsi(true);
            otomatis();
            tgl();
            nasuppTF.requestFocus();
             noTF.setEnabled(false);
            kosuppTF.setEnabled(true);
            tambahBT.setEnabled(true);
            simpanBT.setEnabled(true);
            hapusBT.setEnabled(false);
           

            xBT.setEnabled(true);
        } else{
            tambahBT.setText("Tambah");
            bersih();
            siapIsi(false);
            tombolNormal();

        }
    }//GEN-LAST:event_tambahBTActionPerformed

    private void tambahBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahBTMouseEntered
        // TODO add your handling code here:
        tambahBT.setBackground(new Color(204,145,226));
        tambahBT.setForeground(Color.black);
    }//GEN-LAST:event_tambahBTMouseEntered

    private void xBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xBTActionPerformed
        // TODO add your handling code here:
       xBT.hide();
       tampil();
      
    }//GEN-LAST:event_xBTActionPerformed

    private void xBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xBTMouseEntered
        // TODO add your handling code here:
        xBT.setBackground(new Color(204,145,226));
        xBT.setForeground(Color.black);
    }//GEN-LAST:event_xBTMouseEntered

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int baris = jTable1.getSelectedRow();
        
         noTF.setText(jTable1.getModel().getValueAt(baris, 0).toString());
         
         try{
        String TanggalPeminjaman=jTable1.getValueAt(baris, 1).toString();
        SimpleDateFormat f =new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d=f.parse(TanggalPeminjaman);
        tglTF.setDate(d);
        }catch(Exception e){
            e.printStackTrace();
      }  
         kosuppTF.setText(jTable1.getModel().getValueAt(baris, 2).toString());
        nasuppTF.setText(jTable1.getModel().getValueAt(baris, 3).toString());
        koberTF.setText(jTable1.getModel().getValueAt(baris, 4).toString());
        nabarTF.setText(jTable1.getModel().getValueAt(baris, 5).toString());
        jumlahTF.setText(jTable1.getModel().getValueAt(baris, 6).toString());
        ketCB.setSelectedItem(jTable1.getModel().getValueAt(baris, 7).toString());
        hapusBT.setEnabled(true);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jumlahTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlahTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlahTFActionPerformed

    private void nasuppTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nasuppTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nasuppTFActionPerformed

    private void kosuppTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kosuppTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kosuppTFActionPerformed

    private void koberTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_koberTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_koberTFActionPerformed

    private void nabarTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nabarTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nabarTFActionPerformed

    private void tglTFPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglTFPropertyChange
        // TODO add your handling code here:
        try{
            if(tglTF.getDate()!=null){
                String pattern="yyyy-MM-dd";
                SimpleDateFormat format =new SimpleDateFormat(pattern);
                tgl=String.valueOf(format.format(tglTF.getDate()));
            }
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_tglTFPropertyChange

    private void cariBT2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBT2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBT2MouseEntered

    private void cariBT2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBT2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBT2ActionPerformed

    private void cariBT3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBT3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBT3MouseEntered

    private void cariBT3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBT3ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
        pglbarang fDB = new pglbarang(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
        koberTF.setText(Kober);
         nabarTF.setText(Nabar);
          koberTF.setText(Kober);
           stokTF.setText(Stok);
        
    }//GEN-LAST:event_cariBT3ActionPerformed

    private void cariBT4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cariBT4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBT4MouseEntered

    private void cariBT4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBT4ActionPerformed
        // TODO add your handling code here:
        boolean closable = true;
         pglsupplier fDB = new  pglsupplier(null, closable);
        fDB.fAB = this;
        fDB.setVisible(true);
        fDB.setResizable(true);
        kosuppTF.setText(Kosupp);
         nasuppTF.setText(Namsupp);
          
    }//GEN-LAST:event_cariBT4ActionPerformed

    private void jumlahTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahTFKeyReleased
        // TODO add your handling code here:
        Integer stok = Integer.parseInt(stokTF.getText());
        Integer b = Integer.parseInt(jumlahTF.getText());
        if(b>stok){
             JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
             
             }else{
            
        }
    }//GEN-LAST:event_jumlahTFKeyReleased

    private void cariTFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTFKeyReleased
        // TODO add your handling code here:
          Object header[]={"notransaksi","tgltransaksi","kodesupplier","namasupplier","kodebarang","namabarang","jumlahkeluar","keterangan"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        jTable1.setModel(data);
        setKoneksi();
        String sql="Select * from barang where notransaksi like '%" + cariTF.getText() + "%'" + "or namabarang like '%" + cariTF.getText()+ "%'";
        try {
            ResultSet rs=st.executeQuery(sql);
            while (rs.next())
            {
                String kolom1=rs.getString(1);
                String kolom2=rs.getString(2);
                String kolom3=rs.getString(3);
                String kolom4=rs.getString(4);
                String kolom5=rs.getString(5);

                String kolom6=rs.getString(6);
                String kolom7=rs.getString(7);
                String kolom8=rs.getString(8);
                
                String kolom[]={kolom1,kolom2,kolom3,kolom4,kolom5,kolom6,kolom7,kolom8};
                data.addRow(kolom);
            }
        } catch (Exception e) {
        }
        xBT.show();
    }//GEN-LAST:event_cariTFKeyReleased

    private void editBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBTActionPerformed
        // TODO add your handling code here:
        if(editBT.getText().equalsIgnoreCase("edit")){
            editBT.setText("Batal");
            siapIsi(true);
            noTF.setEnabled(false);
            stokTF.setEnabled(false);
          
          kosuppTF.requestFocus();
            tambahBT.setEnabled(false);
            simpanBT.setEnabled(true);
            hapusBT.setEnabled(false);
            editBT.setEnabled(true);
           
            
        } else{
            editBT.setText("Edit");
            bersih();
            siapIsi(false);
            tombolNormal();
        }
    }//GEN-LAST:event_editBTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cariBT1;
    private javax.swing.JButton cariBT2;
    private javax.swing.JButton cariBT3;
    private javax.swing.JButton cariBT4;
    private javax.swing.JTextField cariTF;
    private javax.swing.JButton editBT;
    private javax.swing.JButton hapusBT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jumlahTF;
    private javax.swing.JComboBox<String> ketCB;
    private javax.swing.JTextField koberTF;
    private javax.swing.JTextField kosuppTF;
    private javax.swing.JTextField nabarTF;
    private javax.swing.JTextField nasuppTF;
    private javax.swing.JTextField noTF;
    private javax.swing.JButton simpanBT;
    private javax.swing.JTextField stokTF;
    private javax.swing.JButton tambahBT;
    private com.toedter.calendar.JDateChooser tglTF;
    private javax.swing.JButton xBT;
    // End of variables declaration//GEN-END:variables


public Connection setKoneksi(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/inventory","root","");
            st=conn.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi Gagal :" +e);
        }
       return conn; 
    }






}