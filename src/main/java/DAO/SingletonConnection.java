package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private SingletonConnection() {
    }

    public static Connection connection;


    public static Connection getConnection(){
        if (connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/quanlysanpham",
                        "root",
                        "123456"
                );
                System.out.println("Thanh cong");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {

            }
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
