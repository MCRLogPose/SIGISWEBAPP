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
        response.setDescription(model.getDescription());
        response.setDateEmision(model.getDateEmision());
        response.setPriority(model.getPriority());
        response.setImage(model.getImage());
        response.setDateAccept(model.getDateAccept());
        response.setState(model.getState());
        response.setUserId(model.getUser().getId());
        response.setCategoryId(model.getCategory().getId());
        response.setLocationId(model.getLocation().getId());
        return response;
    }
}
