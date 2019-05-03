package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.StudentDao;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.Student;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private StudentDao dao;
    private Logger logger = Logger.getLogger(StudentServlet.class);

    public StudentServlet() {
        Constant.loggerConfig(logger);
        dao = new StudentDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("show Students get request");
        int pageid = 1;
        String spageid=req.getParameter("page");
        if (spageid != null) {
            pageid = Integer.parseInt(spageid);
        }
        int recordsPerPage = 5;
        List<Student>students = dao.getStudentsList((pageid - 1) * recordsPerPage + 1, recordsPerPage);
        if (students != null) {
            req.setAttribute("students", students);
        }
        int recordsCount = dao.getColOfRecords();
        int noOfPages = (int) Math.ceil(recordsCount * 1.0 / recordsPerPage);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", pageid);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("show Students post request");
        String buttonValue = req.getParameter("studentButton");
        String[] buttonArr = buttonValue.split("_");
        String studentId = buttonArr[buttonArr.length-1];
        if (buttonValue.contains("delStudent")) {
            dao.deleteStudent(Integer.parseInt(studentId));
            doGet(req,resp);
        } else if (buttonValue.contains("editStudent")) {
            List<String> groupsList = dao.getGroupNumList();
            if (groupsList != null) {
                req.setAttribute("groupsList", groupsList);
            }
            Student student = dao.getStudent(Integer.parseInt(studentId));
            req = setReqParams(req, student);
            req.getRequestDispatcher("view/addStudent.jsp").forward(req, resp);
        }
    }

    private HttpServletRequest setReqParams(HttpServletRequest req, Student student) {
        req.setAttribute("studentID", student.getId());
        req.setAttribute("studentFIO", student.getSurname() + " " + student.getName() + " " + student.getSecondName());
        req.setAttribute("studentGroup", student.getGroupNum());
        req.setAttribute("studentCity", student.getCity());
        req.setAttribute("studentStreet", student.getAddress().getStreet());
        req.setAttribute("studentHouse", student.getAddress().getHouse());
        req.setAttribute("studentFlat", student.getAddress().getFlat());
        return req;
    }
}
