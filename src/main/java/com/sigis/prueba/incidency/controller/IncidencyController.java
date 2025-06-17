package com.sigis.prueba.incidency.controller;

import com.sigis.prueba.asignaciones.dto.AsignacionResponse;
import com.sigis.prueba.asignaciones.service.AsignacionService;
import com.sigis.prueba.incidency.dto.IncidencyRequest;
import com.sigis.prueba.incidency.dto.IncidencyResponse;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.incidency.service.IncidencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/incidencias")
@Tag(name = "Incidencias")
public class IncidencyController {

    @Autowired
    private IncidencyService incidencyService;
    @Autowired
    private AsignacionService asignacionService;

    @PostMapping(consumes = "multipart/form-data")
    public IncidencyResponse createIncidency(@ModelAttribute IncidencyRequest request) {
        return incidencyService.createIncidency(request);
    }

    @GetMapping
    public List<IncidencyResponse> getAll() {
        return incidencyService.getAll();
    }
    @GetMapping("/culminadas")
    @Operation(summary = "Listar incidencias culminadas")
    public List<IncidencyResponse> getCulminadas() {
        return incidencyService.getIncidenciasCulminadas();
    }

    @GetMapping("/completadas")
    public List<IncidencyResponse> getCompletadas() {
        return incidencyService.getIncidenciasCompletadas();
    }

}
