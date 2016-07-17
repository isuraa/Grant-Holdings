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
public class ItemOutDetails {

    private String itemName;
    private String itemNo;
    private String refCode;
    private String description;
    private String invoiceNo;
    private String outwards;
    private String iType;
    private String balance;
    private String unitPrice;
    private String amount;
    private Date itemOutDate;

    public ItemOutDetails(String itemName, String itemNo, String refCode, String description, String invoiceNo, String outwards, String iType, String balance, String unitPrice, String amount, Date itemOutDate) {
        this.itemName = itemName;
        this.itemNo = itemNo;
        this.refCode = refCode;
        this.description = description;
        this.invoiceNo = invoiceNo;
        this.outwards = outwards;
        this.iType = iType;
        this.balance = balance;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.itemOutDate = itemOutDate;
    }

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

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
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

    public String getOutwards() {
        return outwards;
    }

    public void setOutwards(String outwards) {
        this.outwards = outwards;
    }

    public String getiType() {
        return iType;
    }

    public void setiType(String iType) {
        this.iType = iType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getItemOutDate() {
        return itemOutDate;
    }

    public void setItemOutDate(Date itemOutDate) {
        this.itemOutDate = itemOutDate;
    }

}
