package com.sigis.prueba.assignment.dto;
import lombok.Data;

@Data
public class AssignmentHistoryRequest {
    private String response;
    private String state;
    private Long assignmentId;
    private Long userId;
}