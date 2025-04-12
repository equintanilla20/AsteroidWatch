package org.eqdev.AsteroidWatch.service;

import java.time.LocalDate;
import java.util.List;

import org.eqdev.AsteroidWatch.client.NasaClient;
import org.eqdev.AsteroidWatch.dto.Asteroid;
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
        final List<Asteroid> asteroidList = nasaClient.getNeoAsteroids(fromDate, toDate);
        log.info("Received {} asteroids from NASA API", asteroidList.size());
        
        // If there are any hazardous asteroids, send an alert
        final List<Asteroid> dangerousAsteroids = asteroidList.stream()
                .filter(asteroid -> asteroid.getIsPotentiallyHazardous().equalsIgnoreCase("true")) // (Asteroid::isPotentiallyHazardous)
                .toList();
        log.info("Found {} potentially hazardous asteroids", dangerousAsteroids.size());

        // Create an alert and place on Kafka topic
        dangerousAsteroids.forEach(asteroid -> {
            log.info("Sending asteroid alert to Kafka: {}", asteroid);
        });
    }
}
