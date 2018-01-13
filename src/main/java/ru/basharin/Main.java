package ru.basharin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.basharin.dao.implhibernate.ProductDAO;
import ru.basharin.dao.implhibernate.RackDAO;
import ru.basharin.dao.implhibernate.StorageDAO;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//import ru.basharin.CmdApi.SearchWord;
//import ru.basharin.api.CmdApi;

/**
 * Created by drbah on 07.04.2017.
 */
public class Main {
    public static void main(String[] args) {
//        DAOFactory.createStorageDAO().readAll();
//        Calendar calendar = new GregorianCalendar(1970, 0, 12);
//        calendar.getTime();
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
//        System.out.println(dateFormat.format(calendar.getTime()));

        LocalDate localDate = LocalDate.of(2016,1,1);
//        LocalDateTime localDateTime = LocalDateTime.of(2017,12,26,23,59,30);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        while (!localDate.equals(LocalDate.of(2016,12,31))) {
            localDate = localDate.plusDays(1);
            if (localDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
                System.out.println(localDate);
            }
        }

//        String date = "2017-дек-26";
//        System.out.println(LocalDate.parse(date, dateTimeFormatter).format(DateTimeFormatter.ISO_DATE));

    }

    public static void main1(String[] args) throws IOException, InterruptedException {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
        Session session = sessionFactory.openSession();
        Scanner scanner = new Scanner(System.in);
        ProductDAO productDAO = new ProductDAO(session);
        RackDAO rackDAO = new RackDAO(session);
        StorageDAO storageDAO = new StorageDAO(session);
//        CmdApi cmdApi = new CmdApi(scanner , productDAO, rackDAO, storageDAO, session);
//        FullTextSession fullTextSession = Search.getFullTextSession(session);
//        fullTextSession.createIndexer().startAndWait();
//        SearchWord searchWord = new SearchWord(session);
//        product.setCoast(200);
//        product.setId(4L);
//        Rack rack = new Rack();
//        rack.setId(2);
        try {
//            searchWord.searchWordInFild();
//            cmdApi.printGreatingMenu();
//            productDAO.create(product);
//            productDAO.delete(product);
//            System.out.println(productDAO.readProduct("apple"));
//            Rack rack = rackDAO.readProduct(3);
//            rack.getProducts().add(new ru.basharin.modelhibernate.Product("aval", 300));
//            rackDAO.update(rack);
//            System.out.println(rackDAO.readProduct(2));
//            System.out.println(productDAO.readProduct("ananas"));
//            System.out.println(storageDAO.readAll());
//            System.out.println(productDAO.readProductsByRack(rack));
//            System.out.println(session.byId(Product.class).load(4L));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/LearnJava", "postgres", "qwerty123")) {
//            try {
//                Scanner scanner = new Scanner(System.in);
//                ProductDAO productDAO = new ProductSQLDAO(connection);
//                RackDAO rackDAO = new RackSQLDAO(connection);
//                StorageDAO storageDAO = new StorageSQLDAO(connection);
//                CmdApi cmdApi = new CmdApi(scanner, productDAO, rackDAO, storageDAO, connection);
////                System.out.println(productDAO.readProduct("banan"));
//                cmdApi.printGreatingMenu();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
