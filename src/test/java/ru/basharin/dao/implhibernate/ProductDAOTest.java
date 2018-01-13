package ru.basharin.dao.implhibernate;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import ru.basharin.model.*;

import java.util.Arrays;
import java.util.List;

public class ProductDAOTest {
    private static final Storage STORAGE = new Storage(StorageType.CHEMICAL, "Moscow", 1);
    private static final Rack RACK = new Rack(1, STORAGE);
    private static final Product ORANGE = new Product("Orange", 100);
    private static final Product APPLE = new Product("Apple", 50);
    private static final Product CARROT = new Product("Carrot", 15);

    private static Session session;
    private static ProductDAO productDAO;
    private static RackDAO rackDAO;
    private static StorageDAO storageDAO;
    private static ProductCountDAO productCountDAO;

    @BeforeClass
    public static void setUpClass() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        session = configuration.buildSessionFactory(ssrb.build()).openSession();
        productDAO = new ProductDAO(session);
        rackDAO = new RackDAO(session);
        storageDAO = new StorageDAO(session);
        productCountDAO = new ProductCountDAO(session);
    }

    @Before
    public void setUpMethod() throws Exception {
        storageDAO.create(STORAGE);
        productDAO.create(ORANGE);
        productDAO.create(APPLE);
        productDAO.create(CARROT);
        rackDAO.create(RACK);
        productCountDAO.addProductToRack(ORANGE, RACK, 1000);
        productCountDAO.addProductToRack(APPLE, RACK, 1212);
        productCountDAO.addProductToRack(CARROT, RACK, 300);
    }

    @After
    public void tearDown() throws Exception {
        session.getTransaction().begin();
        session.createQuery("delete from " + ProductCount.class.getName()).executeUpdate();
        session.createQuery("delete from " + Product.class.getName()).executeUpdate();
        session.createQuery("delete from " + Rack.class.getName()).executeUpdate();
        session.createQuery("delete from " + Storage.class.getName()).executeUpdate();
        session.getTransaction().commit();
    }

    @Test
    public void testCreate() throws Exception {
        Product product = new Product("Banan", 200);
        productDAO.create(product);
        Assert.assertEquals(product, productDAO.readProduct("Banan"));
    }

    // TODO: 08.11.2017 не работает если не закоментирована стр. 49
    @Test
    public void testDelete() throws Exception {
        productDAO.delete(ORANGE);
        Assert.assertEquals(null, productDAO.readProduct("Orange"));
    }

    @Test
    public void testReadProduct() throws Exception {
        for (ReadProductTestCase testCase: createTestCases()) {
            Assert.assertEquals(testCase.message, testCase.expectedValue, productDAO.readProduct(testCase.productName, testCase.number));
        }
    }

    @Test
    public void testUpdate() throws Exception {
        Product product = new Product("Banan", 200);
        product.setCoast(300);
        productDAO.update(product);
        Assert.assertEquals(product, productDAO.readProduct("Banan"));
    }

    private List<ReadProductTestCase> createTestCases() {
        return Arrays.asList(
                new ReadProductTestCase("Orange", 1, Arrays.asList(ORANGE), "Expected one product Orange"),
                new ReadProductTestCase(null, null, Arrays.asList(ORANGE, APPLE, CARROT), "Expected all products"),
                new ReadProductTestCase("Orange", null, Arrays.asList(ORANGE), "Expected all products with name Orange")
        );
    }

    private static class ReadProductTestCase {
        private String productName;
        private Integer number;
        private List<Product> expectedValue;
        private String message;

        public ReadProductTestCase(String productName, Integer number, List<Product> expectedValue, String message) {
            this.productName = productName;
            this.number = number;
            this.expectedValue = expectedValue;
            this.message = message;
        }
    }
}