/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.bean;

import heps.db.param_list.db.ejb.ManagerFacade;
import heps.db.param_list.db.ejb.ParameterFacade;
import heps.db.param_list.db.entity.Manager;
import heps.db.param_list.db.entity.Parameter;
import heps.db.param_list.jsf.ejb.DataDispFacade;
import heps.db.param_list.jsf.entity.DataDisp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class DataMB implements Serializable {

    private DataDispFacade ejbFacade;
    private List<DataDisp> dataDispList;

    private List<Boolean> stateList;
    private Parameter selectedParameter;
    private DataDisp select;
    private Boolean isLogin;
    private Manager manager;



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

    public DataDisp getSelect() {
        return select;
    }

    public void setSelect(DataDisp select) {
        System.out.println(select.getId());
        this.select = select;
    }

    public DataMB() {
        ejbFacade = new DataDispFacade();
        dataDispList = ejbFacade.getDataDispList();
        this.selectedParameter = new Parameter();
        this.select = new DataDisp();
        this.manager = new Manager();

    }

    @PostConstruct
    public void init() {
        stateList = Arrays.asList(false, true, true, true, true, true, true, true, true, true, true, true, true, true, true);
        isLogin = false;
    }

    public List<DataDisp> getDataDispList() {
        //dataDispList = ejbFacade.getDataDispList();
        return dataDispList;
    }

    public void setDataDispList(List<DataDisp> dataDispList) {
        this.dataDispList = dataDispList;
    }

    public List<Boolean> getStateList() {
        return this.stateList;
    }

    public void onToggle(ToggleEvent e) {
        System.out.println(e.getData());
        if ((int) e.getData()!=0) {
            if (((int) e.getData() - 1) < stateList.size()) {
                stateList.set((int) e.getData() - 1, e.getVisibility() == e.getVisibility().VISIBLE);
            }
        }
    }

    public void cellEdit(CellEditEvent e) {
        DataTable table = (DataTable) e.getSource();
        DataDisp dataDisp = (DataDisp) table.getRowData();
        if (dataDisp.getData().getTeamid() != null && dataDisp.getData().getTeamid().getManagerid() != null) {
            if (this.manager.getName().equals(dataDisp.getData().getTeamid().getManagerid().getName())) {
                String oldValue = e.getOldValue().toString();
                if (!oldValue.equals(dataDisp.getValue())) {
                    ejbFacade.edit(dataDisp, oldValue);
                }
            }
        }
        this.setDataDispList(ejbFacade.getDataDispList());

    }

    public void add() {
        System.out.println("add........");
        this.ejbFacade.add(select, selectedParameter);
        this.dataDispList = ejbFacade.getDataDispList();
        this.select = new DataDisp();
        this.selectedParameter = new Parameter();
    }

    public void delete() {
        System.out.println("delete......");
        String msg = "";
        if (this.select == null || this.select.getData() == null) {
            msg = "The record has already been deleted!";
        } else {

            if (this.select.getData().getTeamid() != null && this.select.getData().getTeamid().getManagerid() != null) {
                if (this.manager.getName().equals(this.select.getData().getTeamid().getManagerid().getName())) {
                    if (this.select != null && !"".equals(select)) {
                        this.ejbFacade.delete(select);
                        msg = "The record has been deleted!";
                    }
                    this.dataDispList = ejbFacade.getDataDispList();
                    this.select = new DataDisp();
                }else{
                   msg = "You do not have the authority to delete this record!";
                }
            } else {
                msg = "You do not have the authority to delete this record!";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
    }

     public void uploadImage(FileUploadEvent event) {
      UploadedFile file=event.getFile();
      if(this.select!=null&&this.select.getData()!=null){
          FileOutputStream outStream=null;
          try {
              File image=new File(file.getFileName());
              byte[] buffer=file.getContents();
              outStream = new FileOutputStream(image);
              outStream.write(buffer);
              this.ejbFacade.setImage(select, image);
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
      this.select = new DataDisp();
    }
    
    public Parameter getSelectedParameter() {
        return selectedParameter;
    }

    public void setSelectedParameter(Parameter selectedParameter) {
        this.selectedParameter = selectedParameter;
    }

    public void parameterSelect(AjaxBehaviorEvent event) {
        if (((UIOutput) event.getSource()).getValue() != null) {
            int paraId = Integer.parseInt(((UIOutput) event.getSource()).getValue().toString());
            this.setSelectedParameter(new ParameterFacade().getParameterById(paraId));

        }
    }

    public void systemSelect(AjaxBehaviorEvent event) {
        if (((UIOutput) event.getSource()).getValue() != null) {
            String system = ((UIOutput) event.getSource()).getValue().toString();
            this.select.setSystem(system);
        }
    }

    public void subsystemSelect(AjaxBehaviorEvent event) {
        if (((UIOutput) event.getSource()).getValue() != null) {
            String subsystem = ((UIOutput) event.getSource()).getValue().toString();
            this.select.setSubsystem(subsystem);
        }
    }

    public void devicetypeSelect(AjaxBehaviorEvent event) {
        if (((UIOutput) event.getSource()).getValue() != null) {
            String devicetype = ((UIOutput) event.getSource()).getValue().toString();
            this.select.setDevice(devicetype);
        }
    }

    public void attributeSelect(AjaxBehaviorEvent event) {
        if (((UIOutput) event.getSource()).getValue() != null) {
            String attribute = ((UIOutput) event.getSource()).getValue().toString();
            this.select.setAttibute(attribute);
        }
    }

    public void teamSelect(AjaxBehaviorEvent event) {
        if (((UIOutput) event.getSource()).getValue() != null) {
            String team = ((UIOutput) event.getSource()).getValue().toString();
            this.select.setTeam(team);
        }
    }

}
