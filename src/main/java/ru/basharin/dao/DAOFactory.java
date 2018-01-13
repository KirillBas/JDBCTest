package ru.basharin.dao;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.basharin.dao.implhibernate.ProductDAO;
import ru.basharin.dao.implhibernate.RackDAO;
import ru.basharin.dao.implhibernate.StorageDAO;

public class DAOFactory {
    private static Session session;
    static {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        session = configuration.buildSessionFactory(ssrb.build()).openSession();
    }

    public static StorageDAO createStorageDAO() {
        return new StorageDAO(session);
    }

    public static RackDAO createRackDAO() {
        return new RackDAO(session);
    }

    public static ProductDAO createProductDAO() {
        return new ProductDAO(session);
    }
}
