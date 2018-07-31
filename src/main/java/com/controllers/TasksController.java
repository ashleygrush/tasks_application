package com.controllers;

import com.exceptions.CustomDatabaseExceptions;
import com.model.CustomResponseObject;
import com.model.Task;
import com.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    TasksService tasksService;


    // find all tasks
    @GetMapping
    public CustomResponseObject getTasks() throws Exception {

        List <Task> tasks = tasksService.findAllTasks();

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(tasks);
        obj.setStatusCode(200);

        return obj;
    }

    // get task by ID
    @GetMapping("/{id}")
    public CustomResponseObject findTaskByID(@PathVariable("id") Long id) throws Exception {

        Task task = tasksService.findTaskByID(id);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(task);
        obj.setStatusCode(200);

        return obj;
    }


    // get task by User ID
    @GetMapping("/user/{user_id}")
    public CustomResponseObject findTaskByUserID(@PathVariable("user_id") Long user_id) throws Exception {

        Task task = tasksService.findTaskByUserID(user_id);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(task);
        obj.setStatusCode(200);

        return obj;
    }


    // create new task
    @PostMapping
    public CustomResponseObject <Task> createTask(@Valid @RequestBody Task task) throws Exception {

        task = tasksService.createTask(task);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(task);
        obj.setStatusCode(200);

        return obj;
    }


    // updates task by ID
    @PutMapping
    public CustomResponseObject<Task> updateTask(@Valid @RequestBody Task task) throws Exception {

        Task t = tasksService.updateTask(task);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(task);
        obj.setStatusCode(200);

        return obj;
    }


    // deletes task by ID
    @DeleteMapping("/{id}")
    public CustomResponseObject<String> deleteTask(@PathVariable("id") Long id) throws Exception {

        boolean success = tasksService.deleteTask(id);
        CustomResponseObject obj = new CustomResponseObject();

        if (success) {
            obj.setData("task successfully removed");
            obj.setStatusCode(200);
            return obj;
        }
        throw new CustomDatabaseExceptions("unable to remove task.");
    }


}

