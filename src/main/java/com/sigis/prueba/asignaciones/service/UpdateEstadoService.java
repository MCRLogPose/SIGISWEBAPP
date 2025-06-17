package com.sigis.prueba.asignaciones.service;

import com.sigis.prueba.asignaciones.dto.AsignacionResponse;
import com.sigis.prueba.asignaciones.mapper.AsignacionMapper;
import com.sigis.prueba.asignaciones.model.AsignacionModel;
import com.sigis.prueba.asignaciones.repository.AsignacionRepository;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.auth.repository.UserRepository;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.incidency.repository.IncidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UpdateEstadoService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Autowired
    private IncidencyRepository incidencyRepository;

    @Autowired
    private AsignacionMapper asignacionMapper;


    public AsignacionResponse updateResponse(Long asignacionId, String respuesta) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));

        AsignacionModel asignacion = asignacionRepository.findById(asignacionId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con id: " + asignacionId));

        asignacion.setUser(user);
        asignacion.setResponse(respuesta);
        asignacion.setEstado("en proceso");
        asignacionRepository.save(asignacion);

        IncidencyModel incidencia = asignacion.getIncidencyModel();
        if ("asignado".equalsIgnoreCase(incidencia.getEstado())) {
            incidencia.setEstado("en proceso");
            incidencyRepository.save(incidencia);
        }
        return asignacionMapper.toResponse(asignacion);
    }
    public AsignacionResponse culminarAsignacion(Long asignacionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));

        AsignacionModel asignacion = asignacionRepository.findById(asignacionId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con ID: " + asignacionId));

        asignacion.setUser(user);
        asignacion.setEstado("culminado");
        asignacionRepository.save(asignacion);

        return asignacionMapper.toResponse(asignacion);
    }
}
