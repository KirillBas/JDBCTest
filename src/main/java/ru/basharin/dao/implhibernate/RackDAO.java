package ru.basharin.dao.implhibernate;

import org.hibernate.Session;
import ru.basharin.model.Rack;
import ru.basharin.model.Storage;

import java.util.List;

/**
 * Created by drbah on 06.06.2017.
 */
public class RackDAO {
    private final Session session;

    public RackDAO(Session session) {
        this.session = session;
    }

    public void create(Rack rack) throws Exception {
        session.getTransaction().begin();
        session.save(rack);
        session.getTransaction().commit();
    }

    public void delete(Rack rack) {
        session.getTransaction().begin();
        session.createQuery("delete from Rack rack where rack.number = :number").setParameter("number", rack.getNumber()).executeUpdate();
        session.getTransaction().commit();
    }

    public void update(Rack rack) {
        session.beginTransaction();
        session.save(rack);
        session.getTransaction().commit();
    }

    public Rack read(int number) {
        List<Rack> racks = session.createQuery("select rack from Rack rack where rack.number = :number").setParameter("number", number).list();
        if (!racks.isEmpty()) {
            return racks.get(0);
        }
        return null;
    }

    public List<Rack> readAll() {
        return session.createQuery("select rack from Rack rack").list();
    }
}
