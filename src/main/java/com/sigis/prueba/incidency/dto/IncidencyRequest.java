package com.sigis.prueba.incidency.dto;

import lombok.Data;

@Data
public class IncidencyRequest {
    private String title;
    private String descripcion;
    private String fechaEmision;
    private String prioridad;
    private String imagen;
    private String fechaAccept;
    private String estado;

    private Long categoriaId;
    private Long ubicacionId;
}
