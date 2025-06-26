package com.sigis.prueba.incidency.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class IncidencyRequest {
    private String title;
    private String description;
    private String dateEmision;
    private String priority;
    private MultipartFile image;
    private String dateAccept;
    private String state;

    private Long categoryId;
    private Long locationId;
}
