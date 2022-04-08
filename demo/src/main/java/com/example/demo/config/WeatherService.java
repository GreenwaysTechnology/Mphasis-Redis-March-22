package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class WeatherService {

    @Autowired
    private ExternalServiceClient client;

    @Cacheable(value = "weather", key = "#zip")
    public int getInfo(int zip) {
        System.out.println("Weather Service api is called");
        return 0;
    }

    @Scheduled(fixedRate = 10_000)
    public void update() {
        System.out.println("updating weather");
        IntStream.rangeClosed(1, 5)
                .forEach(this.client::getWeatherInfo);
    }

}
