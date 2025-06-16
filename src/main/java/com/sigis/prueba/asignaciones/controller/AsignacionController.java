package com.sigis.prueba.asignaciones.controller;

import com.sigis.prueba.asignaciones.dto.AsignacionRequest;
import com.sigis.prueba.asignaciones.dto.AsignacionResponse;
import com.sigis.prueba.asignaciones.service.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AsignacionController {
    @Autowired
    private AsignacionService asignacionService;

    @PostMapping("/asignacion")
    public AsignacionResponse createAsignacion(@RequestBody AsignacionRequest request) {
        return asignacionService.createAsignacion(request);
    }
    @GetMapping("/asignacion")
    public List<AsignacionResponse> getAllAsignaciones() {
        return asignacionService.getAllAsignaciones();
    }
}
