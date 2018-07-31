package com.repository;

import com.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {

    // custom query to find user by email
    User findByEmail(String email);

    // custom query to update name and email
    @Modifying
    @Transactional
    @Query(value = "update tasks_app.users set first_name = ?1, last_name = ?2, email = ?3 where id = ?4", nativeQuery = true)
    int updateUser(String fist_name, String last_name, String email, long id);

}
