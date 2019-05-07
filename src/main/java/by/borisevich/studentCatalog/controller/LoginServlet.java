package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.UserDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(AddStudentServlet.class);
    private UserDao dao = new UserDao();

    public LoginServlet() {
        Constant.loggerConfig(logger);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("got login get request");
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            session.setAttribute("user", null);
            session.setAttribute("role", null);
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = dao.getUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", username);
                session.setAttribute("role", user.getRole());
                logger.info("successfully logged in user " + username);
                resp.sendRedirect(req.getContextPath() + "/showStudents?page=1");
//                req.getRequestDispatcher("home.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Введен неверный пароль!");
                logger.info("user " + username + " can't logged in. Password"  + password + " is wrong.");
                req.getRequestDispatcher("view/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Пользователь с ником " + username + " не существует");
            logger.info("user " + username + " can't logged in. User is not found.");
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
        }
    }
}
