package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.UserDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class VkServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(AddStudentServlet.class);
    private UserDao dao = new UserDao();

    public VkServlet() {
        Constant.loggerConfig(logger);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        logger.info("got vk code " + code);
        if (code != null) {
            JSONObject tokenResponse = null;
            try {
                tokenResponse = getTokenResponse(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tokenResponse != null) {
                String token = tokenResponse.getString("access_token");
                int userID = tokenResponse.getInt("user_id");
                String userIDString = String.valueOf(userID);
                logger.info("got vk token " + token + "for user with id " + userID);
                User user = dao.getUserByUsername(userIDString);
                HttpSession session = req.getSession();
                if (user != null) {
                    session.setAttribute("user", userIDString);
                    session.setAttribute("role", user.getRole());
                } else {
                    String role = Constant.USER_ROLE;
                    if (dao.getColOfUsers() == 0 ) {
                        role = Constant.SUPER_ADMIN_ROLE;
                    }
                    dao.addUser(new User(userIDString, userIDString, role, userIDString + "@vk"));
                    session.setAttribute("user", userIDString);
                    session.setAttribute("role", role);
                }
                resp.sendRedirect(req.getContextPath() + "/showStudents?page=1");
            } else {
                req.setAttribute("message", Constant.ERROR_AUTHORIZATION_MESSAGE);
                req.getRequestDispatcher("view/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", Constant.ERROR_AUTHORIZATION_MESSAGE);
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
        }
    }

    private JSONObject getTokenResponse(String code) throws Exception {
        String url = "https://oauth.vk.com/access_token?" +
                                "client_id=" + Constant.VK_CLIENT_ID +
                                "&client_secret=" + Constant.VK_CLIENT_SECRET +
                                "&redirect_uri=" + Constant.VK_REDIRECT_URL +
                                "&code=" + code;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        logger.debug("Sending 'GET' request to URL : " + url);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return getJSON(result.toString());
    }

    private JSONObject getJSON(String response) {
        String token = "";
        JSONObject object = new JSONObject(response);
        token = object.getString("access_token");
        return object;
    }

}
