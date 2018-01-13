package ru.basharin.api;

import com.google.gson.Gson;
import ru.basharin.dao.DAOFactory;
import ru.basharin.dao.implhibernate.RackDAO;
import ru.basharin.model.Rack;
import ru.basharin.model.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RackServlet extends HttpServlet {
    private RackDAO rackDAO = DAOFactory.createRackDAO();
    private Gson gson = new Gson();

    public static class RackDTO {
        final int number;
        final int storageNumber;

        public RackDTO(int number, int storageNumber) {
            this.number = number;
            this.storageNumber = storageNumber;
        }
    }

    // TODO: 22.11.2017 прикрутить продукт каунт в сервлет, иначе нучего не протестировать!
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        if (number == null) {
            List<RackDTO> racks = new ArrayList<>();
            for (Rack rack : rackDAO.readAll()) {
                racks.add(new RackDTO(rack.getNumber(), rack.getStorage().getNumber()));
            }
            resp.getWriter().write(gson.toJson(racks));
        } else {
            Rack rack = rackDAO.read(Integer.parseInt(number));
            resp.getWriter().write(gson.toJson(new RackDTO(rack.getNumber(), rack.getStorage().getNumber())));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rack rack = gson.fromJson(req.getReader(), Rack.class);
        try {
            rackDAO.create(new Rack(rack.getNumber(), rack.getStorage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rack rack = gson.fromJson(req.getReader(), Rack.class);
        rackDAO.delete(rack);
    }
}
