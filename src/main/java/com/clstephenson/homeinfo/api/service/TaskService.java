package com.clstephenson.homeinfo.api.service;

import com.clstephenson.homeinfo.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAll();

    List<Task> findByPropertyId(long propertyId);

    Optional<Task> findById(long id);

    Task save(Task task);

    void deleteById(long id);

    boolean existsById(long id);
}
