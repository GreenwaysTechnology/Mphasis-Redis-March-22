package com.example.demo.greeter.cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class GreeterService {

    @Cacheable("greet:message")
    public String sayHello() {
        System.out.println("Say Hello is called");
        return "Hello";
    }
}
