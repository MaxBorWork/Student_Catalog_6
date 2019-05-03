package by.borisevich.studentCatalog.controller;

import by.borisevich.studentCatalog.dao.StudentDao;
import by.borisevich.studentCatalog.model.Address;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.Student;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddStudentServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(AddStudentServlet.class);
    private StudentDao dao = new StudentDao();

    public AddStudentServlet() {
        Constant.loggerConfig(logger);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("addStudent get request");
        List<String> groupsList = dao.getGroupNumList();
        if (groupsList != null) {
            req.setAttribute("groupsList", groupsList);
        }
        req.getRequestDispatcher("view/addStudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("addStudent post request");
        Student student = createStudent(req);
        if (!req.getParameter("studentID").equals("")) {
            student.setId(Integer.parseInt(req.getParameter("studentID")));
            dao.updateStudent(student);
        } else {
            dao.addStudent(student);
        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        req.getRequestDispatcher("view/addStudent.jsp").forward(req, resp);
    }

    private Student splitFIO(HttpServletRequest req) {
        logger.info("going to split full name");
        Student student = new Student();
        String fullName = req.getParameter("studentFIO");
        String[] fullNameSplit = fullName.split(" ");

        if (fullNameSplit.length != 3) {
            System.out.println("Error!");
        } else {
            student.setSurname(fullNameSplit[0]);
            student.setName(fullNameSplit[1]);
            student.setSecondName(fullNameSplit[2]);
        }
        logger.info("full name splitted");
        return student;
    }

    private Student createStudent(HttpServletRequest req) {
        Student student = splitFIO(req);
        student.setGroupNum(Integer.parseInt(req.getParameter("studentGroup")));
        student.setCity(req.getParameter("studentCity"));
        Address address = new Address();
        address.setStreet(req.getParameter("studentStreet"));
        address.setHouse(req.getParameter("studentHouse"));
        address.setFlat(req.getParameter("studentFlat"));
        student.setAddress(address);

        return student;
    }
}
