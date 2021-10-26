package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService one = new UserServiceImpl();
        one.createUsersTable();
        one.saveUser("Иван", "Иванов", (byte) 33);
        one.saveUser("Алиса", "Фомина", (byte) 22);
        one.saveUser("Тарас", "Бульба", (byte) 17);
        one.saveUser("Иван", "Грозный", (byte) 55);
        one.getAllUsers().stream().forEach(System.out::println);
        one.cleanUsersTable();
        one.dropUsersTable();
    }
}
