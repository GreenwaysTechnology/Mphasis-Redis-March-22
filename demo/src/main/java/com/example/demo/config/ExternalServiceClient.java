package com.example.demo.config;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ExternalServiceClient {

    @CachePut(value = "weather", key = "#zip")
    public int getWeatherInfo(int zip){
         //read db values and return
        return ThreadLocalRandom.current().nextInt(60, 100);
    }

}
