package com.sigis.prueba.assignment.controller;

import com.sigis.prueba.assignment.dto.AssignmentRequest;
import com.sigis.prueba.assignment.dto.AssignmentResponse;
import com.sigis.prueba.assignment.dto.AssignmentByIncidenceResponse;
import com.sigis.prueba.assignment.service.AssignmentService;
import com.sigis.prueba.assignment.service.AssignmentGroupService;
import com.sigis.prueba.assignment.service.UpdateEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class AsignacionController {
    @Autowired
    private AssignmentService asignacionService;

    @Autowired
    private AssignmentGroupService asignacionGroupService;

    @Autowired
    private UpdateEstadoService updateEstadoService;

    @PostMapping("/assignments")
    public AssignmentResponse createAsignacion(@RequestBody AssignmentRequest request) {
        return asignacionService.createAsignacion(request);
    }
    @GetMapping("/assignment")
    public List<AssignmentResponse> getAllAsignaciones() {
        return asignacionService.getAllAsignaciones();
    }
    @GetMapping("/grouped")
    public ResponseEntity<List<AssignmentByIncidenceResponse>> getAgrupadas() {
        return ResponseEntity.ok(asignacionGroupService.getAsignacionesAgrupadas());
    }

    @GetMapping("/incidence/{id}")
    public ResponseEntity<AssignmentByIncidenceResponse> getPorIncidencia(@PathVariable Long id) {
        return ResponseEntity.ok(asignacionGroupService.getAsignacionesPorIncidencia(id));
    }

    @PutMapping("/assignment/{id}/response")
    public ResponseEntity<AssignmentResponse> actualizarRespuesta(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String response = body.get("response");
        return ResponseEntity.ok(updateEstadoService.updateResponse(id, response));
    }

    @PutMapping("/assignment/{id}/culminated")
    public ResponseEntity<AssignmentResponse> culminarAsignacion (@PathVariable Long id){
        return ResponseEntity.ok(updateEstadoService.culminarAsignacion(id));
    }
}
