package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.UserDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;

public class RegisterServletTest {

    private RegisterServlet registerServlet;
    private HttpServletResponse mockResponse;
    private HttpServletRequest mockRequest;
    private HttpSession mockSession;
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        new Constant();
        userDao = new UserDao();
        registerServlet = new RegisterServlet();
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockResponse = Mockito.mock(HttpServletResponse.class);
        mockSession = Mockito.mock(HttpSession.class);
    }

    @Test
    public void doPost() throws ServletException, IOException {
        String username = "test";
        Mockito.when(mockRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockRequest.getParameter("password")).thenReturn(username);
        Mockito.when(mockRequest.getParameter("userEmail")).thenReturn(username);
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("view/register.jsp"))).thenReturn(requestDispatcher);
        int studentsBefore = userDao.getColOfUsers();
        registerServlet.doPost(mockRequest, mockResponse);
        int studentsAfter = userDao.getColOfUsers();
        userDao.deleteUser(username);
        assertEquals(studentsAfter, studentsBefore+1);
    }

    @Test
    public void doPost1() throws ServletException, IOException {
        String username = "test";
        String admin = "admin";
        Mockito.when(mockRequest.getParameter("username")).thenReturn(username);
        Mockito.when(mockRequest.getParameter("password")).thenReturn(null);
        Mockito.when(mockRequest.getParameter("userEmail")).thenReturn(username);
        Mockito.when(mockRequest.getParameter("role")).thenReturn(admin);
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("view/users.jsp"))).thenReturn(requestDispatcher);
        userDao.addUser(new User(username, username, "user", username));
        registerServlet.doPost(mockRequest, mockResponse);
        User user = userDao.getLastUser();
        userDao.deleteUser(username);
        assertEquals(admin, user.getRole());
    }
}