package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoHibernateImpl udhi = new UserDaoHibernateImpl();

    public void createUsersTable() {
        udhi.createUsersTable();
    }

    public void dropUsersTable() {
        udhi.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        udhi.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        udhi.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return udhi.getAllUsers();
    }

    public void cleanUsersTable() {
        udhi.cleanUsersTable();
    }
}
