/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.ejb;

import heps.db.param_list.db.entity.Manager;
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
public class ManagerFacade {

    /* @PersistenceUnit
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("param_listPU");
    static EntityManager em = emf.createEntityManager();

    @PersistenceContext*/
    public static EntityManager em = EmProvider.getInstance().getEntityManagerFactory().createEntityManager();

    public Manager getManager(String name) {
        Query q;
        q = em.createNamedQuery("Manager.findByName").setParameter("name", name);
        List<Manager> l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            return l.get(0);
        }
    }

    public Boolean validate(Manager manager) {
        if (manager != null) {
            Manager inDatabase = this.getManager(manager.getName());
            if (inDatabase != null) {
                if (inDatabase.getPassword().equals(manager.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }
}
