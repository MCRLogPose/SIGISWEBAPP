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

@RequestMapping("/api/")
public class IncidencyController {

    @Autowired
    private IncidencyService incidencyService;

    @PostMapping ("/incidencias")
    public ResponseEntity<IncidencyResponse> create(@RequestBody IncidencyRequest dto){
        IncidencyResponse created = incidencyService.createIncidency(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping ("/incidencias")
    public ResponseEntity<List<IncidencyResponse>> getAll(){
        List<IncidencyResponse> list = incidencyService.getAll();
        return ResponseEntity.ok(list);
    }
}
