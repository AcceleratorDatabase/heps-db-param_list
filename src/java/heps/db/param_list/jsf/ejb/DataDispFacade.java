/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.ejb;

import heps.db.param_list.ejb.AttributeFacade;
import heps.db.param_list.entity.Data;
import heps.db.param_list.jsf.entity.DataDisp;
import heps.db.param_list.tools.ConstantClassField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import heps.db.param_list.ejb.DataFacade;
import heps.db.param_list.ejb.DevicetypeFacade;
import heps.db.param_list.ejb.HistoryDataFacade;
import heps.db.param_list.ejb.ParameterFacade;
import heps.db.param_list.ejb.SubsystemFacade;
import heps.db.param_list.ejb.SystemFacade;
import heps.db.param_list.entity.HistoryData;
import heps.db.param_list.entity.Parameter;
import heps.db.param_list.servletContextListener.EMFServletContextListener;
import heps.db.param_list.tools.EmProvider;
import java.util.Date;


/**
 *
 * @author Lvhuihui
 */
@Stateless
public class DataDispFacade {

    //@PersistenceUnit
   // public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("param_listPU");
 //   public static EntityManager em = emf.createEntityManager();

    //@PersistenceContext
    public static EntityManager em=EmProvider.getInstance().getEntityManagerFactory().createEntityManager();

    public List<DataDisp> getDataDispList() {
        //System.out.println(em.getEntityManagerFactory().getProperties());
        Query q;
        q = em.createNamedQuery("Data.findAll");
        List dataList = q.getResultList();
        List<DataDisp> dispList = new ArrayList();
        System.out.println("++++"+dataList.size());
        if (dataList.isEmpty() || dataList == null) {
            return null;
        } else {
            Iterator<Data> it = dataList.iterator();
            while (it.hasNext()) {
                Data data=it.next();
                DataDisp dp = new DataDisp();              
                if (data.getSystemid() != null) {
                    dp.setSystem(data.getSystemid().getName());
                }
                if (data.getSubsystemid() != null) {
                    dp.setSubsystem(data.getSubsystemid().getName());
                }
                if (data.getDevicetypeId() != null) {
                    dp.setDevice(data.getDevicetypeId().getName());
                }
                if (data.getParameterid() != null) {
                    dp.setParameterName(data.getParameterid().getName());
                    dp.setDefinition(data.getParameterid().getDefinition());
                    if (data.getParameterid().getUnitid() != null) {
                        dp.setUnit(data.getParameterid().getUnitid().getName());
                    }
                    if (data.getParameterid().getReferenceid() != null) {
                        dp.setReferenceTitle(data.getParameterid().getReferenceid().getTitle());
                        dp.setReferenceAuthor(data.getParameterid().getReferenceid().getAuthor());
                        dp.setReferencePublication(data.getParameterid().getReferenceid().getPublication());
                        dp.setReferenceURL(data.getParameterid().getReferenceid().getUrl());
                        dp.setKeyword(data.getParameterid().getReferenceid().getKeywords());
                    }
                }
                if (data.getAttributeid() != null) {
                    dp.setAttibute(data.getAttributeid().getName());
                }
                
                if(data.getDatemodified()!=null){
                    dp.setChangeDate(data.getDatemodified());
                }

                dp.setValue(data.getValue());
                dp.setData(data);
                dp.setId(data.getId().longValue());                           
                dispList.add(dp);
            }
            return dispList;
        }
    }
// dataDisp is the modified value
    public void edit(DataDisp dataDisp,String oldValue){ 
       Data data=em.find(Data.class, dataDisp.getId().intValue());         
       data.setValue(dataDisp.getValue()); 
       new DataFacade().updateData(data);  
       new HistoryDataFacade().setHistoryData(data, oldValue, new Date());         
    }
    
    public void add(DataDisp dataDisp,Parameter parameter){
       Data data=new Data();
       data.setSystemid(new SystemFacade().getSystem(dataDisp.getSystem()));
       data.setSubsystemid(new SubsystemFacade().getSubsystem(dataDisp.getSubsystem()));
       data.setDevicetypeId(new DevicetypeFacade().getDevicetype(dataDisp.getDevice()));
       data.setValue(dataDisp.getValue());
       data.setDatemodified(new Date());
       data.setAttributeid(new AttributeFacade().getAttribute(dataDisp.getAttibute()));
       data.setParameterid(parameter);
       em.getTransaction().begin();
       em.persist(data);
       em.getTransaction().commit();
    }
}
