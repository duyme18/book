package com.hdd.repository;

import com.hdd.model.Status;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatusRepository extends PagingAndSortingRepository<Status, Long> {
}
