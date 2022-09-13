package Niveau3_persistens;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

    public static Connection connection;
    public static Connection createConnection() {



        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/datademo?serverTimezone=CET&useSSL=false", "root", "ravn123");

        } catch (Exception exception) {
            exception.printStackTrace();

        }

        return connection;
    }

}