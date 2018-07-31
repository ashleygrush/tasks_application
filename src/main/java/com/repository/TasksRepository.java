package com.repository;

import com.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface TasksRepository extends CrudRepository<Task, Long>  {

    // custom query to search by User ID
    @Query(value = "select * from tasks_app.tasks where user_id = ?1", nativeQuery = true)
    Task findByUserID(Long user_id);

    // custom query to update name and email
    @Modifying
    @Transactional
    @Query(value = "update tasks_app.tasks set user_id = ?1, name = ?2, description = ?3, completed = ?4 where id = ?5", nativeQuery = true)
    int updateTask(long user_id, String name, String description, boolean completed, long id);
}
