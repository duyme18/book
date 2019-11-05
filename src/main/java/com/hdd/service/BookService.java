package com.hdd.service;

import com.hdd.model.Book;
import com.hdd.model.Category;
import com.hdd.model.Status;

public interface BookService {
    Iterable<Book> findAll();

    Book findById(Long id);

    void save(Book book);

    void remove(Long id);

    Iterable<Book> findAllByCategory(Category category);

    Iterable<Book> findAllByStatus(Status status);
}
