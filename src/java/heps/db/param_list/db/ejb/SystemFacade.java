/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.ejb;

import heps.db.param_list.db.entity.System;
import heps.db.param_list.comman.tools.EmProvider;
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
public class SystemFacade {

    /*@PersistenceUnit
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("param_listPU");
    static EntityManager em = emf.createEntityManager();

    @PersistenceContext*/
    public static EntityManager em=EmProvider.getInstance().getEntityManagerFactory().createEntityManager();

    public void setSystem(String name) {
        System s = new System();
        s.setName(name);
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }

    public System getSystem(String name) {
        Query q;
        q = em.createNamedQuery("System.findByName").setParameter("name", name);      
        List<System> l = q.getResultList();
        
        if (l.isEmpty()) {
            return null;
        } else {
            return l.get(0);
        }
    }
    
    public List<System> getAllSystem(){
        Query q;
        q=em.createNamedQuery("System.findAll");
        return  q.getResultList();
    
    }
}
