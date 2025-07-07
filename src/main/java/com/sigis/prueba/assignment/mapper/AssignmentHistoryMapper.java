package com.sigis.prueba.assignment.mapper;

import com.sigis.prueba.assignment.dto.AssignmentHistoryResponse;
import com.sigis.prueba.assignment.model.AssignmentHistoryModel;
import org.springframework.stereotype.Component;

@Component
public class AssignmentHistoryMapper {
    public AssignmentHistoryResponse toResponse(AssignmentHistoryModel model) {
        AssignmentHistoryResponse dto = new AssignmentHistoryResponse();
        dto.setId(model.getId());
        dto.setState(model.getState());
        dto.setResponse(model.getResponse());
        dto.setCreatedAt(model.getCreatedAt());

        if (model.getUser() != null) {
            dto.setUsername(model.getUser().getUsername());
        }

        return dto;
    }
}
