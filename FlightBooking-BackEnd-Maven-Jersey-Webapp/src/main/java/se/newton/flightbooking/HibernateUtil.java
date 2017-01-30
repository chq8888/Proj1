package se.newton.flightbooking;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory SESSIONFACTORY;
    private static Session SESSION;

    // static class initializer
    static {
        try {

            // Create the SessionFactory from standard (hibernate.cfg.xml)
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SESSIONFACTORY = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {

            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSIONFACTORY;
    }

    public static void openSession() {
        if (SESSION == null)    
            SESSION = SESSIONFACTORY.openSession();
    }

    public static Session getSession() {
        openSession();
        return SESSION;
    }

    public static void closeSession() {

        if (SESSION != null)    
            SESSION.close();

        SESSION = null;
    }
}
