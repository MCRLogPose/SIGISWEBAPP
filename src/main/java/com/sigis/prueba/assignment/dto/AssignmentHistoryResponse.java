package com.sigis.prueba.assignment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AssignmentHistoryResponse {
    private Long id;
    private String response;
    private String state;
    private Date createdAt;
    private String username; // puedes cambiar por userId si prefieres
}
