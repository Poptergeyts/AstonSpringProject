package ru.poptergeyts.astonspringproject.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.poptergeyts.astonspringproject.config.SessionFactoryConfig;
import ru.poptergeyts.astonspringproject.entity.User;
import ru.poptergeyts.astonspringproject.exception.DbFailException;

import java.util.List;

@Repository
public class UserRepository {
    private final SessionFactoryConfig sessionFactoryConfig;

    public UserRepository(SessionFactoryConfig sessionFactoryConfig) {
        this.sessionFactoryConfig = sessionFactoryConfig;
    }

    public User getByLogin(String login) throws DbFailException {
        try(Session session = sessionFactoryConfig.getSession()) {
            return session.get(User.class, login);
        } catch (HibernateException e) {
            throw new DbFailException();
        }
    }

    public void updatePassword(String login, String newPassword) throws DbFailException {
        try (Session session = sessionFactoryConfig.getSession()) {
            User user = session.get(User.class, login);
            user.setPassword(newPassword);
        } catch (HibernateException e) {
            throw new DbFailException();
        }
    }

    public void addUser(User user) throws DbFailException{
        try (Session session = sessionFactoryConfig.getSession()) {
            session.persist(user);
        } catch (HibernateException e) {
            throw new DbFailException();
        }
    }

    public List<User> getAll() throws DbFailException {
        try (Session session = sessionFactoryConfig.getSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        } catch (HibernateException e) {
            throw new DbFailException();
        }
    }
}
