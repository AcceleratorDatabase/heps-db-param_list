/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.servletContextListener;


import heps.db.param_list.db.ejb.DataFacade;
import heps.db.param_list.jsf.ejb.DataDispFacade;
import heps.db.param_list.comman.tools.EmProvider;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Lvhuihui
 */
@WebListener
public class EMFServletContextListener implements ServletContextListener {
    
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("INITIALIZED");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("DESTROYED");
        if( EmProvider.getInstance().getEntityManagerFactory().isOpen())
            EmProvider.getInstance().getEntityManagerFactory().close();
    }
    
}
