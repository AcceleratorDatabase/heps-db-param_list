/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.ejb;

import static heps.db.param_list.ejb.DataFacade.em;
import heps.db.param_list.entity.Data;
import heps.db.param_list.entity.HistoryData;
import static heps.db.param_list.jsf.ejb.DataDispFacade.em;
import heps.db.param_list.tools.EmProvider;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Lvhuihui
 */
@Stateless
public class HistoryDataFacade {

    public static EntityManager em = EmProvider.getInstance().getEntityManagerFactory().createEntityManager();

    public void setHistoryData(Data dataId, String value, Date date_modified) {

        HistoryData historyData = new HistoryData();
        historyData.setDataId(dataId);
        historyData.setValue(value);
        historyData.setDateModified(date_modified);
        em.getTransaction().begin();
        em.persist(historyData);
        em.getTransaction().commit();
    }
    
    public List<HistoryData> getHistoryData(){     
        Query q;
        q = em.createNamedQuery("HistoryData.findAll");
        return q.getResultList();
    }
}
