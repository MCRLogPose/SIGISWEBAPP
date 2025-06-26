package com.sigis.prueba.assignment.service;

import com.sigis.prueba.assignment.dto.AssignmentRequest;
import com.sigis.prueba.assignment.dto.AssignmentResponse;
import com.sigis.prueba.assignment.mapper.AsignacionMapper;
import com.sigis.prueba.assignment.model.AssignmentModel;
import com.sigis.prueba.assignment.repository.AssignmentRepository;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.auth.repository.UserRepository;
import com.sigis.prueba.incidency.repository.IncidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncidencyRepository incidencyRepository;

    @Autowired
    private AssignmentRepository asignacionRepository;

    @Autowired
    private AsignacionMapper asignacionMapper;

    public AssignmentResponse createAsignacion(AssignmentRequest dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));

        var incidencia = incidencyRepository.findById(dto.getIncidencyId())
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + dto.getIncidencyId()));
        incidencia.setState("asignado");
        incidencyRepository.save(incidencia);

        AssignmentModel asignacion = new AssignmentModel();
        asignacion.setUser(user);
        asignacion.setResponse(dto.getResponse());
        asignacion.setState("asignado");
        asignacion.setIncidencyModel(incidencia);

        return asignacionMapper.toResponse(asignacionRepository.save(asignacion));
    }

    public List<AssignmentResponse> getAllAsignaciones() {
        return asignacionRepository.findAll().stream()
                .map(asignacionMapper::toResponse)
                .collect(Collectors.toList());
    }



}
