package com.hdd.service.impl;

import com.hdd.model.Status;
import com.hdd.repository.StatusRepository;
import com.hdd.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;

public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Iterable<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status findById(Long id) {
        return statusRepository.findOne(id);
    }

    @Override
    public void save(Status status) {
        statusRepository.save(status);
    }

    @Override
    public void remove(Long id) {
        statusRepository.delete(id);
    }
}
