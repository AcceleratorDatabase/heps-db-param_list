/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.bean;

import static com.sun.javafx.logging.PulseLogger.addMessage;
import heps.db.param_list.entity.Data;
import heps.db.param_list.ejb.DataFacade;
import heps.db.param_list.ejb.HistoryDataFacade;
import heps.db.param_list.ejb.ParameterFacade;
import heps.db.param_list.entity.Parameter;
import heps.db.param_list.jsf.ejb.DataDispFacade;
import heps.db.param_list.jsf.entity.DataDisp;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;
import static org.primefaces.model.Visibility.VISIBLE;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class DataMB implements Serializable {

    //  @EJB
    //  private heps.db.param_list.session.DataFacade ejbFacade;
    private DataDispFacade ejbFacade;
    private List<DataDisp> dataDispList;
    private List<Boolean> stateList;
    private Parameter selectedParameter;
    private DataDisp select;

    public DataDisp getSelect() {
        return select;
    }

    public void setSelect(DataDisp select) {
        this.select = select;
    }
    

    public DataMB() {
        ejbFacade = new DataDispFacade();
        dataDispList = ejbFacade.getDataDispList();
        this.selectedParameter = new Parameter();
        this.select=new DataDisp();
    }

    @PostConstruct
    public void init() {
        // dataDispList = null;     
        stateList = Arrays.asList(false, true, true, true, true, true, true, true, true, true, true, true, true, true, true);
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
        String oldValue = e.getOldValue().toString();
        if (!oldValue.equals(dataDisp.getValue())) {
            ejbFacade.edit(dataDisp, oldValue);
            this.dataDispList = ejbFacade.getDataDispList();
        }
    }
    
    public void add(){
        System.out.println("add........");
        this.ejbFacade.add(select, selectedParameter);
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
}
