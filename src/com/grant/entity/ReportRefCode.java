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
public class ReportRefCode {

    private String refCode;
    private Date itemIDateStart;
    private Date itemInDateEnd;

    public ReportRefCode(String refCode, Date itemIDateStart, Date itemInDateEnd) {
        this.refCode = refCode;
        this.itemIDateStart = itemIDateStart;
        this.itemInDateEnd = itemInDateEnd;

    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
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
