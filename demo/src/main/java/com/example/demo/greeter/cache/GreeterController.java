package com.example.demo.greeter.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeter")
public class GreeterController {

    @Autowired
    private GreeterService greeterService;

    @GetMapping("/greetme")
    public ResponseEntity sayHello() {
        return ResponseEntity.ok(greeterService.sayHello());
    }
}
