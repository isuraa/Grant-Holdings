/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grant.entity;

import java.sql.Date;

/**
 *
 * @author Isura Amarasinghe
 */
public class ItemInDetails {

    private String itemName;
    private String itemNo;
    private String description;
    private String invoiceNo;
    private String inwards;
    private String balance;
    private String iType;
    private Date itemInDate;
    private String unitPrice;

    public ItemInDetails(String iName, String iNo, String descript, String invoiceNo, String inwards, String balance, String iType, Date iDate, String unitPrice) {

        this.itemName = iName;
        this.itemNo = iNo;
        this.description = descript;
        this.invoiceNo = invoiceNo;
        this.inwards = inwards;
        this.balance = balance;
        this.iType = iType;
        this.itemInDate = iDate;
        this.unitPrice = unitPrice;

    }
    
       public ItemInDetails(String iName, String iNo, String descript, String inwards, String balance, String iType, Date iDate, String unitPrice) {

        this.itemName = iName;
        this.itemNo = iNo;
        this.description = descript;
        this.inwards = inwards;
        this.balance = balance;
        this.iType = iType;
        this.itemInDate = iDate;
        this.unitPrice = unitPrice;

    }

    /*
    
    Getters and setters
    
     */
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInwards() {
        return inwards;
    }

    public void setInwards(String inwards) {
        this.inwards = inwards;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getiType() {
        return iType;
    }

    public void setiType(String iType) {
        this.iType = iType;
    }

    public Date getItemInDate() {
        return itemInDate;
    }

    public void setItemInDate(Date itemInDate) {
        this.itemInDate = itemInDate;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

}
