/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.bean;

import heps.db.param_list.db.ejb.ParameterFacade;
import heps.db.param_list.db.entity.Parameter;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ViewScoped
public class ParameterMB implements Serializable {

    private List<Parameter> parameterList;
    private ParameterFacade ejbFacade;
    

    public ParameterMB() {
        ejbFacade = new ParameterFacade();
        parameterList = ejbFacade.getAllParameter();
      
    }

    public List<Parameter> getParameterList() {
        return this.parameterList;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }

   
}
