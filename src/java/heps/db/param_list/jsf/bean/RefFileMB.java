/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.bean;

import heps.db.param_list.db.ejb.HistoryDataFacade;
import heps.db.param_list.db.entity.HistoryData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ApplicationScoped
public class RefFileMB implements Serializable {

    private HistoryData historyData;
    private HistoryDataFacade ejbFacade;
    private StreamedContent refFile =null;

    public RefFileMB() {
        ejbFacade = new HistoryDataFacade();
    }

    public HistoryData getHistoryData() {
        return historyData;
    }

    public void setHistoryData(HistoryData historyData) {

        this.historyData = historyData;
    }

    public void putRefFile(HistoryData historyData) {
        if (historyData != null) {
            if (historyData.getModifiedReference() != null) {
                FileInputStream stream = null;
                try {
                    String name = historyData.getModifiedReference();
                    HttpSession session = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true));
                    String serverPath = session.getServletContext().getRealPath("/");
                    String parentPath = new File(serverPath).getParent();
                    String rootPath = new File(parentPath).getParent();
                    String path = rootPath + "\\reference\\" + name;
                    File file = new File(path);
                    stream = new FileInputStream(file);
                    String type = Files.probeContentType(Paths.get(path));
                    String prefix = name.substring(name.lastIndexOf("."));
                    this.refFile = new DefaultStreamedContent(stream, type, "download" + prefix);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(RefFileMB.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RefFileMB.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        }
    }

    public void uploadRefFile(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        String fileName = file.getFileName();
        HttpSession session = (HttpSession) (FacesContext.getCurrentInstance().getExternalContext().getSession(true));
        String serverPath = session.getServletContext().getRealPath("/");
        String parentPath = new File(serverPath).getParent();
        String rootPath = new File(parentPath).getParent();
        File realPath = new File(rootPath + "\\reference\\");
        if (!realPath.exists()) {
            realPath.mkdirs();
        }
        File saveFile = new File(realPath, file.getFileName());
        byte[] buffer = file.getContents();
        System.out.println(fileName);
        try {
            FileOutputStream outStream = new FileOutputStream(saveFile);
            outStream.write(buffer);
            outStream.close();
            ejbFacade.setHisotryDataURL(historyData, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public StreamedContent getRefFile() {
        if(this.refFile!=null)
           return this.refFile;
        return null;
    }
}
