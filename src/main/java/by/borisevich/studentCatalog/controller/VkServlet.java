package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.UserDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class VkServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(AddStudentServlet.class);
    private UserDao dao = new UserDao();
    private final String USER_AGENT = "Mozilla/5.0";

    public VkServlet() {
        Constant.loggerConfig(logger);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            HttpSession session = req.getSession();
            session.setAttribute("code", code);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
    }

//    @Override
//    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        User user = dao.getUserByUsername(username);
//        if (user != null) {
//            if (user.getPassword().equals(req.getParameter("password"))) {
//                HttpSession session = req.getSession();
//                session.setAttribute("user", user);
//                session.setAttribute("role", user.getRole());
//                req.getRequestDispatcher("home.jsp").forward(req, resp);
//            } else {
//                req.setAttribute("message", "Введен неверный пароль!");
//                req.getRequestDispatcher("view/login.jsp").forward(req, resp);
//            }
//        } else {
//            req.setAttribute("message", "Пользователь с ником " + username + " не существует");
//            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
//        }
//    }

    private String getToken(String code) throws Exception {
        String url = "http://www.google.com/search?q=developer";
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);

        HttpResponse response = client.execute(request);
        return response.toString();
    }
}
