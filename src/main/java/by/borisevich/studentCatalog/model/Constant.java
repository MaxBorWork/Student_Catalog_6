package by.borisevich.studentCatalog.model;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Constant {

    public static final String ERROR_AUTHORIZATION_MESSAGE = "Авторизация неудачна!";

    public static final String SUPER_ADMIN_ROLE = "sudo";
    public static final String ADMIN_ROLE = "admin";
    public static final String USER_ROLE = "user";

    public static final String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE if not exists Student (" +
                                                                "id int NOT NULL AUTO_INCREMENT," +
                                                                "surname varchar(255) not null," +
                                                                "name varchar(255) not null," +
                                                                "secondName varchar(255) not null," +
                                                                "groupId int not null," +
                                                                "city varchar(255) not null," +
                                                                "PRIMARY KEY (id),"+
                                                                "FOREIGN KEY (groupId) REFERENCES StudentsGroup(id)" +
                                                                ")";

    public static final String SQL_CREATE_ADDRESS_TABLE = "CREATE TABLE if not exists Address (" +
                                                                "id int NOT NULL AUTO_INCREMENT," +
                                                                "street varchar(255) not null," +
                                                                "house varchar(255) not null," +
                                                                "flat varchar(255) not null," +
                                                                "studentId int not null," +
                                                                "PRIMARY KEY (id)," +
                                                                "FOREIGN KEY (studentId) REFERENCES Student(id)" +
                                                                ")";

    public static final String SQL_CREATE_GROUP_TABLE = "CREATE TABLE if not exists StudentsGroup (" +
                                                                "id int NOT NULL AUTO_INCREMENT," +
                                                                "groupNum varchar(255) not null," +
                                                                "groupType varchar(255) not null," +
                                                                "PRIMARY KEY (id)" +
                                                                ")";

    public static final String SQL_CREATE_ROLE_TABLE = "CREATE TABLE if not exists Role (" +
                                                                "  id int not null AUTO_INCREMENT," +
                                                                "  rolename varchar(255) NOT NULL," +
                                                                "  PRIMARY KEY (id)" +
                                                                ")";

    public static final String SQL_CREATE_USER_TABLE = "CREATE TABLE if not exists User (" +
                                                                "id int not null AUTO_INCREMENT," +
                                                                "username varchar(255) NOT NULL," +
                                                                "password varchar(255) NOT NULL," +
                                                                "email varchar(255)," +
                                                                "roleid int not null," +
                                                                "PRIMARY KEY (id)," +
                                                                "FOREIGN KEY (roleid) REFERENCES Role(id)" +
                                                                ")";

    public static final String SQL_SELECT_LAST_RECORD = "SELECT * FROM Student INNER JOIN " +
                                                            "Address ON Student.id = Address.studentId " +
                                                            "INNER JOIN StudentsGroup ON Student.groupId = StudentsGroup.id " +
                                                            "WHERE Student.id = (SELECT max(id) FROM Student)";

    public static final String SQL_SELECT_LAST_STUDENT_ID = "SELECT max(id) FROM Student";

    public static final String SQL_GET_COL_OF_RECORDS = "SELECT COUNT(id) FROM Student";

    public static final String SQL_SELECT_ALL_RECORDS = "SELECT * FROM Student " +
                                                                "INNER JOIN Address ON Student.id = Address.studentId" +
                                                                "INNER JOIN StudentsGroup ON Student.groupId = StudentsGroup.id";

    public static final String SQL_UPDATE_STUDENT_QUERY = "UPDATE Student " +
                                                                "SET surname=?, name=?, secondName=?, groupId=?, city=? " +
                                                                " WHERE id=?";

    public static final String SQL_UPDATE_ADDRESS_QUERY = "UPDATE Address SET street=?, house=?, flat=?" +
                                                                " WHERE Address.studentId=?;";

    public static final String SQL_UPDATE_GROUP_QUERY = "UPDATE StudentsGroup SET groupId=?, type=?" +
                                                                " WHERE StudentsGroup.id=?;";

    public static final String SQL_DELETE_STUDENT_QUERY = "DELETE FROM Student WHERE id=?";

    public static final String SQL_DELETE_ADDRESS_QUERY = "DELETE FROM Address WHERE studentId=?";

    public static final String SQL_DELETE_GROUP_QUERY = "DELETE FROM StudentsGroup WHERE id=?";

    public static final String SQL_SELECT_STUDENT_BY_ID = "SELECT * FROM Student INNER JOIN " +
                                                                "Address ON Student.id = Address.studentId " +
                                                                "INNER JOIN StudentsGroup ON Student.groupId = StudentsGroup.id " +
                                                                "WHERE Student.id =?";

    public static final String SQL_INSERT_STUDENT_QUERY = "INSERT INTO Student" +
                                                                "(surname, name, secondName, groupId, city)" +
                                                                "VALUES (?,?,?,?,?)";

    public static final String SQL_INSERT_ADDRESS_QUERY = "INSERT INTO Address" +
                                                                "(street, house, flat, studentId) " +
                                                                "VALUES (?,?,?,?)";

    public static final String SQL_INSERT_GROUP_QUERY = "INSERT INTO StudentsGroup" +
                                                                "(groupNum, type) " +
                                                                "VALUES (?,?)";

    public static final String SQL_GET_GROUP_ID_BY_NUM = "SELECT id FROM StudentsGroup WHERE groupNum=?";

    public static final String SQL_INSERT_USER_QUERY = "INSERT INTO User" +
                                                            "(username, password, email, roleid) " +
                                                            "VALUES (?,?,?,?)";

    public static final String SQL_SELECT_USER = "SELECT username, password, email, roleid FROM User " +
                                                        "WHERE username=? AND password=?";

    public static final String SQL_SELECT_USER_BY_USERNAME = "SELECT username, password, email, roleid FROM User " +
                                                                "WHERE username=?";

    public static final String SQL_SELECT_LAST_USER = "SELECT username, password, email, roleid FROM User " +
                                                                "WHERE id = (SELECT max(id) FROM User)";

    public static final String SQL_GET_COL_OF_USERS = "SELECT COUNT(id) FROM User";

    public static final String SQL_DELETE_USER_QUERY = "DELETE FROM User WHERE username=?";

    public static final String SQL_GET_ROLE_ID_BY_NAME = "SELECT id FROM Role WHERE rolename=?";

    public static final String SQL_UPDATE_ROLE = "UPDATE User SET roleid=? WHERE username=?;";

    public static final String SQL_GET_ROLENAME_BY_ID = "SELECT rolename FROM Role WHERE id=?";

    public static final String SQL_GET_ROLENAME_LIST = "SELECT DISTINCT rolename FROM Role WHERE id!=3";

    public static final String SQL_GET_USER_LIST = "SELECT username, password, roleid, email from User " +
                                                        "WHERE User.roleid=2 " +
                                                        "LIMIT ?,?";

    public static final String SQL_GET_EXTENDED_USER_LIST = "SELECT username, password, roleid, email from User " +
            "WHERE User.roleid !=3 " +
            "LIMIT ?,?";

    public static void loggerConfig(Logger logger) {
        logger.setLevel(Level.ALL);
        PatternLayout layout = new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        try {
            FileAppender fileAppender = new FileAppender(layout, "/home/maksim/tomcat/logs/lab6-log.log");
            fileAppender.setAppend(false);
            fileAppender.setImmediateFlush(true);
            logger.addAppender(fileAppender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String DB_URL;
    public static String DB_USER;
    public static String DB_PASSWORD;

    public static String VK_CLIENT_ID;
    public static String VK_REDIRECT_URL;
    public static String VK_CLIENT_SECRET;

    public static String YANDEX_CLIENT_ID;
    public static String YANDEX_REDIRECT_URL;
    public static String YANDEX_CLIENT_SECRET;
    public static String YANDEX_GET_TOKEN_URL;
    public static String YANDEX_GET_INFO_URL;

    public Constant() {
        try{
            String text = new String(Files.readAllBytes(Paths.get("Config.json")), StandardCharsets.UTF_8);
            JSONObject object = new JSONObject(text);

            DB_URL = object.get("DB_URL").toString();
            DB_USER = object.get("DB_USER").toString();
            DB_PASSWORD = object.get("DB_PASSWORD").toString();

            VK_CLIENT_ID = object.get("VK_CLIENT_ID").toString();
            VK_REDIRECT_URL =  object.get("VK_REDIRECT_URL").toString();
            VK_CLIENT_SECRET = object.get("VK_CLIENT_SECRET").toString();

            YANDEX_CLIENT_ID = object.get("YANDEX_CLIENT_ID").toString();
            YANDEX_REDIRECT_URL = object.get("YANDEX_REDIRECT_URL").toString();
            YANDEX_CLIENT_SECRET = object.get("YANDEX_CLIENT_SECRET").toString();
            YANDEX_GET_TOKEN_URL = object.get("YANDEX_GET_TOKEN_URL").toString();
            YANDEX_GET_INFO_URL = object.get("YANDEX_GET_INFO_URL").toString();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
