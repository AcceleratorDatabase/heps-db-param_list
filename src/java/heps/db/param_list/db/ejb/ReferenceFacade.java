/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.db.ejb;


import heps.db.param_list.db.entity.Reference;
import heps.db.param_list.db.entity.Unit;
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
public class ReferenceFacade{

    /*@PersistenceUnit
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("param_listPU");
    static EntityManager em = emf.createEntityManager();

    @PersistenceContext*/
    public static EntityManager em=EmProvider.getInstance().getEntityManagerFactory().createEntityManager();

    public void setReference(String title, String author, String publication, String url, String keywords) {
        Reference r = new Reference();
        r.setTitle(title);
        r.setAuthor(author);
        r.setPublication(publication);
        r.setUrl(url);
        r.setKeywords(keywords);

        em.getTransaction().begin();
        em.persist(r);
        em.getTransaction().commit();
    }

    public Reference getReferenceByTitle(String title) {
        if(title==null||("".equals(title))) return null;
        Query q;
        q = em.createNamedQuery("Reference.findByTitle").setParameter("title", title);
        List<Reference> l = q.getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            return l.get(0);
        }
    }

}
