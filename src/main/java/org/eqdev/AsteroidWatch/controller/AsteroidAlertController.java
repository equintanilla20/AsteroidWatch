package org.eqdev.AsteroidWatch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.eqdev.AsteroidWatch.service.AsteroidAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/v1/asteroid-alerting")
public class AsteroidAlertController {

    private final AsteroidAlertService asteroidAlertService;

    @Autowired
    public AsteroidAlertController(AsteroidAlertService asteroidAlertService) {
        this.asteroidAlertService = asteroidAlertService;
    }

    @PostMapping("/alert")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void alert() {
        asteroidAlertService.alert();
    }
    
    
}
