package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.createSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session
                .createSQLQuery("Create table IF NOT EXISTS Users (id bigserial primary key," +
                        " name text not null, " +
                        "lastName text not null, " +
                        "age smallint not null)")
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.createSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.createSQLQuery("Drop table IF EXISTS Users").executeUpdate();
        transaction.commit();
        session.close();
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.createSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.createSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User user = session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.createSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        List<User> usersList = session.createCriteria(User.class).list();
        transaction.commit();
        session.close();
        return usersList;
    }


    @Override
    public void cleanUsersTable() {
        Session session = Util.createSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.createSQLQuery("delete from Users").executeUpdate();
        transaction.commit();
        session.close();
    }
}

