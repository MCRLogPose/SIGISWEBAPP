package com.sigis.prueba.incidency.service;

import com.sigis.prueba.incidency.dto.UbicacionRequest;
import com.sigis.prueba.incidency.model.LocationModel;
import com.sigis.prueba.incidency.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationModel createUbicacion(UbicacionRequest request){
        LocationModel location = new LocationModel();

        location.setDescripcion(request.getDescripcion());
        location.setPiso(Integer.parseInt(request.getPiso()));
        location.setPabellon(request.getPabellon());

        return locationRepository.save(location);
    }

    public List<LocationModel> getAll(){
        return locationRepository.findAll();
    }
}
