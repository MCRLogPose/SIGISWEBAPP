package com.sigis.prueba.incidency.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class IncidencyRequest {
    private String title;
    private String descripcion;
    private String fechaEmision;
    private String prioridad;
    private MultipartFile imagen;
    private String fechaAccept;
    private String estado;

    private Long categoriaId;
    private Long ubicacionId;
}
