/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.bean;

import heps.db.param_list.excel2DB.Data2DB;
import heps.db.param_list.excel2DB.ExcelTool;
import heps.db.param_list.excel2DB.ReadExcel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class FileUploadMB implements Serializable{

    /**
     * Creates a new instance of FileUploadMB
     */
    public FileUploadMB() {
    }
    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload(FileUploadEvent event) throws FileNotFoundException, IOException {     
        file=event.getFile();
        HttpSession session=(HttpSession)(FacesContext.getCurrentInstance().getExternalContext().getSession(true));
        String serverPath=session.getServletContext().getRealPath("/");
        String parentPath=new File(serverPath).getParent();
        String rootPath=new File(parentPath).getParent();
        File realPath=new File(rootPath+"\\upload\\");
        if(!realPath.exists()){
           realPath.mkdirs();
        }      
       File saveFile=new File(realPath,file.getFileName());        
        byte[] buffer=file.getContents();
        FileOutputStream outStream=new FileOutputStream(saveFile);
        outStream.write(buffer);
        outStream.close();
        if(saveFile!=null){
            Workbook wb = ExcelTool.getWorkbook(saveFile);
            ReadExcel readExcel = new ReadExcel(wb);
            Data2DB db=new Data2DB(readExcel.getSheet(),saveFile);
            db.allData2DB();
        }
        FacesMessage msg = new FacesMessage("Success!"," The parameter datas are imported into the database!");  
        FacesContext.getCurrentInstance().addMessage(null, msg);     
    }

}
