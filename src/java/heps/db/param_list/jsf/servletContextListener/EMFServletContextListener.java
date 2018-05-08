/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.jsf.servletContextListener;


import heps.db.param_list.comman.tools.EmProvider;
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
