package com.scamlet.todo_app.Repositories;

import com.scamlet.todo_app.Entities.Task;
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
        task.setDate(new Date());
        session.persist(task);
        return task;
    }

    @Override
    public Task deleteTask(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Task findToDelete = session.get(Task.class, id);
        if (findToDelete != null) {
            session.remove(findToDelete);
        }
        return findToDelete;
    }

    @Override
    public Task updateTask(Task task) {
        Session session = entityManager.unwrap(Session.class);
        session.merge(task);
        return task;
    }

    @Override
    public List<Task> getTaskList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Task", Task.class).getResultList();
    }
}
