package com.sigis.prueba.assignment.mapper;

import com.sigis.prueba.assignment.dto.AssignmentResponse;
import com.sigis.prueba.assignment.model.AssignmentModel;
import org.springframework.stereotype.Component;

@Component
public class AsignacionMapper {

    public AssignmentResponse toResponse(AssignmentModel model) {
        AssignmentResponse response = new AssignmentResponse();
        response.setId(model.getId());
        response.setUserId(model.getUser().getId());
        response.setIncidencyId(model.getIncidencyModel().getId());
        response.setResponse(model.getResponse());
        response.setDateAssignment(model.getDateAssignment());
        response.setState(model.getState());
        return response;
    }
}
