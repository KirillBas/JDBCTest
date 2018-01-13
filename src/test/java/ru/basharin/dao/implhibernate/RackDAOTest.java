package ru.basharin.dao.implhibernate;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.basharin.model.Rack;
import ru.basharin.model.Storage;
import ru.basharin.model.StorageType;

import java.util.Arrays;

public class RackDAOTest {
    private static final Storage STORAGE = new Storage(StorageType.CHEMICAL, "Moscow", 1);
    private static final Storage STORAGE_2 = new Storage(StorageType.CHEMICAL, "Mascow", 2);
    private static final Rack RACK_1 = new Rack(1, STORAGE);
    private static final Rack RACK_2 = new Rack(2, STORAGE);
    private static final Rack RACK_3 = new Rack(3, STORAGE);

    private Session session;
    private RackDAO rackDAO;
    private StorageDAO storageDAO;

    public RackDAOTest() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        session = configuration.buildSessionFactory(ssrb.build()).openSession();
        rackDAO = new RackDAO(session);
        storageDAO = new StorageDAO(session);
    }

    @Before
    public void setUpMethod() throws Exception {
        storageDAO.create(STORAGE);
        storageDAO.create(STORAGE_2);
        rackDAO.create(RACK_1);
        rackDAO.create(RACK_2);
        rackDAO.create(RACK_3);
    }

    @Test
    public void create() throws Exception {
        Rack rack = new Rack(4, STORAGE);
        rackDAO.create(rack);
        Assert.assertEquals(rack, rackDAO.read(4));
    }

    @Test
    public void delete() throws Exception {
        rackDAO.delete(RACK_1);
        Assert.assertEquals(null, rackDAO.read(1));
    }

    @Test
    public void update() throws Exception {
        Rack rack = new Rack(4, STORAGE);
        rack.setStorage(STORAGE_2);
        rackDAO.update(rack);
        System.out.println(rackDAO.read(4));
        Assert.assertEquals(rack, rackDAO.read(4));
    }

    @Test
    public void read() throws Exception {
        Assert.assertEquals(RACK_1, rackDAO.read(1));
    }

    @Test
    public void readAll() throws Exception {
        Assert.assertEquals(Arrays.asList(RACK_1,RACK_2,RACK_3), rackDAO.readAll());
    }

}