/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.ejb;

import heps.db.param_list.entity.Devicetype;
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
public class DevicetypeFacade {

    @PersistenceUnit
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("param_listPU");
    static EntityManager em = emf.createEntityManager();

    @PersistenceContext
    
       public void setDevicetype(String name) {
        Devicetype d=new Devicetype();
        d.setName(name);
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
    }

    public Devicetype getDevicetype(String name) {
        Query q;
        q = em.createNamedQuery("Devicetype.findByName").setParameter("name", name);
        List<Devicetype> l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            return l.get(0);
        }
    }
}
