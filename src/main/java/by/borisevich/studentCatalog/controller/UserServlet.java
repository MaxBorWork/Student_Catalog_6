package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.StudentDao;
import by.borisevich.studentCatalog.dao.UserDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.Student;
import by.borisevich.studentCatalog.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserDao dao;
    private Logger logger = Logger.getLogger(StudentServlet.class);

    public UserServlet() {
        Constant.loggerConfig(logger);
        dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("show Users get request");
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null &&
                (session.getAttribute("role").equals("sudo") ||
                (session.getAttribute("role").equals("admin")))) {
            int pageid = 1;
            String spageid = req.getParameter("page");
            if (spageid != null) {
                pageid = Integer.parseInt(spageid);
            }
            int recordsPerPage = 5;
            List<User> students = null;
            String role = req.getSession().getAttribute("role").toString();
            if (role.equals("admin")) {
                students = dao.getUsersList((pageid - 1) * recordsPerPage + 1, recordsPerPage);
            } else if (role.equals("sudo")) {
                students = dao.getExtendedUsersList((pageid - 1) * recordsPerPage + 1, recordsPerPage);
            }
            if (students != null) {
                req.setAttribute("users", students);
                int recordsCount = dao.getColOfUsers();
                int noOfPages = (int) Math.ceil(recordsCount * 1.0 / recordsPerPage);
                req.setAttribute("noOfPages", noOfPages);
                req.setAttribute("currentPage", pageid);
            }
            req.setAttribute("username", session.getAttribute("user").toString());
            req.getRequestDispatcher("view/users.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("show Students post request");
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null &&
                (session.getAttribute("role").equals("sudo") ||
                        (session.getAttribute("role").equals("admin")))) {
            String buttonValue = req.getParameter("userButton");
            String[] buttonArr = buttonValue.split("_");
            String username = buttonArr[buttonArr.length - 1];
            if (buttonValue.contains("delUser")) {
                dao.deleteUser(username);
                doGet(req, resp);
            } else if (buttonValue.contains("editUser")) {
                List<String> rolesList = dao.getRolenameList();
                req.setAttribute("username", username);
                if (rolesList != null) {
                    req.setAttribute("rolesList", rolesList);
                }
                req.getRequestDispatcher("view/editRole.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
        }
    }

}
