package by.borisevich.studentCatalog.dao;

import by.borisevich.studentCatalog.model.Address;
import by.borisevich.studentCatalog.model.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoTest {

    private StudentDao dao = new StudentDao();
    private Student student;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void addSomeStudents() {

    }

    @Test
    public void addStudent() {
        Student student = new Student("Test", "Test", "Test", 621702,"Test",
                new Address("Test", "2", "3"));
        int beforeSize = dao.getColOfRecords();
        dao.addStudent(student);
        int finalSize = dao.getColOfRecords();
        dao.deleteStudent(dao.getLastStudent().getId());
        assertEquals(beforeSize+1, finalSize);
    }

    @Test
    public void updateStudent() {
        Student student = dao.getLastStudent();
        student.setSurname("Test1");
        dao.updateStudent(student);
        Student student1 = dao.getStudent(student.getId());
        assertEquals("Test1", student1.getSurname());
    }

    @Test
    public void listStudents() {
    }

    @Test
    public void getStudent() {
        Student student = new Student("getTest", "getTest", "getTest", 621702,"getTest",
                new Address("getTest", "2", "3"));
        dao.addStudent(student);
        Student student1 = dao.getLastStudent();
        String resultSurname = student1.getSurname();
        dao.deleteStudent(student1.getId());
        assertEquals("getTest", resultSurname);
    }

    @Test
    public void deleteStudent() {
        int beforeDelete = dao.getColOfRecords();
        int afterDelete = 0;
        if (beforeDelete != 0) {
            Student student = dao.getLastStudent();
            dao.deleteStudent(student.getId());
            afterDelete = dao.getColOfRecords();
            dao.addStudent(student);
        } else {
            Student student = new Student("Test", "Test", "Test", 621702,"Test",
                    new Address("Test", "2", "3"));
            dao.addStudent(student);
            beforeDelete = dao.getColOfRecords();
        }
        assertEquals(beforeDelete-1, afterDelete);
    }

    @Test
    public void getStudentsList() {
        int pageid = 1;
        int total = 5;
        int expectedSize = 5;
        List<Student> list = dao.getStudentsList(pageid, total);
        assertEquals(expectedSize, list.size());
    }

    @Test
    public void getLastStudent() {
        Student student = new Student("getTest", "getTest", "getTest", 621702,"getTest",
                new Address("getTest", "2", "3"));
        dao.addStudent(student);
        Student student1 = dao.getLastStudent();
        String resultSurname = student1.getSurname();
        dao.deleteStudent(student1.getId());
        assertEquals("getTest", resultSurname);
    }

    @Test
    public void getColOfRecords() {
        int expSize = dao.getColOfRecords() + 1;
        Student student = new Student("Test", "Test", "Test", 621702 ,"Test",
                new Address("Test", "2", "3"));
        dao.addStudent(student);
        int resultSize = dao.getColOfRecords();
        dao.deleteStudent(dao.getLastStudent().getId());
        assertEquals(expSize, resultSize);
    }
}