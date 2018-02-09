/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.ejb;

import heps.db.param_list.entity.Attribute;
import heps.db.param_list.entity.Data;
import heps.db.param_list.entity.Devicetype;
import heps.db.param_list.entity.Parameter;
import heps.db.param_list.entity.Subsystem;
import heps.db.param_list.entity.Team;
import heps.db.param_list.entity.Version;
import heps.db.param_list.tools.EmProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author Lvhuihui
 */
@Stateless
public class DataFacade {

    /*@PersistenceUnit
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("param_listPU");
    public static EntityManager em = emf.createEntityManager();

    @PersistenceContext*/
    public static EntityManager em=EmProvider.getInstance().getEntityManagerFactory().createEntityManager();

    public void setData(String value, Team team, heps.db.param_list.entity.System system, Subsystem subsystem, Devicetype devicetype, Date date_modified, Attribute attribute,
             Parameter parameter, String comment, String status, Version version) {
        Data d = new Data();
        d.setValue(value);
        d.setTeamid(team);
        d.setSystemid(system);
        d.setSubsystemid(subsystem);
        d.setDevicetypeId(devicetype);
        d.setDatemodified(date_modified);
        d.setAttributeid(attribute);
        d.setParameterid(parameter);
        d.setComment(comment);
        d.setStatus(status);
        d.setVersionid(version);
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
    }

   
}
