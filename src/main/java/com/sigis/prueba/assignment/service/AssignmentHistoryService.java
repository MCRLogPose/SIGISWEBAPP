package com.sigis.prueba.assignment.service;

import com.sigis.prueba.assignment.dto.AssignmentHistoryRequest;
import com.sigis.prueba.assignment.dto.AssignmentHistoryResponse;
import com.sigis.prueba.assignment.model.AssignmentHistoryModel;
import com.sigis.prueba.assignment.model.AssignmentModel;
import com.sigis.prueba.assignment.repository.AssignmentHistoryRepository;
import com.sigis.prueba.assignment.repository.AssignmentRepository;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.auth.repository.UserRepository;
import com.sigis.prueba.auth.security.AuthenticatedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AssignmentHistoryService {

    @Autowired
    private AssignmentHistoryRepository historyRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticatedUserProvider authenticatedUserProvider;

    public AssignmentHistoryResponse saveHistory(AssignmentHistoryRequest request) {
        AssignmentModel assignment = assignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        UserModel user = authenticatedUserProvider.getCurrentUser();

        AssignmentHistoryModel history = new AssignmentHistoryModel();
        history.setAssignment(assignment);
        history.setUser(user);
        history.setState(request.getState());
        history.setResponse(request.getResponse());
        history.setCreatedAt(new Date());

        AssignmentHistoryModel saved = historyRepository.save(history);

        // Map manual (puedes usar MapStruct o ModelMapper si prefieres)
        AssignmentHistoryResponse dto = new AssignmentHistoryResponse();
        dto.setId(saved.getId());
        dto.setState(saved.getState());
        dto.setResponse(saved.getResponse());
        dto.setCreatedAt(saved.getCreatedAt());
        dto.setUsername(user.getUsername());

        return dto;
    }


    public List<AssignmentHistoryResponse> getByAssignmentId(Long assignmentId) {
        List<AssignmentHistoryModel> historyList = historyRepository.findByAssignmentId(assignmentId);

        return historyList.stream().map(history -> {
            AssignmentHistoryResponse dto = new AssignmentHistoryResponse();
            dto.setId(history.getId());
            dto.setResponse(history.getResponse());
            dto.setState(history.getState());
            dto.setCreatedAt(history.getCreatedAt());
            dto.setUsername(history.getUser().getUsername());
            return dto;
        }).toList();
    }
}
