package org.fyp.ems.questionSettingModule.Security;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class EMSUtils {
    private static SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
    	 if (sessionFactory == null) {
             // loads configuration and mappings
             Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
             ServiceRegistry serviceRegistry
                 = new StandardServiceRegistryBuilder()
                     .applySettings(configuration.getProperties()).build();

             // builds a session factory from the service registry
             sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
         }
		return sessionFactory;
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    
    }
}
