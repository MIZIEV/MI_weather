package prog.util.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import prog.models.Measurements;

public class DBConnector {

    private static final DBConnector instance = new DBConnector();
    private static SessionFactory sessionFactory;

    private DBConnector() {
        try {
            Configuration configuration = new Configuration().addAnnotatedClass(Measurements.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable eexeption) {
            throw new ExceptionInInitializerError(eexeption);
        }
    }

    public static DBConnector getInstance(){
        return instance;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}