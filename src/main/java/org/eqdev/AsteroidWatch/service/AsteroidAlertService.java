package org.eqdev.AsteroidWatch.service;

import java.time.LocalDate;

import org.eqdev.AsteroidWatch.client.NasaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsteroidAlertService {
    
    private final NasaClient nasaClient;

    @Autowired
    public AsteroidAlertService(NasaClient nasaClient) {
        this.nasaClient = nasaClient;
    }

    public void alert() {
        log.info("Alert service called");

        // From and to date
        final LocalDate fromDate = LocalDate.now();
        final LocalDate toDate = LocalDate.now().plusDays(7);

        // Call NASA API to get asteroid data
        log.info("Fetching asteroid data from NASA API for dates: {} to {}", fromDate, toDate);
        nasaClient.getNeoAsteroids(fromDate, toDate);
        
        // If there are any hazardous asteroids, send an alert

        // Create an alert and place on Kafka topic
    }
}
