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
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);

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
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
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
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
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
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
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

    public List<User> getUsersList(int start,int total) {
        List<User> list=new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement(Constant.SQL_GET_USER_LIST);
            ps.setInt(1, (start-1));
            ps.setInt(2, total);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(new User(resultSet.getString(1),
                        resultSet.getString(2),
                        getRolenameById(resultSet.getInt(3)),
                        resultSet.getString(4)));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();;
        }
        return list;
    }

    public List<User> getExtendedUsersList(int start,int total) {
        List<User> list=new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
            PreparedStatement ps = con.prepareStatement(Constant.SQL_GET_EXTENDED_USER_LIST);
            ps.setInt(1, (start-1));
            ps.setInt(2, total);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                list.add(new User(resultSet.getString(1),
                        resultSet.getString(2),
                        getRolenameById(resultSet.getInt(3)),
                        resultSet.getString(4)));
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();;
        }
        return list;
    }

    public void deleteUser(String username) {
        try {
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
            PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_DELETE_USER_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

            con.close();
            log.info("user " + username + " deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUserRole(int roleID, String username) {
        try {
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(Constant.SQL_UPDATE_ROLE);
                preparedStatement.setInt(1, roleID);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getLastUser() {
        try {
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_SELECT_LAST_USER);
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

    public int getColOfUsers() {
        int colOfRecords = 0;
        try {
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
            PreparedStatement statement = con.prepareStatement(Constant.SQL_GET_COL_OF_USERS);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                colOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colOfRecords;
    }

    public int getRoleId(String rolename) {
        int id = 0;
        try {
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
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
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
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
            Connection con = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
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
