package prog.util.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBWorker {

    private static SessionFactory sessionFactory;

    static {

        try {

            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Throwable eexeption) {
            throw new ExceptionInInitializerError(eexeption);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}