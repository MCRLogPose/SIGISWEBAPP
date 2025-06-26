package com.sigis.prueba.incidency.service;

import com.sigis.prueba.auth.repository.UserRepository;
import com.sigis.prueba.incidency.dto.IncidencyRequest;
import com.sigis.prueba.incidency.dto.IncidencyResponse;
import com.sigis.prueba.incidency.mapper.IncidencyMapper;
import com.sigis.prueba.incidency.model.IncidencyModel;
import com.sigis.prueba.incidency.repository.CategoryRepository;
import com.sigis.prueba.incidency.repository.IncidencyRepository;
import com.sigis.prueba.incidency.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import com.sigis.prueba.auth.model.UserModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private IncidencyMapper incidencyMapper;

    public IncidencyResponse createIncidency(IncidencyRequest dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con username: " + username));

        IncidencyModel incidency = new IncidencyModel();
        incidency.setTitle(dto.getTitle());
        incidency.setDescription(dto.getDescription());
        incidency.setDateEmision(dto.getDateEmision());
        incidency.setPriority(dto.getPriority());

        incidency.setDateAccept(dto.getDateAccept());
        incidency.setState(dto.getState());

        incidency.setUser(user);

        incidency.setCategory(categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + dto.getCategoryId())));
        incidency.setLocation(locationRepository.findById(dto.getLocationId())
                .orElseThrow(() -> new RuntimeException("Ubicacion no encontrada con id: " + dto.getLocationId())));
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            try {
                String uploadsDir = "uploads/";
                String originalFilename = dto.getImage().getOriginalFilename();
                Path uploadPath = Paths.get(uploadsDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(originalFilename);
                Files.copy(dto.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                incidency.setImage(originalFilename);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar la imagen en el servidor", e);
            }
        }

        return incidencyMapper.toResponse(incidencyRepository.save(incidency));
    }

    public List<IncidencyResponse> getAll() {
        return incidencyRepository.findAll().stream()
                .map(incidencyMapper::toResponse)
                .toList();
    }

    public IncidencyResponse verificarYCompletarIncidencia(Long incidenciaId) {
        IncidencyModel incidencia = incidencyRepository.findById(incidenciaId)
                .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + incidenciaId));

        incidencia.setState("completado");
        incidencyRepository.save(incidencia);

        return incidencyMapper.toResponse(incidencia);
    }

    public List<IncidencyResponse> getIncidenciasCulminadas() {
        return incidencyRepository.findByState("culminada")
                .stream()
                .map(incidencyMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<IncidencyResponse> getIncidenciasCompletadas() {
        return incidencyRepository.findByState("completado")
                .stream()
                .map(incidencyMapper::toResponse)
                .collect(Collectors.toList());
    }


}
