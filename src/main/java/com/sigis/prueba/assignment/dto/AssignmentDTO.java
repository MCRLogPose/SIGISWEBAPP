package com.sigis.prueba.assignment.dto;

import lombok.Data;

@Data
public class AssignmentDTO {
    private Long assignmentId;
    private Long userId;
    private String username;
    private String response;
}
