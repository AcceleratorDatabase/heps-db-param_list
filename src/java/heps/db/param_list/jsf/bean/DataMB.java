/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.bean;

import heps.db.param_list.ejb.ManagerFacade;
import heps.db.param_list.ejb.ParameterFacade;
import heps.db.param_list.entity.Manager;
import heps.db.param_list.entity.Parameter;
import heps.db.param_list.jsf.ejb.DataDispFacade;
import heps.db.param_list.jsf.entity.DataDisp;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.ToggleEvent;
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
            msg = "Login success, you could edit, delete or add a record.";
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

    public void setSelect(DataDisp select) {;
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

    public List<Boolean> getStateList() {
        return this.stateList;
    }

    public void onToggle(ToggleEvent e) {
        stateList.set((int) e.getData(), e.getVisibility() == e.getVisibility().VISIBLE);
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
        this.dataDispList = ejbFacade.getDataDispList();
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
        String msg="";
        if (this.select.getData().getTeamid() != null && this.select.getData().getTeamid().getManagerid() != null) {
            if (this.manager.getName().equals(this.select.getData().getTeamid().getManagerid().getName())) {
                if (this.select != null && !"".equals(select)) {
                    this.ejbFacade.delete(select);
                    msg="The record has been deleted!";
                }
                this.dataDispList = ejbFacade.getDataDispList();
                this.select = new DataDisp();
            }
        } else {
            msg="You do not have the authority to delete this record!";           
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
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
