package com.hdd.controller;

import com.hdd.model.Book;
import com.hdd.model.Category;
import com.hdd.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class BookRestController {
    @Autowired
    private BookService bookService;

    //-------------------Retrieve All Categories----------------------------------------------------

    @RequestMapping(value = "/books/", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listAllBooks() {
        List<Book> books = (List<Book>) bookService.findAll();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    //-------------------Retrieve Single category--------------------------------------------------------

    @RequestMapping(value = "/books/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getCategory(@PathVariable("id") long id) {
        System.out.println("Fetching Book with id " + id);
        Book book = bookService.findById(id);
        if (book == null) {
            System.out.println("Book with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    //-------------------Create a category--------------------------------------------------------

    @RequestMapping(value = "/books/", method = RequestMethod.POST)
    public ResponseEntity<Void> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Book " + book.getName());
        bookService.save(book);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/books/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a category --------------------------------------------------------

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateCategory(@PathVariable("id") long id, @RequestBody Book book) {
        System.out.println("Updating Book " + id);

        Book currentBook = bookService.findById(id);

        if (currentBook == null) {
            System.out.println("Book with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentBook.setPublisher(book.getPublisher());
        currentBook.setAuthor(book.getAuthor());
        currentBook.setName(book.getName());
        currentBook.setId(book.getId());

        bookService.save(currentBook);
        return new ResponseEntity<>(currentBook, HttpStatus.OK);
    }

    //------------------- Delete a Category --------------------------------------------------------

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteBook(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Book with id " + id);

        Book book = bookService.findById(id);
        if (book == null) {
            System.out.println("Unable to delete. Book with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        bookService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
