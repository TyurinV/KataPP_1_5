package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private Util util = new Util();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                    " Name VARCHAR(45), " +
                    " lastName VARCHAR(45), " +
                    " age tinyint(3), " +
                    " PRIMARY KEY ( id ))").executeUpdate();


            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void dropUsersTable() {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();

            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    @Override
    public void removeUserById(long id) {
        try {
            Session session = getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery("DELETE FROM user where id = :id")
                    .setParameter("id", id)
                    .executeUpdate();

            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            Session session = Util.getSessionFactory().openSession();
            users = session.createQuery("from User").list();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("truncate table User").executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
