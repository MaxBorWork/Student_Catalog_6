package by.borisevich.studentCatalog.dao;

import by.borisevich.studentCatalog.model.Address;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.Student;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private Logger log = Logger.getLogger(StudentDao.class);

    public StudentDao() {
        try {
            Constant.loggerConfig(log);
            log.info("creating student datatables");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);

            Statement statement = con.createStatement();

            statement.execute(Constant.SQL_CREATE_GROUP_TABLE);

            statement.execute(Constant.SQL_CREATE_STUDENT_TABLE);

            statement.execute(Constant.SQL_CREATE_ADDRESS_TABLE);


            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (student != null) {
                PreparedStatement ps = con.prepareStatement(Constant.SQL_INSERT_STUDENT_QUERY);
                ps.setString(1, student.getSurname());
                ps.setString(2, student.getName());
                ps.setString(3, student.getSecondName());
                ps.setInt(4, getGroupId(student.getGroupNum()));
                ps.setString(5, student.getCity());
                ps .executeUpdate();

                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_INSERT_ADDRESS_QUERY);
                preparedStatement.setString(1, student.getAddress().getStreet());
                preparedStatement.setString(2, student.getAddress().getHouse());
                preparedStatement.setString(3, student.getAddress().getFlat());
                preparedStatement.setInt(4, getLastStudentId());
                preparedStatement .executeUpdate();
                con.close();
                log.info("student " + student.getSurname() + " added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (student != null) {
                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_UPDATE_ADDRESS_QUERY);
                preparedStatement.setString(1, student.getAddress().getStreet());
                preparedStatement.setString(2, student.getAddress().getHouse());
                preparedStatement.setString(3, student.getAddress().getFlat());
                preparedStatement.setInt(4, student.getId());
                preparedStatement.executeUpdate();

                PreparedStatement ps = con.prepareStatement(Constant.SQL_UPDATE_STUDENT_QUERY);
                ps.setString(1, student.getSurname());
                ps.setString(2, student.getName());
                ps.setString(3, student.getSecondName());
                ps.setInt(4, getGroupId(student.getGroupNum()));
                ps.setString(5, student.getCity());
                ps.setInt(6, student.getId());
                ps.executeUpdate();

                con.close();
                log.info("student " + student.getSurname() + " updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> listStudents() {
        List<Student> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(Constant.SQL_SELECT_ALL_RECORDS);
            while (resultSet.next()) {
                list.add(new Student(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getString(3),
                                        resultSet.getString(4),
                                        resultSet.getInt(13),
                                        resultSet.getString(6),
                                        new Address(resultSet.getInt(7),
                                                    resultSet.getString(8),
                                                    resultSet.getString(9),
                                                    resultSet.getString(10))));
            }
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Student> getStudentsList(int start,int total) {
        List<Student> list=new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement ps = con.prepareStatement("SELECT * from Student " +
                                                                "INNER JOIN Address ON Student.id = Address.id " +
                                                                "INNER JOIN StudentsGroup ON Student.groupId = StudentsGroup.id limit "+
                                                                    (start-1)+","+total);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(new Student(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(13),
                        resultSet.getString(6),
                        new Address(resultSet.getInt(7),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getString(10))));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();;
        }
        return list;
    }

    public Student getStudent(int id) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_SELECT_STUDENT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Student foundStudent = new Student();
            if (resultSet.next()) {
                foundStudent.setId(resultSet.getInt(1));
                foundStudent.setSurname(resultSet.getString(2));
                foundStudent.setName(resultSet.getString(3));
                foundStudent.setSecondName(resultSet.getString(4));
                foundStudent.setGroupNum(resultSet.getInt(13));
                foundStudent.setCity(resultSet.getString(6));
                foundStudent.setAddress(new Address(resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10)));
                con.close();

                log.info("found student " + foundStudent.getSurname());
                return foundStudent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteStudent(int id) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_DELETE_ADDRESS_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            PreparedStatement statement = con.prepareStatement(Constant.SQL_DELETE_STUDENT_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();

            con.close();
            log.info("student №" + id + " updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getLastStudent() {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_SELECT_LAST_RECORD);
            ResultSet resultSet = statement.executeQuery();
            Student foundStudent = new Student();
            if (resultSet.next()) {
                foundStudent.setId(resultSet.getInt(1));
                foundStudent.setSurname(resultSet.getString(2));
                foundStudent.setName(resultSet.getString(3));
                foundStudent.setSecondName(resultSet.getString(4));
                foundStudent.setGroupNum(resultSet.getInt(13));
                foundStudent.setCity(resultSet.getString(6));
                foundStudent.setAddress(new Address(resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10)));
                con.close();

                log.info("found student " + foundStudent.getSurname());
                return foundStudent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getLastStudentId() {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_SELECT_LAST_STUDENT_ID);
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getColOfRecords() {
        int colOfRecords = 0;
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_GET_COL_OF_RECORDS);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                colOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colOfRecords;
    }

    public List<String> getGroupNumList() {
        List<String> list=new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT groupNum FROM StudentsGroup");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(resultSet.getString(1));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();;
        }
        return list;
    }

    private int getGroupId(int groupNum) {
        int groupId = 0;
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_GET_GROUP_ID_BY_NUM);
            statement.setInt(1, groupNum);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                groupId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupId;
    }
}
