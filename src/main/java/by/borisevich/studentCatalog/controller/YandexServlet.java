package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.UserDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class YandexServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(AddStudentServlet.class);
    private UserDao dao = new UserDao();

    public YandexServlet() {
        Constant.loggerConfig(logger);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        logger.info("got yandex code " + code);
        if (code != null) {
            String token = "";
            try {
                token = getTokenResponse(code);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!token.equals("")) {
                logger.info("got yandex token " + token );
                JSONObject personalInfo = null;
                try {
                    personalInfo = getPersonInfo(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (personalInfo != null) {
                    String userID = personalInfo.getString("id");
                    User user = dao.getUserByUsername(userID);
                    HttpSession session = req.getSession();
                    if (user != null) {
                        session.setAttribute("user", userID);
                        session.setAttribute("role", user.getRole());
                    } else {
                        String role = Constant.USER_ROLE;
                        if (dao.getColOfUsers() == 0) {
                            role = Constant.SUPER_ADMIN_ROLE;
                        }
                        dao.addUser(new User(userID, userID, role, userID + "@yandex"));
                        session.setAttribute("user", userID);
                        session.setAttribute("role", role);
                    }
                    resp.sendRedirect(req.getContextPath() + "/showStudents?page=1");
                }
            } else {
                req.setAttribute("message", Constant.ERROR_AUTHORIZATION_MESSAGE);
                req.getRequestDispatcher("view/login.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", Constant.ERROR_AUTHORIZATION_MESSAGE);
            req.getRequestDispatcher("view/login.jsp").forward(req, resp);
        }
    }

    private String getTokenResponse(String code) throws Exception {
        HttpClient client= new DefaultHttpClient();
        HttpPost request = new HttpPost(Constant.YANDEX_GET_TOKEN_URL);

        List<NameValuePair> pairs = new ArrayList<>();
        pairs.add(new BasicNameValuePair("grant_type", "authorization_code"));
        pairs.add(new BasicNameValuePair("code", code));
        pairs.add(new BasicNameValuePair("client_id", Constant.YANDEX_CLIENT_ID));
        pairs.add(new BasicNameValuePair("client_secret", Constant.YANDEX_CLIENT_SECRET));
        request.setEntity(new UrlEncodedFormEntity(pairs));

        HttpResponse resp = client.execute(request);
        logger.debug("Sending POST request to URL : " + Constant.YANDEX_GET_TOKEN_URL);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(resp.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        JSONObject object = new JSONObject(result.toString());

        return object.getString("access_token");
    }

    private JSONObject getPersonInfo(String token) throws Exception {
        String url = Constant.YANDEX_GET_INFO_URL + token;
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);
        logger.info("Sending GET request to URL : " + url);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return new JSONObject(result.toString());
    }
}
