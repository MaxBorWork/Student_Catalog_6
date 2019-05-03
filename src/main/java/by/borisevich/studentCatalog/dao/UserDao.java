package by.borisevich.studentCatalog.dao;

import by.borisevich.studentCatalog.model.Address;
import by.borisevich.studentCatalog.model.Constant;
import by.borisevich.studentCatalog.model.Student;
import by.borisevich.studentCatalog.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Logger log = Logger.getLogger(StudentDao.class);

    public UserDao() {
        try {
            Constant.loggerConfig(log);
            log.info("creating user datatables");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);

            Statement statement = con.createStatement();

            statement.execute(Constant.SQL_CREATE_ROLE_TABLE);

            statement.execute(Constant.SQL_CREATE_USER_TABLE);

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            if (user != null) {
                PreparedStatement ps = con.prepareStatement(Constant.SQL_INSERT_USER_QUERY);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setInt(4, getRoleId(user.getRole()));
                ps .executeUpdate();

                con.close();
                log.info("student " + user.getUsername() + " added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String username, String password) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_SELECT_USER);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User foundUser = new User();
                foundUser.setUsername(resultSet.getString(1));
                foundUser.setPassword(resultSet.getString(2));
                foundUser.setEmail(resultSet.getString(3));
                foundUser.setRole(getRolenameById(resultSet.getInt(4)));
                con.close();

                log.info("found user " + foundUser.getUsername());
                return foundUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String username) {
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_SELECT_USER_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User foundUser = new User();
                foundUser.setUsername(resultSet.getString(1));
                foundUser.setPassword(resultSet.getString(2));
                foundUser.setEmail(resultSet.getString(3));
                foundUser.setRole(getRolenameById(resultSet.getInt(4)));
                con.close();

                log.info("found user " + foundUser.getUsername());
                return foundUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getRoleId(String rolename) {
        int id = 0;
        try {
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_GET_ROLE_ID_BY_NAME);
            statement.setString(1, rolename);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<String> getRolenameList() {
        List<String> list=new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement ps = con.prepareStatement(Constant.SQL_GET_ROLENAME_LIST);
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

    private String getRolenameById(int id) {
        String rolename= "";
        try{
            Connection con = DriverManager.getConnection(Constant.dbUrl, Constant.dbUser, Constant.dbPassword);
            PreparedStatement ps = con.prepareStatement(Constant.SQL_GET_ROLENAME_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                rolename = resultSet.getString(1);
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();;
        }
        return rolename;
    }

}