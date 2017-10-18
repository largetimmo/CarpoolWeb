package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by chenjunhao on 2017/10/17.
 */
public class ConnectionPool {
    private static final String ADDR = "jdbc:mysql://127.0.0.1:3306/carpooweb?useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "221600";
    private static final String MYSQL_CLASS_NAME = "com.mysql.jdbc.Driver";
    private Connection bookedCarpoolConnection;
    private Connection carpoolConnection;
    private Connection generalConnection;
    private Connection userManagementConnection;
    private Connection carpoolOwnerConnection;
    private static ConnectionPool instance = new ConnectionPool();
    protected static ConnectionPool getInstance(){
        return instance;
    }
    static {
        try {
            Class.forName(MYSQL_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionPool(){
        try {
            generalConnection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            bookedCarpoolConnection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            carpoolConnection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            userManagementConnection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
            carpoolOwnerConnection = DriverManager.getConnection(ADDR,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected Connection getBookedCarpoolConnection() {
        synchronized (bookedCarpoolConnection) {
            return bookedCarpoolConnection;
        }
    }

    protected Connection getCarpoolConnection() {
        synchronized (carpoolConnection){
            return carpoolConnection;
        }
    }

    protected Connection getGeneralConnection() {
        synchronized (generalConnection){
            return generalConnection;
        }
    }

    protected Connection getUserManagementConnection() {
        synchronized (userManagementConnection){
            return userManagementConnection;
        }
    }
    protected Connection getCarpoolOwnerConnection(){
        synchronized (carpoolOwnerConnection){
            return carpoolOwnerConnection;
        }
    }
}
