/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.bean;

import heps.db.param_list.db.ejb.AttributeFacade;
import heps.db.param_list.db.entity.Attribute;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class AttributeMB implements Serializable{

    private List<Attribute> attributeList;
    private AttributeFacade ejbFacade;
    
    public AttributeMB() {
        ejbFacade=new AttributeFacade();
        attributeList=ejbFacade.getAllAttribute();
    }
     public List<Attribute> getAttributeList() {      
        return this.attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }
}
