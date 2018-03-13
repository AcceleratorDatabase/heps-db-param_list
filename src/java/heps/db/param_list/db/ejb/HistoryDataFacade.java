/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.ejb;

import static heps.db.param_list.db.ejb.DataFacade.em;
import heps.db.param_list.db.entity.Data;
import heps.db.param_list.db.entity.HistoryData;
import static heps.db.param_list.jsf.ejb.DataDispFacade.em;
import heps.db.param_list.comman.tools.EmProvider;
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
        dataId.getHistoryDataList().add(historyData);
        dataId.setHistoryDataList(dataId.getHistoryDataList());
        System.out.println("++++++"+historyData);
        em.getTransaction().begin();
        em.persist(historyData); 
        em.flush();
        em.getTransaction().commit();
    }
    public List<HistoryData> getHistoryData(){     
        Query q;
        q = em.createNamedQuery("HistoryData.findAll");
        return q.getResultList();
    }
}
