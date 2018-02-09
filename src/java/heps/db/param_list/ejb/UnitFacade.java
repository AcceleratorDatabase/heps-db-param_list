/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.ejb;


import heps.db.param_list.entity.Attribute;
import heps.db.param_list.entity.Unit;
import heps.db.param_list.tools.EmProvider;
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
public class UnitFacade {

    /*@PersistenceUnit
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("param_listPU");
    static EntityManager em = emf.createEntityManager();

    @PersistenceContext*/
     public static EntityManager em=EmProvider.getInstance().getEntityManagerFactory().createEntityManager();
    
     public void setUnit(String name){
        Unit a=new Unit();
        a.setName(name);
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

    public Unit getUnit(String name) {
        Query q;
        q = em.createNamedQuery("Unit.findByName").setParameter("name", name);
        List<Unit> l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            return l.get(0);
        }
    }
    
}
