package com.sigis.prueba.asignaciones.mapper;

import com.sigis.prueba.asignaciones.dto.AsignacionResponse;
import com.sigis.prueba.asignaciones.model.AsignacionModel;
import org.springframework.stereotype.Component;

@Component
public class AsignacionMapper {

    public AsignacionResponse toResponse(AsignacionModel model) {
        AsignacionResponse response = new AsignacionResponse();
        response.setId(model.getId());
        response.setUserId(model.getUser().getId());
        response.setIncidencyId(model.getIncidencyModel().getId());
        response.setResponse(model.getResponse());
        response.setFechaAsignacion(model.getFechaAsignacion());
        response.setEstado(model.getEstado());
        return response;
    }
}
