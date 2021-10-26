package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Statement statement;

    {
        try {
            statement = util.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {

        String queryCreateUsersTable = "CREATE TABLE IF NOT EXISTS user " +
                "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                " Name VARCHAR(45), " +
                " lastName VARCHAR(45), " +
                " age tinyint(3), " +
                " PRIMARY KEY ( id ))";

        try {
            statement.executeUpdate(queryCreateUsersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String queryDropUsersTable = "DROP TABLE IF EXISTS user";
        try {
            statement.executeUpdate(queryDropUsersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String querySaveUser = "INSERT INTO user (NAME, LASTNAME, AGE)" +
                "VALUES ( '" + name + "', '" + lastName + "', " + age + ")";
        try {
            statement.executeUpdate(querySaveUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String queryRemoveUserById = "DELETE FROM user " +
                "WHERE Id=" + id;
        try {
            statement.executeUpdate(queryRemoveUserById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String query = "select * from user";
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void cleanUsersTable() {
        String queryСleanUsersTable = "DELETE FROM user";
        try {
            statement.executeUpdate(queryСleanUsersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
