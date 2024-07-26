package com.scamlet.todo_app.Repositories;

import com.scamlet.todo_app.Entities.Task;
import com.scamlet.todo_app.Entities.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class TaskRepository implements  ITaskRepository {

    private final EntityManager entityManager;

    @Autowired
    public TaskRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Task addTask(Task task) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "INSERT INTO tasks (title, description, status, date) VALUES (:title, :description, :status, NOW())";
        int result = session.createNativeQuery(sql)
                .setParameter("title", task.getTitle())
                .setParameter("description", task.getDescription())
                .setParameter("status", task.isStatus())
                .executeUpdate();

        if (result >= 1) {
            task.setDate(new Date());
            return task;
        }

        return null;
    }

    @Override
    public Task deleteTask(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Task findToDelete = session.get(Task.class, id);
        if (findToDelete != null) {
            session.remove(findToDelete);
            return findToDelete;
        }
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        Session session = entityManager.unwrap(Session.class);
        String sql = "UPDATE tasks SET title=:title, description=:description, status=:status, date=:date WHERE id=:id";
        int result = session.createNativeQuery(sql)
                .setParameter("title", task.getTitle())
                .setParameter("description", task.getDescription())
                .setParameter("status", task.isStatus())
                .setParameter("date", task.getDate())
                .executeUpdate();

        if (result >= 1) {
            return task;
        }

        return null;
    }

    @Override
    public List<Task> getTaskList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Task").getResultList();
    }
}
