package com.sigis.prueba.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String username;
    private String rol;

    private List<String> modulos; // ✅ Añadido
}
