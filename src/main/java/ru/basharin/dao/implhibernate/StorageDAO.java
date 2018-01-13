package ru.basharin.dao.implhibernate;

import org.hibernate.Session;
import ru.basharin.model.Storage;

import java.util.List;

/**
 * Created by drbah on 07.06.2017.
 */
public class StorageDAO {
    private final Session session;

    public StorageDAO(Session session) {
        this.session = session;
    }

    public void create(Storage storage) {
        session.getTransaction().begin();
        session.save(storage);
        session.getTransaction().commit();
    }

//    нет проверки на передачу пустого параметра, удаляет null
    public void delete(int number) {
        session.getTransaction().begin();
        session.createQuery("delete from Storage storage where storage.number = :number").setParameter("number", number).executeUpdate();
        session.getTransaction().commit();
    }

    public void update(Storage storage) {
//        проблема была в session.beginTransaction().begin;
        session.beginTransaction();
        session.save(storage);
        session.getTransaction().commit();
    }

    public Storage read(int number) {
        List<Storage> storages = session.createQuery("select storage from Storage storage where storage.number = :number")
                .setParameter("number", number).list();
        if (!storages.isEmpty()) {
            return storages.get(0);
        }
        return null;
    }

    public List<Storage> readAll() {
        return session.createQuery("select storage from Storage storage").list();
    }
}
