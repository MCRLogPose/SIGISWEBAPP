package com.sigis.prueba.asignaciones.controller;

import com.sigis.prueba.asignaciones.dto.AsignacionRequest;
import com.sigis.prueba.asignaciones.dto.AsignacionResponse;
import com.sigis.prueba.asignaciones.dto.AsignacionesPorIncidenciaResponse;
import com.sigis.prueba.asignaciones.service.AsignacionService;
import com.sigis.prueba.asignaciones.service.AsignacionGroupService;
import com.sigis.prueba.asignaciones.service.UpdateEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AsignacionController {
    @Autowired
    private AsignacionService asignacionService;

    @Autowired
    private AsignacionGroupService asignacionGroupService;

    @Autowired
    private UpdateEstadoService updateEstadoService;

    @PostMapping("/asignacion")
    public AsignacionResponse createAsignacion(@RequestBody AsignacionRequest request) {
        return asignacionService.createAsignacion(request);
    }
    @GetMapping("/asignacion")
    public List<AsignacionResponse> getAllAsignaciones() {
        return asignacionService.getAllAsignaciones();
    }
    @GetMapping("/agrupadas")
    public ResponseEntity<List<AsignacionesPorIncidenciaResponse>> getAgrupadas() {
        return ResponseEntity.ok(asignacionGroupService.getAsignacionesAgrupadas());
    }

    @GetMapping("/incidencia/{id}")
    public ResponseEntity<AsignacionesPorIncidenciaResponse> getPorIncidencia(@PathVariable Long id) {
        return ResponseEntity.ok(asignacionGroupService.getAsignacionesPorIncidencia(id));
    }

    @PutMapping("/asignacion/{id}/respuesta")
    public ResponseEntity<AsignacionResponse> actualizarRespuesta(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String response = body.get("response");
        return ResponseEntity.ok(updateEstadoService.updateResponse(id, response));
    }

    @PutMapping("/asignacion/{id}/culminado")
    public ResponseEntity<AsignacionResponse> culminarAsignacion (@PathVariable Long id){
        return ResponseEntity.ok(updateEstadoService.culminarAsignacion(id));
    }
}
