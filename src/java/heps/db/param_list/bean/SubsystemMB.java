/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.bean;

import heps.db.param_list.ejb.SubsystemFacade;
import heps.db.param_list.entity.Subsystem;
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
public class SubsystemMB implements Serializable{

    private List<Subsystem> subsystemList;
    private SubsystemFacade ejbFacade;

    public SubsystemMB() {
        ejbFacade = new SubsystemFacade();
        subsystemList = ejbFacade.getAllSubsystem();
    }

    public List<Subsystem> getSubsystemList() {
        return this.subsystemList;
    }

    public void setSubsystemList(List<Subsystem> subsystemList) {
        this.subsystemList = subsystemList;
    }
}
