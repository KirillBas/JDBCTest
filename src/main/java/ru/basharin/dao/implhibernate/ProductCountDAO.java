package ru.basharin.dao.implhibernate;

import org.hibernate.Session;
import ru.basharin.model.Product;
import ru.basharin.model.ProductCount;
import ru.basharin.model.Rack;

public class ProductCountDAO {
    private final Session session;

    public ProductCountDAO(Session session) {
        this.session = session;
    }

    // TODO: 06.11.2017 написать сервлет и дописать методы для класса
    public ProductCount addProductToRack(Product product, Rack rack, int count) {
        ProductCount productCount = new ProductCount(product, rack, count);
        session.getTransaction().begin();
        session.save(productCount);
        session.getTransaction().commit();
        return productCount;
    }
}
