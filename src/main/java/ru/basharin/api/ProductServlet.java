package ru.basharin.api;

import com.google.gson.Gson;
import ru.basharin.dao.DAOFactory;
import ru.basharin.dao.implhibernate.ProductDAO;
import ru.basharin.model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO = DAOFactory.createProductDAO();
    private Gson gson = new Gson();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int number = Integer.parseInt(req.getParameter("number"));
        resp.getWriter().write(gson.toJson(productDAO.readProduct(name, number)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = gson.fromJson(req.getReader(), Product.class);
        productDAO.create(product);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = gson.fromJson(req.getReader(), Product.class);
        productDAO.update(product);
        resp.getWriter().write(gson.toJson(product));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = gson.fromJson(req.getReader(), Product.class);
        productDAO.delete(product);
    }
}
