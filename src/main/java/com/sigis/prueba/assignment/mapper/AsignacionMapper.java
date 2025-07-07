package com.sigis.prueba.assignment.mapper;

import com.sigis.prueba.assignment.dto.AssignmentGroupResponse;
import com.sigis.prueba.assignment.dto.AssignmentHistoryResponse;
import com.sigis.prueba.assignment.dto.AssignmentResponse;
import com.sigis.prueba.assignment.model.AssignmentModel;
import com.sigis.prueba.assignment.repository.AssignmentRepository;
import com.sigis.prueba.auth.dto.UserResponse;
import com.sigis.prueba.auth.mapper.UserMapper;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.incidency.mapper.IncidencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AsignacionMapper {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IncidencyMapper incidencyMapper;

    @Autowired
    private AssignmentHistoryMapper assignmentHistoryMapper;

    @Autowired
    private AssignmentRepository assignmentRepository;

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
    public AssignmentGroupResponse toResponseGroup(AssignmentModel model) {
        AssignmentGroupResponse response = new AssignmentGroupResponse();

        response.setId(model.getId());
        response.setIncidency(incidencyMapper.toResponse(model.getIncidencyModel()));
        response.setResponse(model.getResponse());
        response.setDateAssignment(model.getDateAssignment());
        response.setState(model.getState());

        // Esto solo aplica si quieres dejar mapping unitario (ej: usuario individual)
        if (model.getUser() != null) {
            response.setUsers(List.of(userMapper.toResponse(model.getUser())));
        }

        if (model.getHistory() != null) {
            List<AssignmentHistoryResponse> historyList = model.getHistory().stream()
                    .map(assignmentHistoryMapper::toResponse)
                    .collect(Collectors.toList());
            response.setHistory(historyList);
        }

        return response;
    }

}
