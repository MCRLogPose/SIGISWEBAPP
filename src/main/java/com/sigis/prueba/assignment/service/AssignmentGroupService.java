package com.sigis.prueba.assignment.service;

import com.sigis.prueba.assignment.dto.AssignmentByIncidenceResponse;
import com.sigis.prueba.assignment.dto.AssignmentDTO;
import com.sigis.prueba.assignment.repository.AssignmentRepository;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.assignment.model.AssignmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// ... otros imports

@Service
public class AssignmentGroupService {
    @Autowired
    private AssignmentRepository asignacionRepository;

    public List<AssignmentByIncidenceResponse> getAsignacionesAgrupadas() {
        List<AssignmentModel> asignaciones = asignacionRepository.findAll();

        Map<Long, AssignmentByIncidenceResponse> agrupadas = new HashMap<>();

        for (AssignmentModel asignacion : asignaciones) {
            Long incidenciaId = asignacion.getIncidencyModel().getId();
            AssignmentByIncidenceResponse dto = agrupadas.computeIfAbsent(incidenciaId, id -> {
                AssignmentByIncidenceResponse nuevo = new AssignmentByIncidenceResponse();
                nuevo.setIncidencyId(id);
                nuevo.setIncidencyTitle(asignacion.getIncidencyModel().getTitle());
                nuevo.setAssigned(new ArrayList<>());
                return nuevo;
            });

            AssignmentDTO asignado = new AssignmentDTO();
            asignado.setUserId(asignacion.getUser().getId());
            asignado.setUsername(asignacion.getUser().getUsername());
            asignado.setResponse(asignacion.getResponse());

            dto.getAssigned().add(asignado);
        }

        return new ArrayList<>(agrupadas.values());
    }

    public AssignmentByIncidenceResponse getAsignacionesPorIncidencia(Long incidenciaId) {
        List<AssignmentModel> asignaciones = asignacionRepository.findByIncidencyModelId(incidenciaId);

        if (asignaciones.isEmpty()) {
            throw new RuntimeException("No hay asignaciones para la incidencia " + incidenciaId);
        }

        IncidencyModel incidencia = asignaciones.get(0).getIncidencyModel();

        AssignmentByIncidenceResponse dto = new AssignmentByIncidenceResponse();
        dto.setIncidencyId(incidencia.getId());
        dto.setIncidencyTitle(incidencia.getTitle());

        List<AssignmentDTO> asignados = asignaciones.stream().map(asignacion -> {
            AssignmentDTO asignado = new AssignmentDTO();
            UserModel user = asignacion.getUser();
            asignado.setUserId(user.getId());
            asignado.setUsername(user.getUsername());
            asignado.setResponse(asignacion.getResponse());
            return asignado;
        }).toList();

        dto.setAssigned(asignados);
        return dto;
    }
}
