package com.sigis.prueba.assignment.service;

import com.sigis.prueba.assignment.dto.*;
import com.sigis.prueba.assignment.mapper.AsignacionMapper;
import com.sigis.prueba.assignment.mapper.AssignmentHistoryMapper;
import com.sigis.prueba.assignment.model.AssignmentModel;
import com.sigis.prueba.assignment.repository.AssignmentRepository;
import com.sigis.prueba.auth.dto.UserResponse;
import com.sigis.prueba.auth.mapper.UserMapper;
import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.auth.repository.UserRepository;
import com.sigis.prueba.incidency.mapper.IncidencyMapper;
import com.sigis.prueba.incidency.repository.IncidencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IncidencyMapper incidencyMapper;

    @Autowired
    private AssignmentHistoryMapper assignmentHistoryMapper;

    @Autowired
    private AssignmentRepository assignmentRepository;


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
    public AssignmentGroupResponse getAsignacionesByIncidencia(Long incidencyId) {
        List<AssignmentModel> asignaciones = asignacionRepository.findByIncidencyModelId(incidencyId);

        if (asignaciones.isEmpty()) {
            throw new RuntimeException("No hay asignaciones para esta incidencia");
        }
        AssignmentModel primera = asignaciones.get(0);
        AssignmentGroupResponse response = new AssignmentGroupResponse();

        response.setId(primera.getId());
        response.setIncidency(incidencyMapper.toResponse(primera.getIncidencyModel()));
        response.setResponse(primera.getResponse());
        response.setDateAssignment(primera.getDateAssignment());
        response.setState(primera.getState());

        List<UserResponse> usuarios = asignaciones.stream()
                .map(a -> userMapper.toResponse(a.getUser()))
                .distinct() // evita duplicados
                .collect(Collectors.toList());
        response.setUsers(usuarios);

        List<AssignmentHistoryResponse> historyList = asignaciones.stream()
                .flatMap(a -> a.getHistory() != null ? a.getHistory().stream() : Stream.empty())
                .map(assignmentHistoryMapper::toResponse)
                .collect(Collectors.toList());
        response.setHistory(historyList);

        return response;
    }


    public List<AssignmentResponse> asignAssigment(AssignmentGroupRequest dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String assigningUsername = authentication.getName();

        var incidencia = incidencyRepository.findById(dto.getIncidencyId())
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con id: " + dto.getIncidencyId()));

        incidencia.setState("asignado");
        incidencyRepository.save(incidencia);

        List<AssignmentResponse> responses = new ArrayList<>();

        for (Long userId : dto.getUserIds()) {
            UserModel assignedUser = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

            AssignmentModel asignacion = new AssignmentModel();
            asignacion.setUser(assignedUser);
            asignacion.setResponse(dto.getResponse());
            asignacion.setState("asignado");
            asignacion.setIncidencyModel(incidencia);

            responses.add(asignacionMapper.toResponse(asignacionRepository.save(asignacion)));
        }

        return responses;
    }

    public List<AssignmentResponse> getAllAsignaciones() {
        return asignacionRepository.findAll().stream()
                .map(asignacionMapper::toResponse)
                .collect(Collectors.toList());
    }



}
