package com.sigis.prueba.asignaciones.dto;

import lombok.Data;
import java.util.List;

@Data
public class AsignacionesPorIncidenciaResponse {
    private Long incidencyId;
    private String incidencyTitle;
    private List<AsignadoDTO> asignados;
}
