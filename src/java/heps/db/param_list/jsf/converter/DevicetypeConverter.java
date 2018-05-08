/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.converter;

import heps.db.param_list.db.ejb.DevicetypeFacade;
import heps.db.param_list.db.ejb.SystemFacade;
import heps.db.param_list.db.entity.Devicetype;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Lvhuihui
 */
@FacesConverter("devicetypeConverter")
public class DevicetypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return new DevicetypeFacade().getDevicetype(string);
        }

    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || o.equals("")) {
            return "";
        } else {
            return ((Devicetype) o).getName();
        }

    }
}
