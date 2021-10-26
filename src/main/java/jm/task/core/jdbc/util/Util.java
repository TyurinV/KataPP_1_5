package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {
    private Driver driver;
    private Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/users";
    private final String USERNAME = "root";
    private final String PASSWORD = "parol123";

    public Connection getConnection() {
        return connection;
    }

    public Util() {
        {
            try {
                driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);

                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            } catch (SQLException e) {
                System.out.println("Не удалось загрузить класс драйвер");
            }
        }
    }
}
