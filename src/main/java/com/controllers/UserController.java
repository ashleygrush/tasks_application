package com.controllers;

import com.exceptions.CustomDatabaseExceptions;
import com.model.CustomResponseObject;
import com.model.User;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    // get all users
    @GetMapping()
    public CustomResponseObject getUsers() throws Exception {

        List<User> users = userService.findAllUsers();
        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(users);
        obj.setStatusCode(200);

        return obj;

    }


    // get user by id
    @GetMapping("/{id}")
    public CustomResponseObject <User> findUserByID(@PathVariable ("id") long id) throws Exception {

    User users = userService.findUserByID(id);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(users);
        obj.setStatusCode(200);

        return obj;
    }


    // create new user
    @PostMapping
    public CustomResponseObject<User> createUser(@Valid @RequestBody User user) throws Exception {

        User u = userService.createUser(user);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(user);
        obj.setStatusCode(500);

        return obj;

    }


    // update user
    @PutMapping
    public CustomResponseObject<User> updateUser(@Valid @RequestBody User user) throws Exception {

        User u = userService.updateUser(user);

        CustomResponseObject obj = new CustomResponseObject();
        obj.setData(user);
        obj.setStatusCode(500);

        return obj;
    }


    // delete user by ID
    @DeleteMapping("/{id}")
    public CustomResponseObject<String> deleteUser(@PathVariable("id") Long id) throws Exception {

        boolean success = userService.deleteUser(id);
        CustomResponseObject obj = new CustomResponseObject();

        if (success) {
            obj.setData("user removed.");
            obj.setStatusCode(200);
            return obj;
        }
        throw new CustomDatabaseExceptions("Unable to remove user.");
    }


}
