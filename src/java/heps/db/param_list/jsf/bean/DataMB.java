/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.bean;

import heps.db.param_list.entity.Data;
import heps.db.param_list.ejb.DataFacade;
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

    public DataMB() {
        ejbFacade = new DataDispFacade();
    }

    @PostConstruct
    public void init() {
        dataDispList = null;
        stateList = Arrays.asList(false, true, true, true, true, true, true, true, true, true, true, true, true, true, true);
    }

    public List<DataDisp> getDataDispList() {
        dataDispList = ejbFacade.getDataDispList();
        return dataDispList;
    }

    public List<Boolean> getStateList() {
        return this.stateList;
    }

    public void onToggle(ToggleEvent e) {
        stateList.set((int) e.getData(), e.getVisibility() == e.getVisibility().VISIBLE);
    }

}
