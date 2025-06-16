package com.sigis.prueba.incidency.mapper;

import com.sigis.prueba.incidency.dto.IncidencyResponse;
import com.sigis.prueba.incidency.model.IncidencyModel;
import org.springframework.stereotype.Component;

@Component
public class IncidencyMapper {

    public IncidencyResponse toResponse(IncidencyModel model) {
        IncidencyResponse response = new IncidencyResponse();
        response.setId(model.getId());
        response.setTitle(model.getTitle());
        response.setDescripcion(model.getDescripcion());
        response.setFechaEmision(model.getFechaEmision());
        response.setPrioridad(model.getPrioridad());
        response.setImagen(model.getImagen());
        response.setFechaAccept(model.getFechaAccept());
        response.setEstado(model.getEstado());
        response.setUserId(model.getUser().getId());
        response.setCategoriaId(model.getCategoria().getId());
        response.setUbicacionId(model.getUbicacion().getId());
        return response;
    }
}
