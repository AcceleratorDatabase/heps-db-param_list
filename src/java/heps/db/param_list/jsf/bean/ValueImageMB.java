/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.bean;

import heps.db.param_list.db.ejb.DataFacade;
import heps.db.param_list.db.entity.Data;
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

    public void putImage(Data data) {
        System.out.println("putImage..........");
        if (data != null) {            
            if (data.getImage() != null) {
                image = new DefaultStreamedContent(new ByteArrayInputStream(data.getImage()));
            }
        }
    }

    public StreamedContent getImage() {
        return this.image;
    }
}
