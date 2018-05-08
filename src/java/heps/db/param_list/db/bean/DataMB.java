/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.bean;

import heps.db.param_list.db.ejb.DataFacade;
import heps.db.param_list.db.ejb.HistoryDataFacade;
import heps.db.param_list.db.ejb.ManagerFacade;
import heps.db.param_list.db.entity.Data;
import heps.db.param_list.db.entity.Manager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class DataMB implements Serializable {

    private List<Data> dataList;
    private List<Boolean> stateList;
    private Data select;
    private Boolean isLogin;
    private Manager manager;    
    private final DataFacade dataFacade;
    
     public DataMB() {
        this.dataFacade=new DataFacade();
        this.select = new Data();
        this.manager = new Manager();    
    }
     
        
    @PostConstruct
    public void init() {
        stateList = Arrays.asList(false, true, true, true, true, true, true
                , true, true, true, true, true, true, true, true,true);
        isLogin = false;
        this.dataList=dataFacade.getAllData();
    }

    
    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }
    
    
//////////////////////////////////
    public void validate() {
        System.out.println("++++" + manager.getName());
        this.isLogin = new ManagerFacade().validate(manager);
        String msg = "";
        if (this.isLogin) {
            msg = "Login success!";
        } else {
            msg = "The name or password is wrong.";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Boolean getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Boolean isLogin) {
        this.isLogin = isLogin;
    }

    public Data getSelect() {
        return select;
    }

    public void setSelect(Data select) {
        this.select = select;
    }



    public List<Boolean> getStateList() {
        return this.stateList;
    }

    public void onToggle(ToggleEvent e) {
        System.out.println(e.getData());
        if ((int) e.getData() != 0) {
            if (((int) e.getData() - 1) < stateList.size()) {
                stateList.set((int) e.getData() - 1, e.getVisibility() == e.getVisibility().VISIBLE);
            }
        }
    }

    public void cellEdit(CellEditEvent e) {
        System.out.println("edit...........");
        DataTable table = (DataTable) e.getSource();
        Data data = (Data) table.getRowData();
        if (data.getTeamid() != null && data.getTeamid().getManagerid() != null) {
            if (this.manager.getName().equals(data.getTeamid().getManagerid().getName())) {
                String oldValue = e.getOldValue().toString();
                if (!oldValue.equals(data.getValue())) {                   
                   new HistoryDataFacade().setHistoryData(data, oldValue, new Date());
                   dataFacade.updateData(data);                  
                }
            }
        }else{
            data.setValue(e.getOldValue().toString());
        }     
    }
    
    public void delete() {
        System.out.println("delete......");
        String msg = "";
        if (this.select==null) {
            msg = "The record has already been deleted!";
        } else {

            if (this.select.getTeamid() != null && this.select.getTeamid().getManagerid() != null) {
                if (this.manager.getName().equals(this.select.getTeamid().getManagerid().getName())) {
                    if (this.select != null && !"".equals(select)) {
                        this.dataFacade.deleteData(select);
                        msg = "The record has been deleted!";
                    }
                    this.dataList = this.dataFacade.getAllData();
                    this.select = new Data();
                } else {
                    msg = "You do not have the authority to delete this record!";
                }
            } else {
                msg = "You do not have the authority to delete this record!";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

    public void uploadImage(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (this.select != null) {
            FileOutputStream outStream = null;
            try {
                File image = new File(file.getFileName());
                byte[] buffer = file.getContents();
                outStream = new FileOutputStream(image);
                outStream.write(buffer);
                this.dataFacade.setImage(select, image);
                outStream.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DataMB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DataMB.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    outStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(DataMB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        this.select = new Data();
    }

     public void add() {
       // System.out.println("add........");
        this.select.setDatemodified(new Date());
        this.dataFacade.setData(select);
        this.dataList = this.dataFacade.getAllData();
        this.select = new Data();
    }

}
