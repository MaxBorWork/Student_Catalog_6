package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.StudentDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;

public class AddStudentServletTest {
    private AddStudentServlet addStudentServlet;
    private HttpServletResponse mockResponse;
    private HttpServletRequest mockRequest;

//    @Before
//    public void setUp() throws Exception {
//        addStudentServlet = new AddStudentServlet();
//        mockRequest = Mockito.mock(HttpServletRequest.class);
//        mockResponse = Mockito.mock(HttpServletResponse.class);
//    }
//
//    @Test
//    public void doPost() throws ServletException, IOException {
//        Mockito.when(mockRequest.getParameter("studentFIO")).thenReturn("TestT test tess");
//        Mockito.when(mockRequest.getParameter("studentGroup")).thenReturn("621702");
//        Mockito.when(mockRequest.getParameter("studentCity")).thenReturn("secret");
//        Mockito.when(mockRequest.getParameter("studentStreet")).thenReturn("secret");
//        Mockito.when(mockRequest.getParameter("studentHouse")).thenReturn("1222");
//        Mockito.when(mockRequest.getParameter("studentFlat")).thenReturn("111");
//        Mockito.when(mockRequest.getParameter("studentID")).thenReturn("");
//        RequestDispatcher requestDispatcher = Mockito.mock(RequestDispatcher.class);
//        Mockito.when(mockRequest.getRequestDispatcher(eq("view/addStudent.jsp"))).thenReturn(requestDispatcher);
//        StudentDao dao = new StudentDao();
//        int colBefore = dao.getColOfRecords();
//        addStudentServlet.doPost(mockRequest, mockResponse);
//        int colAfter = dao.getColOfRecords();
//        dao.deleteStudent(dao.getLastStudent().getId());
//        assertEquals(colBefore+1, colAfter);
//    }

}