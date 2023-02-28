package com.app.clientsdataapi.controller;

import com.app.clientsdataapi.entity.Gender;
import com.app.clientsdataapi.service.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/occupations")
public class OccupationController {

    private final OccupationService occupationsService;

    @Autowired
    public OccupationController(OccupationService occupationsService) {
        this.occupationsService = occupationsService;
    }

    @GetMapping("/male")
    public ResponseEntity<?> maleOccupationsList() {
        return ResponseEntity.ok(occupationsService.getOccupations(Gender.MALE));
    }

    @GetMapping("/female")
    public ResponseEntity<?> femaleOccupationsList() {
        return ResponseEntity.ok(occupationsService.getOccupations(Gender.FEMALE));
    }
}
