/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.bean;

import heps.db.param_list.db.ejb.DataFacade;
import heps.db.param_list.db.entity.Data;
import heps.db.param_list.jsf.entity.DataDisp;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Lvhuihui
 */
@ManagedBean
@ApplicationScoped
public class ValueImageMB implements Serializable {

    private DataFacade ejbFacade;
    private StreamedContent image = null;

    public ValueImageMB() {
        ejbFacade = new DataFacade();
    }

    public void putImage(DataDisp dataDisp) {
        if (dataDisp != null) {
            int dataId = dataDisp.getId().intValue();
            Data data = ejbFacade.find(dataId);
            if (data.getImage() != null) {
                image = new DefaultStreamedContent(new ByteArrayInputStream(data.getImage()));
            }

        }
    }

    public StreamedContent getImage() {
        return this.image;
        /*FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String dataId = context.getExternalContext().getRequestParameterMap().get("dataId");
            System.out.println("***********"+dataId);
            Data data = null;
            if (dataId != null && (!"".equals(dataId))) {
                data = ejbFacade.find(Integer.parseInt(dataId));
                if(data.getImage()!=null)
                    return new DefaultStreamedContent(new ByteArrayInputStream(data.getImage()));
            }

        }
        return null;*/
    }
}
