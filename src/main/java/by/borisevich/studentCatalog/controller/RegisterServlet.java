package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.StudentDao;
import by.borisevich.studentCatalog.dao.UserDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RegisterServlet extends HttpServlet {
    private Logger logger = Logger.getLogger(AddStudentServlet.class);
    private UserDao dao = new UserDao();

    public RegisterServlet() {
        Constant.loggerConfig(logger);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("register get request");
        List<String> rolenameList = dao.getRolenameList();
        if (rolenameList != null) {
            req.setAttribute("rolesList", rolenameList);
        }
        req.getRequestDispatcher("view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("register post request");
        dao.addUser(createUser(req));
        req.setAttribute("successRegistration", "Регистрация успешна!");
        req.getRequestDispatcher("view/register.jsp").forward(req, resp);
    }

    private User createUser(HttpServletRequest req) {
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("userEmail"));
        user.setRole(req.getParameter("role"));
        return user;
    }
}
