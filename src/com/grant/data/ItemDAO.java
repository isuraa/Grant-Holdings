/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grant.data;

import com.grant.database.DBConnManager;
import com.grant.entity.ItemDetails;
import com.grant.entity.ItemInDetails;
import com.grant.entity.ItemOutDetails;
import com.grant.entity.OutPrintDetails;
import com.grant.entity.PrintDetails;
import com.grant.entity.RefCodeDetails;
import com.grant.entity.ReportICatogory;
import com.grant.entity.ReportRefCode;
import com.grant.util.FileDateTime;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Isura Amarasinghe
 */
public class ItemDAO {

    DBConnManager dbConnManager = null;

    public ItemDAO() {

        dbConnManager = new DBConnManager();
    }

    public ArrayList getSubItems() {

        ArrayList itemList = null;
        Connection dbConn = null;

        try {

            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            // int jobCatId = getJobCatId(jobCat);
            String query = "SELECT DISTINCT v_item_name FROM grant_item";

            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            itemList = new ArrayList();

            while (rs.next()) {
                String itmNm = rs.getString(1);
                System.out.println(itmNm);
                itemList.add(itmNm);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed at ItemDAO");
        } finally {

            dbConnManager.con_close(dbConn);
        }
        return itemList;
    }

    public ArrayList fillSubItemNo(String itemName) {

        ArrayList jobSubCatList = null;
        Connection dbConn = null;

        try {

            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            // int itemN = getJobCatId(itemName);
            String query = "SELECT DISTINCT v_item_no FROM grant_item WHERE v_item_name = '" + itemName + "'";

            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            jobSubCatList = new ArrayList();

            while (rs.next()) {
                String subCatName = rs.getString(1);
                System.out.println(subCatName);
                jobSubCatList.add(subCatName);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed at JobSubCatNames");
        } finally {

            dbConnManager.con_close(dbConn);
        }
        return jobSubCatList;
    }

    public String fillSubDescription(String itemName, String itemNo) {

        String descript = null;
        Connection dbConn = null;

        try {

            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String query = "SELECT v_descript FROM grant_item WHERE v_item_name='" + itemName + "' AND v_item_no = '" + itemNo + "'";
            //SELECT v_descript FROM grant_item WHERE v_item_name='B Chemical' AND v_item_no = 'Chem1'

            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                descript = rs.getString("v_descript");
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed at JobSubCatNames");
        } finally {

            dbConnManager.con_close(dbConn);
        }
        return descript;
    }

    public ArrayList getSubRefCode() {

        ArrayList refList = null;
        Connection dbConn = null;

        try {

            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            // int jobCatId = getJobCatId(jobCat);
            String query = "SELECT DISTINCT v_ref_code FROM grant_ref";

            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            refList = new ArrayList();

            while (rs.next()) {
                String refLst = rs.getString(1);
                System.out.println(refLst);
                refList.add(refLst);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed at ItemDAO");
        } finally {

            dbConnManager.con_close(dbConn);
        }
        return refList;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             REF CODE FOR TABLE GET
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public Vector getRepCodeTbl() {

        Vector<Vector<String>> repCodeDetailsVector = null;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT DISTINCT v_ref_code FROM grant_ref";

            ResultSet rs = stmt.executeQuery(query);
            repCodeDetailsVector = new Vector<Vector<String>>();

            while (rs.next()) {
                Vector<String> repCo = new Vector<String>();
                repCo.add(rs.getString(1));

                repCodeDetailsVector.add(repCo);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return repCodeDetailsVector;
    }

    public boolean DeleteRef(RefCodeDetails d) {
        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String repCode = d.getRepCode();

            String query = "DELETE FROM grant_ref WHERE v_ref_code = " + "'" + repCode + "'";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Delete query failed");
            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    /*
    
    Save item with no and disciption
    
     */
    public boolean addNewItem(ItemDetails d) {

        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String itemName = d.getItemName();

            String itemNo = d.getItemNo();

            String description = d.getDescription();

            String query = "INSERT INTO grant_item(v_item_name,v_item_no,v_descript)  " + "VALUES( '" + itemName + "','" + itemNo + "','" + description + "'" + ")";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Insert query failed");

            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    public Vector getitemsCatTbl() {

        Vector<Vector<String>> itemDetailsVector = null;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT DISTINCT v_item_name, v_item_no, v_descript  FROM grant_item";

            ResultSet rs = stmt.executeQuery(query);
            itemDetailsVector = new Vector<Vector<String>>();

            while (rs.next()) {
                Vector<String> item = new Vector<String>();
                item.add(rs.getString(1));
                item.add(rs.getString(2));
                item.add(rs.getString(3));

                itemDetailsVector.add(item);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return itemDetailsVector;
    }

    public boolean DeleteItemCatagory(ItemDetails d) {
        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String itemName = d.getItemName();
            String itemNo = d.getItemNo();
            String description = d.getDescription();

            String query = "DELETE FROM grant_item WHERE v_item_name = " + "'" + itemName + "'" + "AND v_item_no =" + "'" + itemNo + "'" + "AND v_descript =" + "'" + description + "'";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Delete query failed");
            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             REF CODE ADD
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================

     */
    public boolean addNewRefCode(RefCodeDetails d) {

        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String repCode = d.getRepCode();

            String query = "INSERT INTO grant_ref(v_ref_code)  " + "VALUES( '" + repCode + "'" + ")";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Insert query failed");

            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             Stock IN SAVE
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================

     */
    public boolean addItemIn(ItemInDetails d) {

        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String itemName = d.getItemName();
            String itemNo = d.getItemNo();
            String description = d.getDescription();
            //String invoiceNo = d.getInvoiceNo();
            String inwards = d.getInwards();
            String balance = d.getBalance();
            String iType = d.getiType();
            Date itemInDate = d.getItemInDate();
            String unitPrice = d.getUnitPrice();

            String query = "INSERT INTO grant_item_in(v_item_name,v_item_no,v_descrip,i_inwards,i_balance,v_type,d_in_date,i_unit_price)  " + "VALUES( '" + itemName + "','" + itemNo + "','" + description + "','" + inwards + "','" + balance + "','" + iType + "','" + itemInDate + "','" + unitPrice + "'" + ")";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Insert query failed-------");

            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             STOCK OUT GET BALANCEMAX
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public String getBalaceRecord(String itemName, String itemNo) {

        String balance = null;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT (i_balance) FROM grant_item_in WHERE v_item_name='" + itemName + "' AND v_item_no ='" + itemNo + "' ORDER BY i_itin_id DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                balance = rs.getString(1);
            }
        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return balance;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             STOCK OUT UPDATE BALANCEMAX
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public boolean updateBalaceRecord(double balance, String itemName, String itemNo) {

        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            // String query1 = "INSERT INTO grant_item(i_sub_total,i_discount,i_total,v_cus_name,v_address,v_pay_type,v_no,v_order_no)  " + "VALUES( '" + subTotal + "','" + discount + "','" + total + "','" + customerName + "','" + address + "','" + PayType + "','" + noPay + "','" + orderNo + "'" + ") SELECT FROM grant_item WHERE id =" + invoiceNo + ";";
            String query = "UPDATE grant_item_in SET i_balance='" + balance + "' WHERE v_item_name='" + itemName + "' AND v_item_no ='" + itemNo + "' ORDER BY i_itin_id DESC LIMIT 1";
            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val >= 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Insert query failed");

            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;

    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             Stock In SAVE
 
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================

     */
    public boolean addItemOut(ItemOutDetails d) {

        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String itemName = d.getItemName();
            String itemNo = d.getItemNo();
            String refCode = d.getRefCode();
            String description = d.getDescription();
            String invoiceNo = d.getInvoiceNo();
            String outwards = d.getOutwards();
            String iType = d.getiType();
            String balance = d.getBalance();
            String unitPrice = d.getUnitPrice();
            String amount = d.getAmount();
            Date itemInDate = d.getItemOutDate();

            String query = "INSERT INTO grant_item_out(v_item_name,v_item_no,v_ref_code,v_descrip,v_invo_no,i_outwards,v_outtype,i_balance,i_unit_price,i_amount,d_in_date)  " + "VALUES( '" + itemName + "','" + itemNo + "','" + refCode + "','" + description + "','" + invoiceNo + "','" + outwards + "','" + iType + "','" + balance + "','" + unitPrice + "','" + amount + "','" + itemInDate + "'" + ")";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Insert query failed-------");

            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             STOCK OUT GET INVOICE MAX VALUE
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    https://www.youtube.com/watch?v=kK_Miz2AOUA&feature=youtu.be
     */
    public Vector getInvoMaxStockOut() {

        Vector<Vector<String>> itemDetailsVector = null;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "select max(v_invo_no) from grant_item_out";

            ResultSet rs = stmt.executeQuery(query);
            itemDetailsVector = new Vector<Vector<String>>();

            while (rs.next()) {
                Vector<String> item = new Vector<String>();
                item.add(rs.getString(1));
                itemDetailsVector.add(item);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return itemDetailsVector;
    }

    public Map<String, String> fillUnitPrice(String itemName, String itemNo) {
        String unitPrice = null;
        String balance = null;
        Connection dbConn = null;
        Map<String, String> map = new HashMap<>();

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT i_unit_price,i_balance FROM grant_item_in WHERE v_item_name ='" + itemName + "' AND v_item_no ='" + itemNo + "' ORDER BY i_itin_id DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                unitPrice = rs.getString(1);
                balance = rs.getString(2);
            }

            map.put("unitPrice", unitPrice);
            map.put("balance", balance);
        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------fillUnitPrice Select query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return map;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             STOCK OUT REPORT
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    https://www.youtube.com/watch?v=kK_Miz2AOUA&feature=youtu.be
     */
    public List getAllItemOutReport() throws FileNotFoundException, IOException {

        ResultSet rs = null;
        Connection dbConn = null;
        List ss = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String query = "SELECT * FROM grant_item_out";
            //String query = "INSERT INTO grant_item_out(v_item_name,v_item_no,v_ref_code,v_descrip,v_invo_no,i_outwards,i_balance,d_in_date)  " + "VALUES( '" + itemName + "','" + itemNo + "','" + refCode +  "','"+ description + "','" + invoiceNo + "','" + outwards + "','" + balance +  "','"+ itemInDate + "'" + ")";

            System.out.println(query);

            rs = stmt.executeQuery(query);

            /////////////
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("lawix10");
            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("CellHeadName1");
            rowhead.createCell((short) 1).setCellValue("CellHeadName2");
            rowhead.createCell((short) 2).setCellValue("CellHeadName3");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);
                // row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("column1")));
                row.createCell((short) 1).setCellValue(rs.getString(1));
                row.createCell((short) 2).setCellValue(rs.getString(2));
                i++;
            }
            String yemi = "C:/Users/Isura Amarasinghe/Desktop/test.xls";
            //C:/Users/Isura Amarasinghe/Desktop
            FileOutputStream fileOut = new FileOutputStream(yemi);
            workbook.write(fileOut);
            fileOut.close();
            ///////////

        } catch (SQLException sQLException) {

            System.out.println(sQLException + "-----------Insert query failed-------");

            rs = null;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return ss;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             STOCK ACCORDING TO STOCK IN
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public boolean getStockINReport(ReportICatogory rrc) throws FileNotFoundException, IOException {

        ResultSet rs = null;
        Connection dbConn = null;
        boolean ss = false;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();
            String query = "SELECT * FROM grant_item_in WHERE d_in_date BETWEEN '" + rrc.getItemIDateStart() + "' AND '" + rrc.getItemInDateEnd() + "'";
            System.out.println(query);

            rs = stmt.executeQuery(query);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("lawix10");
            HSSFRow rowhead = sheet.createRow((short) 0);

            rowhead.createCell((short) 1).setCellValue("Item ID");
            rowhead.createCell((short) 2).setCellValue("v_item_name");
            rowhead.createCell((short) 3).setCellValue("v_item_no");
            rowhead.createCell((short) 4).setCellValue("v_descrip");
            rowhead.createCell((short) 5).setCellValue("i_inwards");
            rowhead.createCell((short) 6).setCellValue("i_balance");
            rowhead.createCell((short) 7).setCellValue("v_type");
            rowhead.createCell((short) 8).setCellValue("d_in_date");
            rowhead.createCell((short) 9).setCellValue("i_unit_price");

            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);
                //row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt(0)));
                row.createCell((short) 1).setCellValue(rs.getString(1));
                row.createCell((short) 2).setCellValue(rs.getString(2));
                row.createCell((short) 3).setCellValue(rs.getString(3));
                row.createCell((short) 4).setCellValue(rs.getString(4));
                row.createCell((short) 5).setCellValue(rs.getString(5));
                row.createCell((short) 6).setCellValue(rs.getString(6));
                row.createCell((short) 7).setCellValue(rs.getString(7));
                row.createCell((short) 8).setCellValue(rs.getString(8));
                row.createCell((short) 9).setCellValue(rs.getString(9));

                i++;
            }

            FileDateTime fileDateTime = new FileDateTime();
            FileOutputStream fileOut = new FileOutputStream(fileDateTime.getFileName("Stock_In"));
            workbook.write(fileOut);
            ss=true;
        } catch (SQLException sQLException) {
            ss=false;
            System.out.println(sQLException + "-----------Insert query failed-------");
            rs = null;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return ss;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             STOCK ACCORDING TO STOCK OUT REPORT
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public boolean getStockOutReport(ReportICatogory ric) throws FileNotFoundException, IOException {

        ResultSet rs = null;
        Connection dbConn = null;
        boolean ss = false;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String query = "SELECT * FROM grant_item_print WHERE d_in_date BETWEEN '" + ric.getItemIDateStart() + "' AND '" + ric.getItemInDateEnd() + "'";

            System.out.println(query);

            rs = stmt.executeQuery(query);

            /////////////
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("lawix10");
            HSSFRow rowhead = sheet.createRow((short) 0);
            //rowhead.createCell((short) 0).setCellValue("Item ID");

            rowhead.createCell((short) 1).setCellValue("i_itin_id");
            rowhead.createCell((short) 2).setCellValue("v_item_name");
            rowhead.createCell((short) 3).setCellValue("v_item_no");
            rowhead.createCell((short) 4).setCellValue("v_ref_code");
            rowhead.createCell((short) 5).setCellValue("v_descrip");
            rowhead.createCell((short) 6).setCellValue("v_invo_no");
            rowhead.createCell((short) 7).setCellValue("i_outwards");
            rowhead.createCell((short) 8).setCellValue("v_outtype");
            rowhead.createCell((short) 9).setCellValue("i_balance");
            rowhead.createCell((short) 10).setCellValue("i_unit_price");
            rowhead.createCell((short) 11).setCellValue("i_amount");
            rowhead.createCell((short) 12).setCellValue("i_sub_total");
            rowhead.createCell((short) 13).setCellValue("i_discount");
            rowhead.createCell((short) 14).setCellValue("i_total");
            rowhead.createCell((short) 15).setCellValue("d_in_date");
            rowhead.createCell((short) 16).setCellValue("v_cus_name");
            rowhead.createCell((short) 17).setCellValue("v_address");
            rowhead.createCell((short) 18).setCellValue("v_payType");
            rowhead.createCell((short) 19).setCellValue("v_no");
            rowhead.createCell((short) 20).setCellValue("v_order_no");

            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);
                //row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt(0)));
                row.createCell((short) 1).setCellValue(rs.getString(1));
                row.createCell((short) 2).setCellValue(rs.getString(2));
                row.createCell((short) 3).setCellValue(rs.getString(3));
                row.createCell((short) 4).setCellValue(rs.getString(4));
                row.createCell((short) 5).setCellValue(rs.getString(5));
                row.createCell((short) 6).setCellValue(rs.getString(6));
                row.createCell((short) 7).setCellValue(rs.getString(7));
                row.createCell((short) 8).setCellValue(rs.getString(8));
                row.createCell((short) 9).setCellValue(rs.getString(9));
                row.createCell((short) 10).setCellValue(rs.getString(10));
                row.createCell((short) 11).setCellValue(rs.getString(11));
                row.createCell((short) 12).setCellValue(rs.getString(12));
                row.createCell((short) 13).setCellValue(rs.getString(13));
                row.createCell((short) 14).setCellValue(rs.getString(14));
                row.createCell((short) 15).setCellValue(rs.getString(15));
                row.createCell((short) 16).setCellValue(rs.getString(16));
                row.createCell((short) 17).setCellValue(rs.getString(17));
                row.createCell((short) 18).setCellValue(rs.getString(18));
                row.createCell((short) 19).setCellValue(rs.getString(19));
                row.createCell((short) 20).setCellValue(rs.getString(20));
                i++;
            }

            FileDateTime fileDateTime = new FileDateTime();
            FileOutputStream fileOut = new FileOutputStream(fileDateTime.getFileName("Stock_Out"));
            workbook.write(fileOut);
            fileOut.close();
            ss = true;

        } catch (SQLException sQLException) {
            ss = false;
            System.out.println(sQLException + "-----------Insert query failed-------");

            rs = null;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return ss;
    }

    //////////////
    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             STOCK ACCORDING TO ITEM CATOGARY REPORT
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public List getItemCatogaryReport(ReportICatogory ric) throws FileNotFoundException, IOException {

        ResultSet rs = null;
        Connection dbConn = null;
        List ss = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String query = "SELECT * FROM grant_item_out WHERE d_in_date BETWEEN '" + ric.getItemIDateStart() + "' AND '" + ric.getItemInDateEnd() + "' AND v_item_name = '" + ric.getItemName() + "'";
            //SELECT * FROM grant_item_out WHERE d_in_date BETWEEN '2016-01-07' AND '2016-01-08' AND v_item_name = 'Item 1 chm'
            //String query = "INSERT INTO grant_item_out(v_item_name,v_item_no,v_ref_code,v_descrip,v_invo_no,i_outwards,i_balance,d_in_date)  " + "VALUES( '" + itemName + "','" + itemNo + "','" + refCode +  "','"+ description + "','" + invoiceNo + "','" + outwards + "','" + balance +  "','"+ itemInDate + "'" + ")";

            System.out.println(query);

            rs = stmt.executeQuery(query);

            /////////////
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("lawix10");
            HSSFRow rowhead = sheet.createRow((short) 0);
            //rowhead.createCell((short) 0).setCellValue("Item ID");
            rowhead.createCell((short) 1).setCellValue("i_itin_id");
            rowhead.createCell((short) 2).setCellValue("v_item_name");
            rowhead.createCell((short) 3).setCellValue("v_item_no");
            rowhead.createCell((short) 4).setCellValue("v_ref_code");
            rowhead.createCell((short) 5).setCellValue("v_descrip");
            rowhead.createCell((short) 6).setCellValue("v_invo_no");
            rowhead.createCell((short) 7).setCellValue("i_outwards");
            rowhead.createCell((short) 8).setCellValue("v_outtype");
            rowhead.createCell((short) 9).setCellValue("i_balance");
            rowhead.createCell((short) 10).setCellValue("i_unit_price");
            rowhead.createCell((short) 11).setCellValue("i_amount");
            rowhead.createCell((short) 12).setCellValue("i_sub_total");
            rowhead.createCell((short) 13).setCellValue("i_discount");
            rowhead.createCell((short) 14).setCellValue("i_total");
            rowhead.createCell((short) 15).setCellValue("d_in_date");
            rowhead.createCell((short) 16).setCellValue("v_cus_name");
            rowhead.createCell((short) 17).setCellValue("v_address");
            rowhead.createCell((short) 18).setCellValue("v_payType");
            rowhead.createCell((short) 19).setCellValue("v_no");
            rowhead.createCell((short) 20).setCellValue("v_order_no");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);
                //row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt(0)));
                row.createCell((short) 1).setCellValue(rs.getString(1));
                row.createCell((short) 2).setCellValue(rs.getString(2));
                row.createCell((short) 3).setCellValue(rs.getString(3));
                row.createCell((short) 4).setCellValue(rs.getString(4));
                row.createCell((short) 5).setCellValue(rs.getString(5));
                row.createCell((short) 6).setCellValue(rs.getString(6));
                row.createCell((short) 7).setCellValue(rs.getString(7));
                row.createCell((short) 8).setCellValue(rs.getString(8));
                row.createCell((short) 9).setCellValue(rs.getString(9));
                row.createCell((short) 10).setCellValue(rs.getString(10));
                row.createCell((short) 11).setCellValue(rs.getString(11));
                row.createCell((short) 12).setCellValue(rs.getString(12));
                row.createCell((short) 13).setCellValue(rs.getString(13));
                row.createCell((short) 14).setCellValue(rs.getString(14));
                row.createCell((short) 15).setCellValue(rs.getString(15));
                row.createCell((short) 16).setCellValue(rs.getString(16));
                row.createCell((short) 17).setCellValue(rs.getString(17));
                row.createCell((short) 18).setCellValue(rs.getString(18));
                row.createCell((short) 19).setCellValue(rs.getString(19));
                row.createCell((short) 20).setCellValue(rs.getString(20));
                i++;
            }
            String yemi = "C:/Users/Isura Amarasinghe/Desktop/test.xls";
            //C:/Users/Isura Amarasinghe/Desktop
            FileOutputStream fileOut = new FileOutputStream(yemi);
            workbook.write(fileOut);
            fileOut.close();
            ///////////

        } catch (SQLException sQLException) {

            System.out.println(sQLException + "-----------Insert query failed-------");

            rs = null;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return ss;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             STOCK ACCORDING TO ITEM CATOGARY REPORT
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public List getRefPerfReport(ReportRefCode rrc) throws FileNotFoundException, IOException {

        ResultSet rs = null;
        Connection dbConn = null;
        List ss = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String query = "SELECT * FROM grant_item_out WHERE d_in_date BETWEEN '" + rrc.getItemIDateStart() + "' AND '" + rrc.getItemInDateEnd() + "' AND v_ref_code = '" + rrc.getRefCode() + "'";
            //SELECT * FROM grant_item_out WHERE d_in_date BETWEEN '2016-01-07' AND '2016-01-08' AND v_item_name = 'Item 1 chm'
            //String query = "INSERT INTO grant_item_out(v_item_name,v_item_no,v_ref_code,v_descrip,v_invo_no,i_outwards,i_balance,d_in_date)  " + "VALUES( '" + itemName + "','" + itemNo + "','" + refCode +  "','"+ description + "','" + invoiceNo + "','" + outwards + "','" + balance +  "','"+ itemInDate + "'" + ")";

            System.out.println(query);

            rs = stmt.executeQuery(query);

            /////////////
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("lawix10");
            HSSFRow rowhead = sheet.createRow((short) 0);
            //rowhead.createCell((short) 0).setCellValue("Item ID");
            rowhead.createCell((short) 1).setCellValue("i_itin_id");
            rowhead.createCell((short) 2).setCellValue("v_item_name");
            rowhead.createCell((short) 3).setCellValue("v_item_no");
            rowhead.createCell((short) 4).setCellValue("v_ref_code");
            rowhead.createCell((short) 5).setCellValue("v_descrip");
            rowhead.createCell((short) 6).setCellValue("v_invo_no");
            rowhead.createCell((short) 7).setCellValue("i_outwards");
            rowhead.createCell((short) 8).setCellValue("v_outtype");
            rowhead.createCell((short) 9).setCellValue("i_balance");
            rowhead.createCell((short) 10).setCellValue("i_unit_price");
            rowhead.createCell((short) 11).setCellValue("i_amount");
            rowhead.createCell((short) 12).setCellValue("i_sub_total");
            rowhead.createCell((short) 13).setCellValue("i_discount");
            rowhead.createCell((short) 14).setCellValue("i_total");
            rowhead.createCell((short) 15).setCellValue("d_in_date");
            rowhead.createCell((short) 16).setCellValue("v_cus_name");
            rowhead.createCell((short) 17).setCellValue("v_address");
            rowhead.createCell((short) 18).setCellValue("v_payType");
            rowhead.createCell((short) 19).setCellValue("v_no");
            rowhead.createCell((short) 20).setCellValue("v_order_no");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);
                //row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt(0)));
                row.createCell((short) 1).setCellValue(rs.getString(1));
                row.createCell((short) 2).setCellValue(rs.getString(2));
                row.createCell((short) 3).setCellValue(rs.getString(3));
                row.createCell((short) 4).setCellValue(rs.getString(4));
                row.createCell((short) 5).setCellValue(rs.getString(5));
                row.createCell((short) 6).setCellValue(rs.getString(6));
                row.createCell((short) 7).setCellValue(rs.getString(7));
                row.createCell((short) 8).setCellValue(rs.getString(8));
                row.createCell((short) 9).setCellValue(rs.getString(9));
                row.createCell((short) 10).setCellValue(rs.getString(10));
                row.createCell((short) 11).setCellValue(rs.getString(11));
                row.createCell((short) 12).setCellValue(rs.getString(12));
                row.createCell((short) 13).setCellValue(rs.getString(13));
                row.createCell((short) 14).setCellValue(rs.getString(14));
                row.createCell((short) 15).setCellValue(rs.getString(15));
                row.createCell((short) 16).setCellValue(rs.getString(16));
                row.createCell((short) 17).setCellValue(rs.getString(17));
                row.createCell((short) 18).setCellValue(rs.getString(18));
                row.createCell((short) 19).setCellValue(rs.getString(19));
                row.createCell((short) 20).setCellValue(rs.getString(20));
                i++;
            }
            String yemi = "C:/Users/Isura Amarasinghe/Desktop/ref.xls";
            //C:/Users/Isura Amarasinghe/Desktop
            FileOutputStream fileOut = new FileOutputStream(yemi);
            workbook.write(fileOut);
            fileOut.close();
            ///////////

        } catch (SQLException sQLException) {

            System.out.println(sQLException + "-----------Insert query failed-------");

            rs = null;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return ss;
    }

    public List resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList(50);
        while (rs.next()) {
            HashMap row = new HashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             EDIT STOCK IN GET ALL
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public Vector getStockInTbl() {

        Vector<Vector<String>> stockDetailsVector = null;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT * FROM grant_item_in";

            ResultSet rs = stmt.executeQuery(query);
            stockDetailsVector = new Vector<Vector<String>>();

            while (rs.next()) {
                Vector<String> repCo = new Vector<String>();
                repCo.add(rs.getString(1));
                repCo.add(rs.getString(2));
                repCo.add(rs.getString(3));
                repCo.add(rs.getString(4));
                repCo.add(rs.getString(5));
                repCo.add(rs.getString(6));
                repCo.add(rs.getString(7));
                repCo.add(rs.getString(8));
                repCo.add(rs.getString(9));
                //repCo.add(rs.getString(10));

                stockDetailsVector.add(repCo);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return stockDetailsVector;
    }

    public boolean DeleteStockIn(String invoiceNo) {
        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "DELETE FROM grant_item_in WHERE i_itin_id =" + "'" + invoiceNo + "'";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Delete query failed");
            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             EDIT STOCK OUT GET ALL
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public Vector getStockOutTbl(String invoNo) {

        Vector<Vector<String>> stockDetailsVector = null;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT * FROM grant_item_out WHERE v_invo_no = '" + invoNo + "'";

            ResultSet rs = stmt.executeQuery(query);
            stockDetailsVector = new Vector<Vector<String>>();

            while (rs.next()) {
                Vector<String> repCo = new Vector<String>();
                repCo.add(rs.getString(2));
                repCo.add(rs.getString(3));
                repCo.add(rs.getString(4));
                repCo.add(rs.getString(5));
                repCo.add(rs.getString(6));
                repCo.add(rs.getString(7));
                repCo.add(rs.getString(8));
                repCo.add(rs.getString(9));
                repCo.add(rs.getString(10));
                repCo.add(rs.getString(11));
                repCo.add(rs.getString(12));

                stockDetailsVector.add(repCo);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return stockDetailsVector;
    }

    public boolean deleteStockOut(ItemOutDetails d) {
        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String itemName = d.getItemName();
            String itemNo = d.getItemNo();
            String refCode = d.getRefCode();
            String description = d.getDescription();
            String invoiceNo = d.getInvoiceNo();
            String outwards = d.getOutwards();
            String iType = d.getiType();
            String balance = d.getBalance();
            // Date itemOutDate=d.setItemOutDate();

            String query = "DELETE FROM grant_item_out WHERE v_item_name = " + "'" + itemName + "'" + "AND v_item_no =" + "'" + itemNo + "'" + "AND v_descrip =" + "'" + description + "'" + "AND v_invo_no =" + "'" + invoiceNo + "'" + "AND i_outwards =" + "'" + outwards + "'" + "AND v_outtype =" + "'" + iType + "'";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Delete query failed");
            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    public OutPrintDetails getStockOutToPrint(String invoNo) {

        String repCode = null;
        Connection dbConn = null;
        OutPrintDetails outPrintDetails = new OutPrintDetails();

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT DISTINCT (v_ref_code) FROM grant_item_out WHERE v_invo_no = '" + invoNo + "'";

            ResultSet rs = null;
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                repCode = rs.getString(1);

            }
            outPrintDetails.setRefCode(repCode);
        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------getStockOutToPrint query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return outPrintDetails;
    }

    public String getPrintSubTotal(String invoNo) {

        Connection dbConn = null;
        String subTotal = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT SUM(i_amount) FROM grant_item_out WHERE v_invo_no = '" + invoNo + "'";

            ResultSet rs = null;
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                subTotal = rs.getString(1);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------getStockOutToPrint query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return subTotal;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             Stock In SAVE
 
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================

     */
    public boolean authentication(String userName, String passWrd) {

        boolean result = false;
        Connection dbConn = null;
        String pw = null;

        try {
            dbConn = dbConnManager.connect();
            Statement stmt = dbConn.createStatement();

            String query = "SELECT * FROM grant_user WHERE v_user_name = '" + userName + "' AND v_pass_wrd = '" + passWrd + "'";

            ResultSet rs = null;
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                pw = rs.getString(2);
                if (passWrd.equals(pw)) {
                    result = true;
                }

            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------getStockOutToPrint query failed");
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             EDIT STOCK OUT GET ALL
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
     */
    public String balanceHandler(PrintDetails d) {

        String itemName = null;
        String itemNo = null;
        String bal = null;
        String outwards = null;
        Connection dbConn = null;

        try {

            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String query = "SELECT v_item_name,v_item_no,i_outwards,i_balance FROM grant_item_out WHERE v_invo_no='" + d.getInvoiceNo() + "'";
            //SELECT v_descript FROM grant_item WHERE v_item_name='B Chemical' AND v_item_no = 'Chem1'

            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                itemName = rs.getString("v_item_name");
                itemNo = rs.getString("v_item_no");
                outwards = rs.getString("i_outwards");
                bal = rs.getString("i_balance");

                double bl = Double.parseDouble(bal);
                double ot = Double.parseDouble(outwards);
                double newBal = bl - ot;

                updateBalaceRecord(newBal, itemName, itemNo);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed at JobSubCatNames");
        } finally {

            dbConnManager.con_close(dbConn);
        }

        return null;
    }

    /*
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================
    
                                             GET ALL DATA FROM OUT TABLE AND
    
 
    
                                             SAVE TO PRINT TABLE
    
    =====================================================================================================
    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| 
    =====================================================================================================

   
     */
    public boolean addItemOutToPrint(PrintDetails d) {

        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String query = "INSERT INTO grant_item_print (v_item_name,v_item_no,v_ref_code,v_descrip,v_invo_no,i_outwards,v_outtype,i_balance,i_unit_price,i_amount,d_in_date) SELECT v_item_name,v_item_no,v_ref_code,v_descrip,v_invo_no,i_outwards,v_outtype,i_balance,i_unit_price,i_amount,d_in_date FROM grant_item_out WHERE v_invo_no ='" + d.getInvoiceNo() + "'";

            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val >= 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Insert query failed-------");

            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;
    }

    //Add customer Details 
    public boolean addCusDetailsToPrint(PrintDetails d) {

        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = dbConnManager.connect();

            Statement stmt = dbConn.createStatement();

            String invoiceNo = d.getInvoiceNo();
            String customerName = d.getCustomerName();
            String address = d.getAddress();
            String PayType = d.getPayType();
            String orderNo = d.getOrderNo();
            String noPay = d.getNoPay();
            String subTotal = d.getSubTotal();
            String discount = d.getDiscount();
            String total = d.getTotal();

            // String query1 = "INSERT INTO grant_item(i_sub_total,i_discount,i_total,v_cus_name,v_address,v_pay_type,v_no,v_order_no)  " + "VALUES( '" + subTotal + "','" + discount + "','" + total + "','" + customerName + "','" + address + "','" + PayType + "','" + noPay + "','" + orderNo + "'" + ") SELECT FROM grant_item WHERE id =" + invoiceNo + ";";
            String query = "UPDATE grant_item_print SET i_sub_total ='" + subTotal + "', i_discount='" + discount + "',i_total='" + total + "',v_cus_name='" + customerName + "',v_address='" + address + "',v_pay_type='" + PayType + "',v_no='" + noPay + "',v_order_no='" + orderNo + "' WHERE v_invo_no='" + invoiceNo + "';";
            System.out.println(query);

            int val = stmt.executeUpdate(query);

            if (val >= 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Insert query failed");

            result = false;
        } finally {
            dbConnManager.con_close(dbConn);
        }
        return result;

    }
}
