/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.ejb;

import heps.db.param_list.entity.Subsystem;
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
public class SubsystemFacade{

    @PersistenceUnit
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("param_listPU");
    static EntityManager em = emf.createEntityManager();

    @PersistenceContext
    
      public void setSubsystem(String name) {
        Subsystem s=new Subsystem();
        s.setName(name);
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }

    public Subsystem getSubsystem(String name) {
        Query q;
        q = em.createNamedQuery("Subsystem.findByName").setParameter("name", name);
        List<Subsystem> l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            return l.get(0);
        }
    }
    
}
