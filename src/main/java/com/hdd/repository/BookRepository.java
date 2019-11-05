package com.hdd.repository;

import com.hdd.model.Book;
import com.hdd.model.Category;
import com.hdd.model.Status;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Iterable<Book> findAllByCategory(Category category);
    Iterable<Book> findAllByStatus(Status status);
}
