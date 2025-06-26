package com.sigis.prueba.incidency.controller;

import com.sigis.prueba.assignment.service.AssignmentService;
import com.sigis.prueba.auth.dto.UserResponse;
import com.sigis.prueba.auth.service.AuthService;
import com.sigis.prueba.incidency.dto.IncidencyRequest;
import com.sigis.prueba.incidency.dto.IncidencyResponse;
import com.sigis.prueba.incidency.service.IncidencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@Tag(name = "Incidents")
public class IncidencyController {

    @Autowired
    private IncidencyService incidencyService;
    @Autowired
    private AssignmentService asignacionService;
    @Autowired
    private AuthService authService;

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

    @GetMapping("/completed")
    public List<IncidencyResponse> getCompletadas() {
        return incidencyService.getIncidenciasCompletadas();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = authService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
