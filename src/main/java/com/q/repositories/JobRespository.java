package com.q.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.q.entities.Job;


public interface JobRespository extends CrudRepository<Job, Long> {

    List<Job> findByLocation(String location);
    
}

