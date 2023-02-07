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
/**
 * @author XZ
 * used for registration and show
 */
@WebServlet(value = "/UserRegister")
public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("templates/register.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        DBUtils dbUtils = new DBUtils(new DBConnection("postgresql", "localhost",
                "5432", "postgres", "123456789"));
        dbUtils.registerUser("user", new User(email, password));
        request.setAttribute("message", "User registered successfully");
        request.getRequestDispatcher("templates/register.jsp").forward(request, response);
        dbUtils.getConnection().DBClose();
    }
}
