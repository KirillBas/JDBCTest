package ru.basharin.dao.implhibernate;

import org.hibernate.Session;
import ru.basharin.model.Product;

import java.util.List;

/**
 * Created by drbah on 02.06.2017.
 */
public class ProductDAO {
    private final Session session;

    public ProductDAO(Session session) {
        this.session = session;
    }

    public Product create(Product product) {
        session.getTransaction().begin();
        session.save(product);
        session.getTransaction().commit();
        return product;
    }

    public void delete(Product product) {
        product.setDeleted(true);
        update(product);
    }

    public List<Product> readProduct(String name, Integer number) {
        if (name == null && number == null) {
            return session.createQuery("from Product product where deleted = false").list();
        } else if (name == null) {
            return session.createQuery("select productCount.product from ProductCount productCount " +
                    "where productCount.rack.number = :number and productCount.product.deleted")
                    .setParameter("number", number).list();
        } else if (number == null) {
            return session.createQuery("from Product where name like :name and deleted = false").setParameter("name", "%" + name + "%").list();
        } else {
            return session.createQuery("select productCount.product from ProductCount productCount " +
                    "where productCount.product.name like :name " +
                    "and productCount.rack.number = :number and productCount.product.deleted = false")
                    .setParameter("name", "%" + name + "%")
                    .setParameter("number", number).list();
        }
    }

    public Product readProduct(String name) {
        List<Product> product = session.createQuery("select product from Product product where product.name = :name and deleted = false")
                .setParameter("name", name).list();
        if (!product.isEmpty()) {
            return product.get(0);
        }
        return null;
    }

    public void update(Product product) {
//        Product temp = product;
        session.beginTransaction();
        session.save(product);
//        if (!session.contains(product)) {
//            temp = (Product) session.byId(Product.class).load(product.getId());
//            temp.setName(product.getName());
//            temp.setCoast(product.getCoast());
//        }
//        session.update(temp);
        session.getTransaction().commit();
    }
}
