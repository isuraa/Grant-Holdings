/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grant.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Isura Amarasinghe
 */
public class FileDateTime {

    public String getFileName(String fileType) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_hh_mm");
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDateTime = dateTime.format(formatter) + ".xls";
        String fileName = "C:/Users/Isura Amarasinghe/Desktop/" + fileType + formattedDateTime;
        return fileName;

    }

}
