package com.sigis.prueba.incidency.controller;

import com.sigis.prueba.incidency.dto.UbicacionRequest;
import com.sigis.prueba.incidency.model.LocationModel;
import com.sigis.prueba.incidency.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicacion")
public class LocationController {
    @Autowired
    private UbicacionService ubicacionService;

    @PostMapping
    public ResponseEntity<LocationModel> create (@RequestBody UbicacionRequest request){
        LocationModel saved= ubicacionService.createUbicacion(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<LocationModel> getAll(){
        return ubicacionService.getAll();
    }
}
