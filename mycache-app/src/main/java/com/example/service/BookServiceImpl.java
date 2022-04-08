package com.example.service;

import com.example.entity.Book;
import com.example.repo.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = "books")
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        logger.info("adding book with id - {}", book.getId());
        return bookRepository.save(book);
    }


    @Override
    @CachePut(key = "#book.id")
    public Book updateBook(Book book) {
        bookRepository.updateAddress(book.getId(), book.getName());
        logger.info("book updated with new name");
        return book;
    }

    //   @Override
    //now this method will executed only if cache miss, if cache hit, method wont be executed
//    @Cacheable(cacheNames = {"books", "isbns"})
//    public Book getBook(long id) {
//        logger.info("fetching book from db");
//        Optional<Book> book = bookRepository.findById(id);
//        if (book.isPresent()) {
//            return book.get();
//        } else {
//            return new Book();
//        }
//    }

    @Override
    @Cacheable(key = "#id")
    public Book getBook(long id) {
        logger.info("fetching book from db");
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            return new Book();
        }
    }
    //conditional : cache only books whose id greater than 5
//    @Override
//    @Cacheable(cacheNames = "books", condition = "#id>5 ")
//    public Book getBook(long id) {
//        logger.info("fetching book from db" + id);
//        Optional<Book> book = bookRepository.findById(id);
//        if (book.isPresent()) {
//            return book.get();
//        } else {
//            return new Book();
//        }
//    }

//    @Override
//    @Cacheable(cacheNames = "books",  unless = "#id==3")
//    public Book getBook(long id) {
//        logger.info("fetching book from db" + id);
//        Optional<Book> book = bookRepository.findById(id);
//        if (book.isPresent()) {
//            return book.get();
//        } else {
//            return new Book();
//        }
//    }

    @Override
    @CacheEvict(key = "#id", allEntries = true)
    public String deleteBook(long id) {
        bookRepository.deleteById(id);
        return "Book deleted";
    }
}
