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
import static org.mockito.Mockito.atLeast;

public class StudentServletTest {

    private StudentServlet studentServlet;
    private HttpServletResponse mockResponse;
    private HttpServletRequest mockRequest;
    private HttpSession mockSession;

    @Before
    public void setUp() throws Exception {
        new Constant();
        studentServlet = new StudentServlet();
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockResponse = Mockito.mock(HttpServletResponse.class);
        mockSession = Mockito.mock(HttpSession.class);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        Mockito.when(mockSession.getAttribute("user")).thenReturn("user");
        Mockito.when(mockRequest.getParameter("page")).thenReturn("1");
        Mockito.when(mockRequest.getParameter("currentPage")).thenReturn("1");
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("home.jsp"))).thenReturn(requestDispatcher);
        studentServlet.doGet(mockRequest, mockResponse);
        assertEquals(mockRequest.getParameter("currentPage"), "1");
    }

    @Test
    public void doPost() throws ServletException, IOException {
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        Mockito.when(mockSession.getAttribute("user")).thenReturn("user");
        Mockito.when(mockRequest.getParameter("studentButton")).thenReturn("editStudent_1");
        Mockito.when(mockRequest.getAttribute("studentID")).thenReturn("1");
        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher(eq("view/addStudent.jsp"))).thenReturn(requestDispatcher);
        studentServlet.doPost(mockRequest, mockResponse);
        assertEquals(mockRequest.getAttribute("studentID"), "1");
    }
}