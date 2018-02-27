/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.ejb;

import static heps.db.param_list.ejb.DataFacade.em;
import heps.db.param_list.entity.Data;
import heps.db.param_list.entity.HistoryData;
import heps.db.param_list.tools.EmProvider;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

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
}
