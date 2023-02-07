package de.xiekang.web_tutorial.controller;

import de.xiekang.web_tutorial.db.DBConnection;
import de.xiekang.web_tutorial.db.DBUtils;
import de.xiekang.web_tutorial.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/ShowUsers")
public class UserShowServlet extends HttpServlet {
    // TODO: add password encryption method!!!
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        DBUtils dbUtils = new DBUtils(new DBConnection("postgresql", "localhost",
                "5432", "postgres", "123456789"));
        List<User> users = dbUtils.showUsers("user");
        request.setAttribute("users", users);
        request.getRequestDispatcher("templates/show.jsp").forward(request, response);
        dbUtils.getConnection().DBClose();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("templates/show.jsp").forward(request, response);
    }
}
