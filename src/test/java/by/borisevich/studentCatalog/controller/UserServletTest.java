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

public class UserServletTest {

    private UserServlet userServlet;
    private HttpServletResponse mockResponse;
    private HttpServletRequest mockRequest;
    private HttpSession mockSession;

    @Before
    public void setUp() throws Exception {
        new Constant();
        userServlet = new UserServlet();
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockResponse = Mockito.mock(HttpServletResponse.class);
        mockSession = Mockito.mock(HttpSession.class);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        Mockito.when(mockRequest.getParameter("page")).thenReturn("1");
        Mockito.when(mockRequest.getParameter("currentPage")).thenReturn("1");
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        Mockito.when(mockSession.getAttribute("user")).thenReturn("user");
        Mockito.when(mockSession.getAttribute("role")).thenReturn("sudo");
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("view/users.jsp"))).thenReturn(requestDispatcher);
        userServlet.doGet(mockRequest, mockResponse);
        assertEquals(mockRequest.getParameter("currentPage"), "1");
    }

}