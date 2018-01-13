package ru.basharin.dao.implhibernate;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.basharin.model.Storage;
import ru.basharin.model.StorageType;

import java.util.Arrays;

public class StorageDAOTest {
    private static final Storage STORAGE_1 = new Storage(StorageType.CHEMICAL, "Moscow", 1);
    private static final Storage STORAGE_2 = new Storage(StorageType.GROCERY, "Piter", 2);
    private static final Storage STORAGE_3 = new Storage(StorageType.TECHNICAL, "Orel", 3);
    private static final Storage STORAGE_4 = new Storage(StorageType.GROCERY, "Tambov", 4);
    private static final Storage STORAGE_5 = new Storage(StorageType.CHEMICAL, "Bratsk", 5);

    private Session session;
    private StorageDAO storageDAO;

    public StorageDAOTest() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        session = configuration.buildSessionFactory(ssrb.build()).openSession();
        storageDAO = new StorageDAO(session);
    }

    @Before
    public void setUpMethod() throws Exception {
        storageDAO.create(STORAGE_1);
        storageDAO.create(STORAGE_2);
        storageDAO.create(STORAGE_3);
        storageDAO.create(STORAGE_4);
        storageDAO.create(STORAGE_5);
    }

    @Test
    public void testCreate() throws Exception {
        Storage storage = new Storage(StorageType.CHEMICAL, "Novosib", 6);
        storageDAO.create(storage);
        Assert.assertEquals(storage, storageDAO.read(6));
    }

    @Test
    public void testDelete() throws Exception {
        System.out.println("Storage № " + storageDAO.read(2) + " deleted!");
        storageDAO.delete(2);
        Assert.assertEquals(null, storageDAO.read(2));
    }

    @Test
    public void update() throws Exception {
        Storage storage = new Storage(StorageType.CHEMICAL, "Moscow", 7);
        storage.setAdress("Mascow");
        storage.setStorageType(StorageType.GROCERY);
        storageDAO.update(storage);
        Assert.assertEquals(storage, storageDAO.read(7));
    }


    @Test
    public void testRead() throws Exception {
        Assert.assertEquals(STORAGE_1, storageDAO.read(1));
    }

    @Test
    public void testReadAll() throws Exception {
        Assert.assertEquals(Arrays.asList(STORAGE_1,STORAGE_2,STORAGE_3,STORAGE_4,STORAGE_5), storageDAO.readAll());
    }

}