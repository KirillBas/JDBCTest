package ru.basharin.api;

import com.google.gson.Gson;
import ru.basharin.dao.DAOFactory;
import ru.basharin.dao.implhibernate.StorageDAO;
import ru.basharin.model.Storage;
import ru.basharin.model.StorageType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageServlet extends HttpServlet {

    private StorageDAO storageDAO = DAOFactory.createStorageDAO();
    private Gson gson = new Gson();

    public static class StorageDTO {
        final int number;
        final String adress;
        final StorageType storageType;

        public StorageDTO(int number, String adress, StorageType storageType) {
            this.number = number;
            this.adress = adress;
            this.storageType = storageType;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        if (number == null) {
            List<StorageDTO> storages = new ArrayList<>();
            for (Storage storage : storageDAO.readAll()) {
                storages.add(new StorageDTO(storage.getNumber(), storage.getAdress(), storage.getStorageType()));
            }
            resp.getWriter().write(gson.toJson(storages));
        } else {
            Storage storage = storageDAO.read(Integer.parseInt(number));
            resp.getWriter().write(gson.toJson(new StorageDTO(storage.getNumber(), storage.getAdress(), storage.getStorageType())));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StorageDTO storage = gson.fromJson(req.getReader(), StorageDTO.class);
        storageDAO.create(new Storage(storage.storageType, storage.adress, storage.number));
        resp.getWriter().write(gson.toJson(storage));

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StorageDTO storage = gson.fromJson(req.getReader(), StorageDTO.class);
        storageDAO.delete(storage.number);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StorageDTO storage = gson.fromJson(req.getReader(), StorageDTO.class);
        storageDAO.update(new Storage(storage.storageType, storage.adress, storage.number));
        resp.getWriter().write(gson.toJson(storage));
    }
}
