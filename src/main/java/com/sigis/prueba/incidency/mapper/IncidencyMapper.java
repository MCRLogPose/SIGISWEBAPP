package com.sigis.prueba.incidency.mapper;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.sigis.prueba.auth.mapper.UserMapper;
import com.sigis.prueba.incidency.dto.IncidencyResponse;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.incidency.model.LocationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncidencyMapper {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private LocationMapper locationMapper;

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
        response.setUser(userMapper.toResponse(model.getUser()));
        response.setCategory(categoryMapper.toResponse(model.getCategory()));
        response.setLocation(locationMapper.toResponse(model.getLocation()));
        return response;
    }
}
