package com.scamlet.todo_app.Repositories;

import com.scamlet.todo_app.Entities.User;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public class UserRepository implements IUserRepository{

    private final EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User addUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "INSERT INTO users (username, password) VALUES (:userName, SHA2(:password, 256))";
        int result = session.createNativeQuery(sql, User.class)
                .setParameter("userName", user.getUserName())
                .setParameter("password", user.getPassword())
                .executeUpdate();

        if (result >= 1) {
            return user;
        }

        return null;
    }

    @Override
    public User deleteUser(Long id) {
        Session session = entityManager.unwrap(Session.class);
        User findToDelete = session.get(User.class, id);
        if (findToDelete != null) {
            session.remove(findToDelete);
        }
        return findToDelete;
    }

    @Override
    public User loginUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        List<User> resultList = session.createNativeQuery("SELECT * FROM users u WHERE u.userName=:userName AND u.password=SHA2(:password, 256)", User.class)
                .setParameter("userName", user.getUserName())
                .setParameter("password", user.getPassword())
                .getResultList();

        if (!resultList.isEmpty()) {
            return user;
        }

        return null;
    }

    @Override
    public User updateUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "UPDATE users u SET u.userName=:userName, u.password=SHA2(:password, 256) WHERE u.id=:id";
        int result = session.createNativeQuery(sql, User.class)
                .setParameter("userName", user.getUserName())
                .setParameter("password", user.getPassword())
                .setParameter("id", user.getId())
                .executeUpdate();

        if (result >= 1) {
            return user;
        }

        return null;
    }

    @Override
    public List<User> getUserList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from User", User.class).getResultList();

    }
}
