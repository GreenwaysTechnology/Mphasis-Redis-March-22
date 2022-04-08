package com.example;

import com.example.entity.Book;
import com.example.repo.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class MycacheAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycacheAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(BookRepository bookRepository) {
        return args -> {
            Book book = null;
            for (int i = 0; i < 10; i++) {
                book = new Book();
                book.setAuthor("Author " + i);
                book.setCategory("Caching " + i);
                book.setEdition(i + "nd edition ");
                book.setName("Caching in Action " + i);
                book.setPublisher("my Publisher " + i);
                bookRepository.save(book);
            }
            bookRepository.findAll().forEach(System.out::println);

        };
    }
}
