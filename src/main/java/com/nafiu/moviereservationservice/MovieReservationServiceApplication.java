package com.nafiu.moviereservationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MovieReservationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieReservationServiceApplication.class, args);
    }
}
