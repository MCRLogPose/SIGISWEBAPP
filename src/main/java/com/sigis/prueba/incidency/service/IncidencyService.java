package com.sigis.prueba.incidency.service;

import com.sigis.prueba.auth.repository.UserRepository;
import com.sigis.prueba.incidency.dto.IncidencyRequest;
import com.sigis.prueba.incidency.dto.IncidencyResponse;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.incidency.repository.CategoryRepository;
import com.sigis.prueba.incidency.repository.IncidencyRepository;
import com.sigis.prueba.incidency.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidencyService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private IncidencyRepository incidencyRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private UserRepository userRepository;

    private IncidencyResponse toResponse(IncidencyModel model) {
        IncidencyResponse response = new IncidencyResponse();
        response.setId(model.getId());
        response.setTitle(model.getTitle());
        response.setDescripcion(model.getDescripcion());
        response.setFechaEmision(model.getFechaEmision());
        response.setPrioridad(model.getPrioridad());
        response.setImagen(model.getImagen());
        response.setFechaAccept(model.getFechaAccept());
        response.setEstado(model.getEstado());
        response.setUserId(model.getUser().getId());
        response.setCategoriaId(model.getCategoria().getId());
        response.setUbicacionId(model.getUbicacion().getId());
        return response;
    }

    public IncidencyResponse createIncidency(IncidencyRequest dto) {
        System.out.println("DTO recibido:");
        System.out.println("UserId = " + dto.getUserId());
        System.out.println("CategoriaId = " + dto.getCategoriaId());
        System.out.println("UbicacionId = " + dto.getUbicacionId());

        if (dto.getUserId() == null) {
            throw new IllegalArgumentException("UserId no puede ser null");
        }
        if (dto.getCategoriaId() == null) {
            throw new IllegalArgumentException("CategoriaId no puede ser null");
        }
        if (dto.getUbicacionId() == null) {
            throw new IllegalArgumentException("UbicacionId no puede ser null");
        }

        IncidencyModel incidency = new IncidencyModel();
        incidency.setTitle(dto.getTitle());
        incidency.setDescripcion(dto.getDescripcion());
        incidency.setFechaEmision(dto.getFechaEmision());
        incidency.setPrioridad(dto.getPrioridad());
        incidency.setImagen(dto.getImagen());
        incidency.setFechaAccept(dto.getFechaAccept());
        incidency.setEstado(dto.getEstado());

        incidency.setUser(userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + dto.getUserId())));
        incidency.setCategoria(categoryRepository.findById(dto.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + dto.getCategoriaId())));
        incidency.setUbicacion(locationRepository.findById(dto.getUbicacionId()).orElseThrow(() -> new RuntimeException("Ubicacion no encontrada con id: " + dto.getUbicacionId())));

        IncidencyModel saved = incidencyRepository.save(incidency);
        return toResponse(saved);
    }



    public List<IncidencyResponse> getAll(){
        return incidencyRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

}
