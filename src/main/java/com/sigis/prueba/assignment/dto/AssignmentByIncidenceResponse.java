package com.sigis.prueba.assignment.dto;

import lombok.Data;
import java.util.List;

@Data
public class AssignmentByIncidenceResponse {
    private Long incidencyId;
    private String incidencyTitle;
    private List<AssignmentDTO> assigned;
}
