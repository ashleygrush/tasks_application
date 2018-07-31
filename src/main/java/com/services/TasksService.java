package com.services;

import com.exceptions.CustomDatabaseExceptions;
import com.model.Task;
import com.repository.TasksRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    // finds all
    @Cacheable(value = "tasks") // preload tasks into cache
    public List<Task> findAllTasks() throws Exception {

        List<Task> tasks;

        try {
            tasks = (List<Task>) tasksRepository.findAll();
        } catch (Exception e) {
            throw e;
        }

        return tasks;
    }


    // finds task by ID
    @Cacheable(value = "tasks", key = "#id") // puts ID key in the cache
    public Task findTaskByID(Long id) throws Exception {
        Task task;
        try {
            task = tasksRepository.findById(id).get();
            return task;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param user_id
     * @return
     * @throws Exception
     */
    // finds task by User_ID
    @Cacheable(value = "tasks", key = "user_id")
    public Task findTaskByUserID(Long user_id) throws Exception {

        // demonstrates a long call/slow connection to server
        Thread.sleep(5000);

        Task task;
        try {
            task = tasksRepository.findByUserID(user_id);
            return task;
        } catch (Exception e) {
            throw e;
        }
    }


    // creates new task
//    @CacheEvict(allEntries = true) // clears cache completely
    @CachePut(value = "tasks", key = "#task.id") // finds by each id
    public Task createTask(Task task) throws Exception{

        try {
            Task t = tasksRepository.save(task);
            return t;
        } catch (Exception e) {
            throw e;
        }
    }


    // updates task by ID
    @CachePut(value = "tasks", key = "#task.id") // finds by each id
    public Task updateTask(Task task) throws Exception, CustomDatabaseExceptions {

        try {
            int i = tasksRepository.updateTask(task.getUser_id(), task.getName(), task.getDescription(), task.getCompleted(), task.getId());

            if (i < 1) {
                throw new CustomDatabaseExceptions("unable to update user.");
            }
            return tasksRepository.findById(task.getId()).get();
        } catch (Exception e) {
            throw new CustomDatabaseExceptions(e.getMessage());
        }
    }


    // delete task by ID
    @CacheEvict(value = "tasks", key = "#id") // will return records in the cache
    public boolean deleteTask(Long id) throws Exception {

        try {
            tasksRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

}
