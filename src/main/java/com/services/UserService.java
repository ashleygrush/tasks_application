package com.services;

import com.exceptions.CustomDatabaseExceptions;
import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    // finds all
    public List<User> findAllUsers() throws Exception {

        List<User> users;
        try {
            users = (List<User>) userRepository.findAll();
        } catch (Exception e) {
            throw e;
        }

        return users;
    }


    // finds by id
    public User findUserByID(long id) throws Exception {
        User user;

        try {
            user = userRepository.findById(id).get();
        } catch (Exception e) {
            throw e;
        }

        return user;
    }


    // creates new user
    public User createUser(User user) throws Exception {

        try {
            User u = userRepository.save(user);
            return userRepository.findByEmail(u.getEmail());
        } catch (Exception e) {
            throw e;
        }
    }

    // updates user
    public User updateUser(User user) throws Exception, CustomDatabaseExceptions {

        try {
            int i = userRepository.updateUser(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getId());

            if (i < 1) {
                throw new CustomDatabaseExceptions("unable to update user.");
            }
            return userRepository.findById(user.getId()).get();
        } catch (Exception e) {
            throw new CustomDatabaseExceptions(e.getMessage());
        }
    }

    // delete user
    public boolean deleteUser(Long id) throws Exception {

        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}