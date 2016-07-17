/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grant.entity;

/**
 *
 * @author Isura Amarasinghe
 */
public class ItemDetails {

    private String itemName;
    private String itemNo;
    private String description;

    public ItemDetails(String iName, String iNo, String des) {

        this.itemName = iName;
        this.itemNo = iNo;
        this.description = des;
    }

    /* 
    Getters and Setters
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

}
