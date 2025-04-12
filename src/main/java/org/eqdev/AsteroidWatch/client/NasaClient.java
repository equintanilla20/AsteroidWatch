package org.eqdev.AsteroidWatch.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NasaClient {

    @Value("${nasa.api.key}")
    private String apiKey;
    
    @Value("${nasa.neo.api.url}")
    private String apiUrl;

    public List<Asteroid> getNeoAsteroids(final LocalDate fromDate, final LocalDate toDate) {
        final RestTemplate restTemplate = new RestTemplate();
        final NasaNeoResponse nasaNeoResponse = restTemplate.getForObject(getUrl(fromDate, toDate), NasaNeoResponse.class);
    }

    public String getUrl(final LocalDate fromDate, final LocalDate toDate) {
        String apiUrl = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("start_date", fromDate.toString())
                .queryParam("end_date", toDate.toString())
                .queryParam("api_key", apiKey)
                .toUriString();
        return apiUrl;
    }
}
