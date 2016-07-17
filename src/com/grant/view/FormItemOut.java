/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grant.view;

import com.grant.data.ItemDAO;
import com.grant.entity.ItemOutDetails;
import com.grant.util.DatePicker;
import com.grant.util.MyInputVerifier;
import com.grant.util.MyNumericVerifier;
import com.grant.util.InputValidator;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.InputVerifier;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.record.PageBreakRecord;

/**
 *
 * @author Isura Amarasinghe
 */
public class FormItemOut extends javax.swing.JFrame {

    private Vector<Vector<String>> data;
    private Vector<String> header;

    /**
     * Creates new form FormItem
     */
    public FormItemOut() {

        header = new Vector<String>();
        header.add("itemName");
        header.add("itemNo");
        header.add("repCode");
        header.add("description");
        header.add("invoiceNo");
        header.add("outwards");
        header.add("type");
        header.add("balance");
        header.add("unitPrice");
        header.add("amount");
        header.add("date");

        initComponents();
        fillSubItems();
        fillInvoceType();
        fillSubRefCode();
        fillItemsTypes();
        // loadTable();

    }

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
        jCBIemCatogory = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFDiscription = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jBDelete = new javax.swing.JButton();
        jBAdd = new javax.swing.JButton();
        jCBItemNo = new javax.swing.JComboBox<>();
        jBInDate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jCBRefCode = new javax.swing.JComboBox<>();
        jLblBalance = new javax.swing.JLabel();
        jCBInvoiceNo = new javax.swing.JComboBox<>();
        jLInvoiceNo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTFInwards = new javax.swing.JTextField();
        jCBOutType = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblStockOut = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLunitPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Item catogary");

