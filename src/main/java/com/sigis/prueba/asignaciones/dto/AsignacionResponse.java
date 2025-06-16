package com.sigis.prueba.asignaciones.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AsignacionResponse {
    private Long id;
    private Long userId;
    private Long incidencyId;
    private String response;
    private Date fechaAsignacion;
}
