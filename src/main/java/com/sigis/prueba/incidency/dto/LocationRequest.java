package com.sigis.prueba.incidency.dto;

import lombok.Data;

@Data
public class LocationRequest {
    private String pavilion;
    private int floor;
    private String reference;
}
