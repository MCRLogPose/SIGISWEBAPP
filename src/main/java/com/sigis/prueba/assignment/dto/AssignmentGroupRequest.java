package com.sigis.prueba.assignment.dto;

import lombok.Data;

import java.util.List;

@Data
public class AssignmentGroupRequest {
    private List<Long> userIds; // Cambiar de userId a una lista
    private Long incidencyId;
    private String response;
}
