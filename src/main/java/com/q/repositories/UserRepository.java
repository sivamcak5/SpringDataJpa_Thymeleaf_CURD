package com.q.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.q.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLastName(String lastName);
    
}
