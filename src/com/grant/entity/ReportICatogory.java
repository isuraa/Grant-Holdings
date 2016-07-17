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
public class ReportICatogory {

    private String itemName;
    private Date itemIDateStart;
    private Date itemInDateEnd;

    public ReportICatogory(String itemName, Date itemIDateStart, Date itemInDateEnd) {
        this.itemName = itemName;
        this.itemIDateStart = itemIDateStart;
        this.itemInDateEnd = itemInDateEnd;
    }
    
        public ReportICatogory( Date itemIDateStart, Date itemInDateEnd) {
        this.itemIDateStart = itemIDateStart;
        this.itemInDateEnd = itemInDateEnd;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getItemIDateStart() {
        return itemIDateStart;
    }

    public void setItemIDateStart(Date itemIDateStart) {
        this.itemIDateStart = itemIDateStart;
    }

    public Date getItemInDateEnd() {
        return itemInDateEnd;
    }

    public void setItemInDateEnd(Date itemInDateEnd) {
        this.itemInDateEnd = itemInDateEnd;
    }

}
