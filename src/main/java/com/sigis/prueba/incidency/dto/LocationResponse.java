package com.sigis.prueba.incidency.dto;

import lombok.Data;

@Data
public class LocationResponse {
    private Long id;
    private String pavilion;
    private int floor;
    private String reference;
}
