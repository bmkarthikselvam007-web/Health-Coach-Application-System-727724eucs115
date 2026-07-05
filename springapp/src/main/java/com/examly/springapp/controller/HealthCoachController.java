package com.examly.springapp.controller;

import com.examly.springapp.model.HealthCoach;
import com.examly.springapp.service.HealthCoachService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HealthCoachController {

    private final HealthCoachService healthCoachService;

    public HealthCoachController(HealthCoachService healthCoachService) {
        this.healthCoachService = healthCoachService;
    }

    @PostMapping("/addCoach")
    public HealthCoach addCoach(@RequestBody HealthCoach coach) {
        return healthCoachService.addCoach(coach);
    }

    @GetMapping("/getAllCoaches")
    public List<HealthCoach> getAllCoaches() {
        return healthCoachService.getAllCoaches();
    }
}