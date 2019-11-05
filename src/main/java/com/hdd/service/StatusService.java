package com.hdd.service;

import com.hdd.model.Category;
import com.hdd.model.Status;

public interface StatusService {
    Iterable<Status> findAll();

    Status findById(Long id);

    void save(Status status);

    void remove(Long id);
}
