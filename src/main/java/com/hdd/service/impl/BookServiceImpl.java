package com.hdd.service.impl;

import com.hdd.model.Book;
import com.hdd.model.Category;
import com.hdd.model.Status;
import com.hdd.repository.BookRepository;
import com.hdd.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public Iterable<Book> findAllByCategory(Category category) {
        return bookRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Book> findAllByStatus(Status status) {
        return bookRepository.findAllByStatus(status);
    }
}
