/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author TOSHIBA DC
 */
public class laporanbarang extends javax.swing.JInternalFrame {

    /**
     * Creates new form murid
     */
    public laporanbarang() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)getUI()).setNorthPane(null);
    
    }
void cetak_semua(){
        try {
            String NamaFile = "src/Laporan/barang1.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/inventory","root","");
            HashMap param = new HashMap();
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    void cetak_satuan(){
         try {
            String NamaFile = "src/Laporan/barang.jasper";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/inventory","root","");
            HashMap param = new HashMap();
            //Mengambil parameter
            param.put("brg",barangCB.getSelectedItem());
                   
            JasperPrint JPrint = JasperFillManager.fillReport(NamaFile, param, koneksi);
            JasperViewer.viewReport(JPrint, false);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Data tidak dapat dicetak!","Cetak Data",JOptionPane.ERROR_MESSAGE);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cetakBT = new javax.swing.JButton();
        semua = new javax.swing.JRadioButton();
        satu = new javax.swing.JRadioButton();
        barangCB = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1080, 680));
        setVerifyInputWhenFocusTarget(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cetakBT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cetakBT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/Print.png"))); // NOI18N
        cetakBT.setText("CETAK");
        cetakBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cetakBTMouseEntered(evt);
            }
        });
        cetakBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakBTActionPerformed(evt);
            }
        });
        jPanel5.add(cetakBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 170, 40));

        semua.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(semua);
        semua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        semua.setText("Cetak Semua Barang");
        semua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semuaActionPerformed(evt);
            }
        });
        jPanel5.add(semua, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 170, 30));

        satu.setBackground(new java.awt.Color(255, 255, 255));
        satu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        satu.setText("Berdasarkan Kategori");
        satu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                satuActionPerformed(evt);
            }
        });
        jPanel5.add(satu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 170, 30));

        barangCB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        barangCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elektronik", "Pakaian", "Pokok" }));
        barangCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barangCBActionPerformed(evt);
            }
        });
        jPanel5.add(barangCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 170, 30));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 410, 272));

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("LAPORAN BARANG");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, 38));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1070, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cetakBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakBTActionPerformed
        // TODO add your handling code here:
         if(satu.isSelected()){
            cetak_satuan();
        } else if(semua.isSelected()){
            cetak_semua();
        } else{
            JOptionPane.showMessageDialog(null, "Pilih kriteria cetak kelas");
        }
         
    }//GEN-LAST:event_cetakBTActionPerformed

    private void cetakBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cetakBTMouseEntered
        // TODO add your handling code here:
        cetakBT.setBackground(new Color(204,145,226));
        cetakBT.setForeground(Color.black);
    }//GEN-LAST:event_cetakBTMouseEntered

    private void semuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_semuaActionPerformed

    private void satuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_satuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_satuActionPerformed

    private void barangCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barangCBActionPerformed
        // TODO add your handling code here:
         if(barangCB.getSelectedItem().equals("Elektronik")){}
        else if(barangCB.getSelectedItem().equals("Pakaian")){}
        else if(barangCB.getSelectedItem().equals("Pokok")){}
        else if(barangCB.getSelectedItem().equals("Lain-lain")){}

    }//GEN-LAST:event_barangCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> barangCB;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cetakBT;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton satu;
    private javax.swing.JRadioButton semua;
    // End of variables declaration//GEN-END:variables
}
