/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.excel2DB;

import heps.db.param_list.comman.constants.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author huihui
 */
public class ReadExcel {

    public Workbook wb;

    public ReadExcel(Workbook wb) {
        this.wb = wb;
    }

    public Workbook getWb() {
        return wb;
    }

    public void setWb(Workbook wb) {
        this.wb = wb;
    }

    public ArrayList getSheet() {
        ArrayList dataList = new ArrayList();
        Sheet sheet = wb.getSheetAt(0);
        int colNum = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum();
        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
            Row row = (Row) rit.next();
            if (row.getCell(0) != null) {
                ArrayList oneRow = new ArrayList();
                for (int i = 0; i < colNum; i++) {
                    Cell cell = row.getCell(i);
                    Object o = "";
                    if (null != cell) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                o = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                Double val3 = cell.getNumericCellValue();
                                String val = val3.toString();
                                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                                    Date theDate = cell.getDateCellValue();
                                    SimpleDateFormat format = new SimpleDateFormat(DateFormat.dateFormat);
                                    val = format.format(theDate);
                                }else{
                                    if(Math.abs(val3-val3.intValue())<Double.MIN_VALUE) {
                                        DecimalFormat df = new DecimalFormat("0");
                                        val=df.format(val3);
                                    }
                                }
                                                                
                                o = val;
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                o = cell.getBooleanCellValue();
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                o = cell.getCellFormula();
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                o = "";
                                break;
                            case Cell.CELL_TYPE_ERROR:
                                System.out.println("Error");
                                break;
                        }
                    }
                    oneRow.add(o);
                }
                // System.out.println(oneRow);
                dataList.add(oneRow);
            }
        }
        return dataList;
    }

    public ArrayList getSheet(String sheetName) {
        ArrayList dataList = new ArrayList();
        Sheet sheet = wb.getSheet(sheetName);
        int colNum = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum();
        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext();) {
            Row row = (Row) rit.next();
            if (row.getCell(0) != null) {
                ArrayList oneRow = new ArrayList();
                for (int i = 0; i < colNum; i++) {
                    Cell cell = row.getCell(i);
                    Object o = "";
                    if (null != cell) {
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                o = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                Double val3 = cell.getNumericCellValue();
                                String val = val3.toString();
                                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                                    Date theDate = cell.getDateCellValue();
                                    SimpleDateFormat format = new SimpleDateFormat(DateFormat.dateFormat);
                                    val = format.format(theDate);
                                   // System.out.println("*****"+val);
                                } else {
                                    DecimalFormat df = new DecimalFormat("0");
                                    val = df.format(cell.getNumericCellValue());
                                }
                                o = val;
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                o = cell.getBooleanCellValue();
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                o = cell.getCellFormula();
                                break;
                            case Cell.CELL_TYPE_BLANK:
                                o = "";
                                break;
                            case Cell.CELL_TYPE_ERROR:
                                System.out.println("Error");
                                break;
                        }
                    }
                    oneRow.add(o);
                }
                // System.out.println(oneRow);
                dataList.add(oneRow);
            }
        }
        return dataList;
    }
}
