package com.sigis.prueba.assignment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AssignmentResponse {
    private Long id;
    private Long userId;
    private Long incidencyId;
    private String response;
    private String state;
    private Date dateAssignment;
}
