package com.sigis.prueba.assignment.dto;

import com.sigis.prueba.auth.dto.UserResponse;
import com.sigis.prueba.incidency.dto.IncidencyResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class AssignmentGroupResponse {
    private Long id;
    private List<UserResponse> users;
    private IncidencyResponse incidency;
    private String response;
    private Date dateAssignment;
    private String state;
    private List<AssignmentHistoryResponse> history; // <-- CAMBIO A LISTA
}
