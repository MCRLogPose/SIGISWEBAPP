package com.sigis.prueba.incidency.dto;

import com.sigis.prueba.auth.dto.UserResponse;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class IncidencyResponse {
    private Long id;
    private String title;
    private String description;
    private String dateEmision;
    private String priority;
    private String image;
    private String dateAccept;
    private String state;

    private UserResponse user;
    private CategoryResponse category;
    private LocationResponse location;






}

