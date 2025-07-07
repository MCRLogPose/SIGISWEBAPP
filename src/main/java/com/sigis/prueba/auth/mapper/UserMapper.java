package com.sigis.prueba.auth.mapper;

import com.sigis.prueba.auth.dto.UserResponse;
import com.sigis.prueba.auth.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(UserModel user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setNombre(user.getNombre());
        dto.setApellidos(user.getApellidos());
        dto.setCorreo(user.getCorreo());
        dto.setTelefono(user.getTelefono());
        dto.setDni(user.getDni());
        // Setear rol
        if (user.getRol() != null) {
            dto.setRol(user.getRol().getTipoRol());
        }

        // Setear especialidad desde OperarioDetails
        if (user.getOperarioDetails() != null) {
            dto.setEspecialidad(user.getOperarioDetails().getEspecialidad());
        }

        dto.setTipoRol(user.getRol() != null ? user.getRol().getTipoRol() : null);

        // Puedes cargar módulos si quieres también
        // response.setModulos(...);

        return dto;
    }
}
