/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.excel2DB;

import java.io.File;
import org.apache.poi.ss.usermodel.Workbook;
import heps.db.param_list.jsf.ejb.DataDispFacade;
import java.util.List;
import heps.db.param_list.jsf.entity.DataDisp;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lvhuihui
 */
public class Test {

    public static void main(String[] args) throws IOException {
        FileInputStream fin = null;

        File file = new File("D:\\NetBeansProjects\\heps-db-param_list\\schema\\parameter_list.png");
        fin = new FileInputStream(file);
        byte[] bytes = null;
        bytes = new byte[fin.available()];
        fin.read(bytes);
        System.out.println(bytes);
        fin.close();


        /* File file=new File("E:\\高能所数据库\\injector_parameter_list.xlsx");
            Workbook wb = ExcelTool.getWorkbook(file);
            ReadExcel readExcel = new ReadExcel(wb);
            Data2DB db=new Data2DB(readExcel.getSheet(),file);
            db.allData2DB();*/
 /* DataDispFacade df=new DataDispFacade();
            List l=df.getDataDispList();
            DataDisp d=(DataDisp) l.get(0);*/
    }
}
