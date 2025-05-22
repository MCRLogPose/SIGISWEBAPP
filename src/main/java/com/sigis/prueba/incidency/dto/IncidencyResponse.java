package com.sigis.prueba.incidency.dto;

import lombok.Data;

@Data
public class IncidencyResponse {
    private Long id;
    private String title;
    private String descripcion;
    private String fechaEmision;
    private String prioridad;
    private String estado;
    private String imagen;
    private String fechaAccept;

    private Long userId;
    private Long categoriaId;
    private Long ubicacionId;


}

