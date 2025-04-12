package org.eqdev.AsteroidWatch.client;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.eqdev.AsteroidWatch.dto.Asteroid;
import org.eqdev.AsteroidWatch.dto.response.NasaNeoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NasaClient {

    @Value("${nasa.api.key}")
    private String nasaApiKey;
    
    @Value("${nasa.neo.api.url}")
    private String nasaApiUrl;

    public List<Asteroid> getNeoAsteroids(final LocalDate fromDate, final LocalDate toDate) {
        final RestTemplate restTemplate = new RestTemplate();
        final NasaNeoResponse nasaNeoResponse = restTemplate.getForObject(getUrl(fromDate, toDate), NasaNeoResponse.class);

        List<Asteroid> asteroids = new ArrayList<>();
        
        if (nasaNeoResponse != null) {
            asteroids = nasaNeoResponse
                    .getNearEarthObjects()
                    .values()
                    .stream()
                    .flatMap(List::stream)
                    .toList();
        }
        return asteroids;
    }

    public String getUrl(final LocalDate fromDate, final LocalDate toDate) {
        String apiUrl = UriComponentsBuilder.fromUriString(nasaApiUrl)
                .queryParam("start_date", fromDate.toString())
                .queryParam("end_date", toDate.toString())
                .queryParam("api_key", nasaApiKey)
                .toUriString();
        return apiUrl;
    }
}
