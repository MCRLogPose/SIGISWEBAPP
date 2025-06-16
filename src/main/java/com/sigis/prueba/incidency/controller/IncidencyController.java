package com.sigis.prueba.incidency.controller;

import com.sigis.prueba.incidency.dto.IncidencyRequest;
import com.sigis.prueba.incidency.dto.IncidencyResponse;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.incidency.service.IncidencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/incidencias")
public class IncidencyController {

    @Autowired
    private IncidencyService incidencyService;

    @PostMapping(consumes = "multipart/form-data")
    public IncidencyResponse createIncidency(@ModelAttribute IncidencyRequest request) {
        return incidencyService.createIncidency(request);
    }

    @GetMapping
    public List<IncidencyResponse> getAll() {
        return incidencyService.getAll();
    }
}
