package com.sigis.prueba.incidency.mapper;

import com.sigis.prueba.incidency.dto.LocationResponse;
import com.sigis.prueba.incidency.model.LocationModel;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationResponse toResponse(LocationModel model) {
        if (model == null) return null;

        LocationResponse dto = new LocationResponse();
        dto.setId(model.getId());
        dto.setPavilion(model.getPavilion());
        dto.setFloor(model.getFloor());
        dto.setReference(model.getReference());
        return dto;
    }
}
