/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.bean;

import heps.db.param_list.db.ejb.DevicetypeFacade;
import heps.db.param_list.db.entity.Devicetype;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class DevicetypeMB implements Serializable{

    /**
     *  private List<System> systemList;
    private SystemFacade ejbFacade;
    
    public SystemMB() {
        ejbFacade=new SystemFacade();
        systemList=ejbFacade.getAllSystem();
    }
     public List<System> getSystemList() {      
        return systemList;
    }

    public void setSystemList(List<System> systemList) {
        this.systemList = systemList;
    }
     */
    private List<Devicetype> devicetypeList;
    private DevicetypeFacade ejbFacade;
    
    public DevicetypeMB() {
        ejbFacade=new DevicetypeFacade();
        this.devicetypeList=ejbFacade.getAllDeviceType();
    }
    
    public List<Devicetype> getDevicetypeList() {      
        return this.devicetypeList;
    }
    
     public void setDevicetypeList(List<Devicetype> devicetypeList) {
        this.devicetypeList=devicetypeList;
    }
       
}