        jCBIemCatogory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBIemCatogoryItemStateChanged(evt);
            }
        });
        jCBIemCatogory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBIemCatogoryActionPerformed(evt);
            }
        });

        jLabel2.setText("Item No");

        jLabel3.setText("Discription");

        jBDelete.setText("Delete");
        jBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeleteActionPerformed(evt);
            }
        });

        jBAdd.setText("Release from Inventory");
        jBAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jBDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jCBItemNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCBItemNoItemStateChanged(evt);
            }
        });
        jCBItemNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBItemNoActionPerformed(evt);
            }
        });

        jBInDate.setText("Pick Date");
        jBInDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBInDateActionPerformed(evt);
            }
        });

        jLabel6.setText("Rep Code");

        jCBRefCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBRefCodeActionPerformed(evt);
            }
        });

        jLblBalance.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLblBalance.setText("Balance");

        jCBInvoiceNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBInvoiceNoActionPerformed(evt);
            }
        });

        jLInvoiceNo.setText("Invoice No");

        jLabel5.setText("Outwards");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFInwards, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jCBOutType, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFInwards, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBOutType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTblStockOut.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "itemName ", "itemNo ", "repCode ", "description ", "invoiceNo ", "outwards ", "type ", "balance ", "unitPrice", "amount", "date "
            }
        ));
        jTblStockOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblStockOutMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblStockOut);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Unit Price");

        jLunitPrice.setText("unit price");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblBalance)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBInDate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jCBInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jCBItemNo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCBIemCatogory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTFDiscription, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCBRefCode, 0, 176, Short.MAX_VALUE)
                                    .addComponent(jLunitPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(14, 14, 14)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBInDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBIemCatogory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBItemNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTFDiscription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLunitPrice))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jCBRefCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBInvoiceNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBInvoiceNoActionPerformed
        String invoType = jCBInvoiceNo.getSelectedItem().toString();
        String date = jBInDate.getText();

        if ("sales".equals(invoType) && !"Pick Date".equals(date) && !"".equals(date)) {
            String invoMax = getMaxInvoceNo();
            String invoNewMx = String.format("%03d", (Integer.parseInt(invoMax) + 1));

            //2015-02-04|012|ser
            String invoceNo = date + "|" + invoNewMx + "|sal";
            jLInvoiceNo.setText(invoceNo);
            loadTable(invoceNo);

        } else if ("services".equals(invoType) && !"Pick Date".equals(date) && !"".equals(date)) {

            String invoMax = getMaxInvoceNo();
            String invoNewMx = String.format("%03d", (Integer.parseInt(invoMax) + 1));

            //2015-02-04|012|ser
            String invoceNo = date + "|" + invoNewMx + "|ser";

            jLInvoiceNo.setText(invoceNo);
            loadTable(invoceNo);

        } else if ("".equals(date)) {
            JOptionPane.showMessageDialog(null, "Please select date first");

        }
    }//GEN-LAST:event_jCBInvoiceNoActionPerformed

    private void jCBRefCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBRefCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBRefCodeActionPerformed

    private void jBInDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBInDateActionPerformed
        DatePicker datePicker = new DatePicker(this);
        jBInDate.setText(datePicker.setPickedDate());
    }//GEN-LAST:event_jBInDateActionPerformed

    private void jCBItemNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBItemNoActionPerformed

    }//GEN-LAST:event_jCBItemNoActionPerformed

    private void jCBItemNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBItemNoItemStateChanged
        String itemName = jCBIemCatogory.getSelectedItem().toString();
        String itemNo = (String) jCBItemNo.getSelectedItem();
        //String itemNo = jCBItemNo.getSelectedItem().toString();
        fillSubDescription(itemName, itemNo);
    }//GEN-LAST:event_jCBItemNoItemStateChanged

    private void jBAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAddActionPerformed
        try {
            if (validateFields()) {
                //  boolean ss = jTFInwards.setInputVerifier(new MyInputVerifier());
                String itemName = jCBIemCatogory.getSelectedItem().toString();
                String itemNo = jCBItemNo.getSelectedItem().toString();
                String refCode = jCBRefCode.getSelectedItem().toString();
                String description = jTFDiscription.getText();
                String invoiceNo = jLInvoiceNo.getText();
                String outwards = jTFInwards.getText();
                String iType = jCBOutType.getSelectedItem().toString();
                String balance = jLblBalance.getText();
                String unitPrice = jLunitPrice.getText();
                String amount = null;
                String date = jBInDate.getText();

                if (Double.parseDouble(balance) < Double.parseDouble(outwards)) {
                    JOptionPane.showMessageDialog(null, "Stock Insufficient");
                } else {

                    double amunt = Double.parseDouble(outwards) * Double.parseDouble(unitPrice);
                    amount = String.valueOf(amunt);

                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date parsed = format.parse(date);
                    java.sql.Date itemInDate = new java.sql.Date(parsed.getTime());

                    ItemOutDetails itemOutDetails = new ItemOutDetails(itemName, itemNo, refCode, description, invoiceNo, outwards, iType, balance, unitPrice, amount, itemInDate);

                    ItemDAO itemDAO = new ItemDAO();
                    itemDAO.addItemOut(itemOutDetails);
                    loadTable(invoiceNo);
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(FormItemOut.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jBAddActionPerformed

    private void jBDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeleteActionPerformed
        try {
            String itemName = jCBIemCatogory.getSelectedItem().toString();
            String itemNo = jCBItemNo.getSelectedItem().toString();
            String refCode = jCBRefCode.getSelectedItem().toString();
            String description = jTFDiscription.getText();
            String invoiceNo = jLInvoiceNo.getText();
            String outwards = jTFInwards.getText();
            String iType = jCBOutType.getSelectedItem().toString();
            String balance = jLblBalance.getText();
            String unitPrice = jLunitPrice.getText();
            String amount = null;
            String date = jBInDate.getText();

            double amunt = Double.parseDouble(outwards) * Double.parseDouble(unitPrice);
            amount = String.valueOf(amunt);

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(date);
            java.sql.Date itemInDate = new java.sql.Date(parsed.getTime());

            ItemOutDetails itemOutDetails = new ItemOutDetails(itemName, itemNo, refCode, description, invoiceNo, outwards, iType, balance, unitPrice, amount, itemInDate);

            ItemDAO itemDAO = new ItemDAO();
            itemDAO.deleteStockOut(itemOutDetails);
            loadTable(invoiceNo);
        } catch (ParseException ex) {
            Logger.getLogger(FormItemOut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBDeleteActionPerformed

    private void jCBIemCatogoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBIemCatogoryActionPerformed

        String itemName = jCBIemCatogory.getSelectedItem().toString();
        fillSubItemNo(itemName);
    }//GEN-LAST:event_jCBIemCatogoryActionPerformed

    private void jCBIemCatogoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCBIemCatogoryItemStateChanged

    }//GEN-LAST:event_jCBIemCatogoryItemStateChanged

    private void jTblStockOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblStockOutMouseClicked
        selectRowItem();
    }//GEN-LAST:event_jTblStockOutMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String invoiceNo = jLInvoiceNo.getText();
        FormPrint formPrint = new FormPrint(invoiceNo);
        // formPrint.getVariables(invoiceNo);
        this.setVisible(false);
        formPrint.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormItemOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormItemOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormItemOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormItemOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>''
        //</editor-fold>''

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormItemOut().setVisible(true);

            }
        });
    }

    private void fillSubItems() {

        ItemDAO itemDAO = new ItemDAO();
        ArrayList jobCatList = itemDAO.getSubItems();
        Iterator i = jobCatList.iterator();
        jCBIemCatogory.removeAllItems();
        while (i.hasNext()) {
            jCBIemCatogory.addItem((String) i.next());
        }
        String itemName = jCBIemCatogory.getSelectedItem().toString();
        fillSubItemNo(itemName);
    }

    private String fillSubItemNo(String itemName) {
        ItemDAO dao = new ItemDAO();
        ArrayList jobSubCatList = dao.fillSubItemNo(itemName);
        Iterator i = jobSubCatList.iterator();
        jCBItemNo.removeAllItems();
        while (i.hasNext()) {
            jCBItemNo.addItem((String) i.next());
        }
        String itemNo = jCBItemNo.getSelectedItem().toString();
        jCBItemNo.setSelectedIndex(0);
        fillSubDescription(itemName, itemNo);

        return itemNo;

    }

    private void fillSubDescription(String itemName, String itemNo) {
        ItemDAO dao = new ItemDAO();
        String itemDes = dao.fillSubDescription(itemName, itemNo);

        jTFDiscription.setText(itemDes);
        fillUnitPrice(itemName, itemNo);
    }

    private void fillUnitPrice(String itemName, String itemNo) {
        ItemDAO dao = new ItemDAO();
        Map<String, String> map = dao.fillUnitPrice(itemName, itemNo);
        String unitPrice = map.get("unitPrice");
        String balance = map.get("balance");

        jLunitPrice.setText(unitPrice);
        jLblBalance.setText(balance);

    }

    private void fillSubRefCode() {
        ItemDAO dao = new ItemDAO();
        ArrayList refCodelist = dao.getSubRefCode();
        Iterator i = refCodelist.iterator();
        jCBRefCode.removeAllItems();
        while (i.hasNext()) {
            jCBRefCode.addItem((String) i.next());
        }
        jCBRefCode.setSelectedIndex(0);
    }

    private void fillInvoceType() {

        // jCBIemCatogory.removeAllItems();
        jCBInvoiceNo.addItem("Select Invoice Type");
        jCBInvoiceNo.addItem("sales");
        jCBInvoiceNo.addItem("services");

    }

    private String getMaxInvoceNo() {

        ItemDAO itemDAO = new ItemDAO();
        Vector maxVec = itemDAO.getInvoMaxStockOut();
        String maxInvo;
        if (maxVec.get(0).toString().equals("[null]")) {
            maxInvo = "000";
        } else {
            String[] value_split = maxVec.toString().split("\\|");
            maxInvo = value_split[1];
        }
        return maxInvo;
    }

    private void fillItemsTypes() {

        // jCBIemCatogory.removeAllItems();
        jCBOutType.addItem("units");
        jCBOutType.addItem("liters");
        jCBOutType.addItem("kilogram");
        jCBOutType.addItem("numbers");
        jCBOutType.addItem("meters");
        jCBOutType.addItem("square meters");

    }

    private void loadTable(String invoNo) {
        ItemDAO dao = new ItemDAO();
        data = dao.getStockOutTbl(invoNo);
        jTblStockOut.setModel(new javax.swing.table.DefaultTableModel(data, header));
        jScrollPane1.setViewportView(jTblStockOut);
    }

    public boolean validateFields() {
        InputValidator validate = new InputValidator();
        if (!validate.validInvo(jLInvoiceNo, "Please enter Invoice NO")) {
            return false;
        } else if (!validate.validateInteger(jTFInwards, "Please enter OutWards")) {
            return false;
        } else if (!validate.validateInteger(jLblBalance, "Stocks Not Availabel")) {
            return false;
        }
        return true;
    }

    private void selectRowItem() {

        //retrieving the selected row index
        int row = jTblStockOut.getSelectedRow();

        //if a single row is selected from the table, take each cell values into the controls
        if (jTblStockOut.getRowSelectionAllowed()) {

            //selectedJobId = Integer.parseInt(jTable_JobDetails.getValueAt(row, 0).toString());
            jCBIemCatogory.setSelectedItem(jTblStockOut.getValueAt(row, 0).toString());
            jCBItemNo.setSelectedItem(jTblStockOut.getValueAt(row, 1).toString());
            jCBRefCode.setSelectedItem(jTblStockOut.getValueAt(row, 2).toString());
            jTFDiscription.setText(jTblStockOut.getValueAt(row, 3).toString());
            jLInvoiceNo.setText(jTblStockOut.getValueAt(row, 4).toString());
            jBInDate.setText(jTblStockOut.getValueAt(row, 10).toString());
            jTFInwards.setText(jTblStockOut.getValueAt(row, 5).toString());
            jCBOutType.setSelectedItem(jTblStockOut.getValueAt(row, 6).toString());
            jLblBalance.setText(jTblStockOut.getValueAt(row, 7).toString());
            // jBInDate.setText(jTblStockOut.getValueAt(row, 9).toString());

        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAdd;
    private javax.swing.JButton jBDelete;
    private javax.swing.JButton jBInDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jCBIemCatogory;
    private javax.swing.JComboBox<String> jCBInvoiceNo;
    private javax.swing.JComboBox<String> jCBItemNo;
    private javax.swing.JComboBox<String> jCBOutType;
    private javax.swing.JComboBox<String> jCBRefCode;
    private javax.swing.JLabel jLInvoiceNo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLblBalance;
    private javax.swing.JLabel jLunitPrice;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFDiscription;
    private javax.swing.JTextField jTFInwards;
    private javax.swing.JTable jTblStockOut;
    // End of variables declaration//GEN-END:variables
}