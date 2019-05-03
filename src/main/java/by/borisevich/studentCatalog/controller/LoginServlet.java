package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.UserDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.IdTokenVerifierAndParser;
import by.borisevich.studentCatalog.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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
        req.getRequestDispatcher("view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        User user = dao.getUserByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(req.getParameter("password"))) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Введен неверный пароль!");
                req.getRequestDispatcher("view/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "Пользователь с ником " + username + " не существует");
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
        }
    }
}
