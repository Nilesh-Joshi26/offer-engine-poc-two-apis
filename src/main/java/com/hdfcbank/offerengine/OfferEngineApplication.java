package com.hdfcbank.offerengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OfferEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfferEngineApplication.class, args);
    }
}
