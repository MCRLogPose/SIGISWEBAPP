package com.sigis.prueba.asignaciones.service;

import com.sigis.prueba.asignaciones.dto.AsignacionesPorIncidenciaResponse;
import com.sigis.prueba.asignaciones.dto.AsignadoDTO;
import com.sigis.prueba.asignaciones.repository.AsignacionRepository;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.asignaciones.model.AsignacionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// ... otros imports

@Service
public class AsignacionGroupService {
    @Autowired
    private AsignacionRepository asignacionRepository;

    public List<AsignacionesPorIncidenciaResponse> getAsignacionesAgrupadas() {
        List<AsignacionModel> asignaciones = asignacionRepository.findAll();

        Map<Long, AsignacionesPorIncidenciaResponse> agrupadas = new HashMap<>();

        for (AsignacionModel asignacion : asignaciones) {
            Long incidenciaId = asignacion.getIncidencyModel().getId();
            AsignacionesPorIncidenciaResponse dto = agrupadas.computeIfAbsent(incidenciaId, id -> {
                AsignacionesPorIncidenciaResponse nuevo = new AsignacionesPorIncidenciaResponse();
                nuevo.setIncidencyId(id);
                nuevo.setIncidencyTitle(asignacion.getIncidencyModel().getTitle());
                nuevo.setAsignados(new ArrayList<>());
                return nuevo;
            });

            AsignadoDTO asignado = new AsignadoDTO();
            asignado.setUserId(asignacion.getUser().getId());
            asignado.setUsername(asignacion.getUser().getUsername());
            asignado.setResponse(asignacion.getResponse());

            dto.getAsignados().add(asignado);
        }

        return new ArrayList<>(agrupadas.values());
    }

    public AsignacionesPorIncidenciaResponse getAsignacionesPorIncidencia(Long incidenciaId) {
        List<AsignacionModel> asignaciones = asignacionRepository.findByIncidencyModelId(incidenciaId);

        if (asignaciones.isEmpty()) {
            throw new RuntimeException("No hay asignaciones para la incidencia " + incidenciaId);
        }

        IncidencyModel incidencia = asignaciones.get(0).getIncidencyModel();

        AsignacionesPorIncidenciaResponse dto = new AsignacionesPorIncidenciaResponse();
        dto.setIncidencyId(incidencia.getId());
        dto.setIncidencyTitle(incidencia.getTitle());

        List<AsignadoDTO> asignados = asignaciones.stream().map(asignacion -> {
            AsignadoDTO asignado = new AsignadoDTO();
            UserModel user = asignacion.getUser();
            asignado.setUserId(user.getId());
            asignado.setUsername(user.getUsername());
            asignado.setResponse(asignacion.getResponse());
            return asignado;
        }).toList();

        dto.setAsignados(asignados);
        return dto;
    }
}
