package com.sigis.prueba.asignaciones.service;

import com.sigis.prueba.asignaciones.dto.AsignacionRequest;
import com.sigis.prueba.asignaciones.dto.AsignacionResponse;
import com.sigis.prueba.asignaciones.model.AsignacionModel;
import com.sigis.prueba.asignaciones.repository.AsignacionRepository;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.auth.repository.UserRepository;
import com.sigis.prueba.incidency.repository.IncidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AsignacionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncidencyRepository incidencyRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    public AsignacionResponse createAsignacion(AsignacionRequest dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));

        AsignacionModel asignacion = new AsignacionModel();
        asignacion.setUser(user);
        asignacion.setResponse(dto.getResponse());

        asignacion.setIncidencyModel(incidencyRepository.findById(dto.getIncidencyId())
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + dto.getIncidencyId())));

        AsignacionModel saved = asignacionRepository.save(asignacion);

        AsignacionResponse response = new AsignacionResponse();
        response.setId(saved.getId());
        response.setUserId(saved.getUser().getId());
        response.setIncidencyId(saved.getIncidencyModel().getId());
        response.setResponse(saved.getResponse());
        response.setFechaAsignacion(saved.getFechaAsignacion());

        return response;
    }
    public List<AsignacionResponse> getAllAsignaciones() {
        return asignacionRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private AsignacionResponse toResponse(AsignacionModel model) {
        AsignacionResponse response = new AsignacionResponse();
        response.setId(model.getId());
        response.setUserId(model.getUser().getId());
        response.setIncidencyId(model.getIncidencyModel().getId());
        response.setResponse(model.getResponse());
        response.setFechaAsignacion(model.getFechaAsignacion());
        return response;
    }
}
