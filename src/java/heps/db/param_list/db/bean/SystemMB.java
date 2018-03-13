/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.bean;

import heps.db.param_list.db.ejb.SystemFacade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import heps.db.param_list.db.entity.System;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class SystemMB implements Serializable {

    private List<System> systemList;
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
}
