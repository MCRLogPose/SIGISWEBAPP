package com.sigis.prueba.assignment.service;

import com.sigis.prueba.assignment.dto.AssignmentResponse;
import com.sigis.prueba.assignment.mapper.AsignacionMapper;
import com.sigis.prueba.assignment.model.AssignmentModel;
import com.sigis.prueba.assignment.repository.AssignmentRepository;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.auth.repository.UserRepository;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.incidency.repository.IncidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateEstadoService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository asignacionRepository;

    @Autowired
    private IncidencyRepository incidencyRepository;

    @Autowired
    private AsignacionMapper asignacionMapper;


    public AssignmentResponse updateResponse(Long asignacionId, String respuesta) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));

        AssignmentModel asignacion = asignacionRepository.findById(asignacionId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con id: " + asignacionId));

        asignacion.setUser(user);
        asignacion.setResponse(respuesta);
        IncidencyModel incidencia = asignacion.getIncidencyModel();

        if(incidencia.getState().equalsIgnoreCase("asignado")){//puede hacerse con incidencia o asignacio pues el state es el mismo
            asignacion.setState("en proceso");
            incidencia.setState("en proceso");
        }else{
            asignacion.setState("asignado");
            incidencia.setState("asignado");
        }
        asignacionRepository.save(asignacion);
        incidencyRepository.save(incidencia);

        return asignacionMapper.toResponse(asignacion);
    }
    public AssignmentResponse culminarAsignacion(Long asignacionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));

        AssignmentModel asignacion = asignacionRepository.findById(asignacionId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada con ID: " + asignacionId));

        asignacion.setUser(user);
        asignacion.setState("culminado");
        asignacionRepository.save(asignacion);

        IncidencyModel incidencia = asignacion.getIncidencyModel();
        if ("en proceso".equalsIgnoreCase(incidencia.getState())) {
            incidencia.setState("culminado");
            incidencyRepository.save(incidencia);
        }
        return asignacionMapper.toResponse(asignacion);
    }
}
