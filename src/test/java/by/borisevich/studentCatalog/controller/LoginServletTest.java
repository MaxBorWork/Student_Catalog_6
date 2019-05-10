package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.model.Constant;
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

public class LoginServletTest {

    private LoginServlet loginServlet;
    private HttpServletResponse mockResponse;
    private HttpServletRequest mockRequest;
    private HttpSession mockSession;

    @Before
    public void setUp() throws Exception {
        new Constant();
        loginServlet = new LoginServlet();
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockResponse = Mockito.mock(HttpServletResponse.class);
        mockSession = Mockito.mock(HttpSession.class);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        Mockito.when(mockSession.getAttribute("user")).thenReturn("user");
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("view/login.jsp"))).thenReturn(requestDispatcher);
        loginServlet.doGet(mockRequest, mockResponse);
        assertNull(mockSession.getAttribute("role"));
    }

    @Test
    public void doPost() throws ServletException, IOException {
        Mockito.when(mockRequest.getParameter("username")).thenReturn("admin");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("111");
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("view/login.jsp"))).thenReturn(requestDispatcher);
        loginServlet.doPost(mockRequest, mockResponse);
        assertNull(mockSession.getAttribute("role"));
    }

    @Test
    public void doPost1() throws ServletException, IOException {
        Mockito.when(mockRequest.getParameter("username")).thenReturn("test");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("111");
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("view/login.jsp"))).thenReturn(requestDispatcher);
        loginServlet.doPost(mockRequest, mockResponse);
        assertNull(mockSession.getAttribute("role"));
    }

    @Test
    public void doPost2() throws ServletException, IOException {
        Mockito.when(mockRequest.getParameter("username")).thenReturn("admin");
        Mockito.when(mockRequest.getParameter("password")).thenReturn("admin");
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("/showStudents?page=1"))).thenReturn(requestDispatcher);
        loginServlet.doPost(mockRequest, mockResponse);
        assertNull(mockSession.getAttribute("role"));
    }
}