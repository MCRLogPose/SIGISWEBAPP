package com.sigis.prueba.incidency.service;

import com.sigis.prueba.incidency.dto.LocationRequest;
import com.sigis.prueba.incidency.model.LocationModel;
import com.sigis.prueba.incidency.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationModel createUbicacion(LocationRequest request){
        LocationModel location = new LocationModel();

        location.setReference(request.getReference());
        location.setFloor(request.getFloor());
        location.setPavilion(request.getPavilion());

        return locationRepository.save(location);
    }

    public List<LocationModel> getAll(){
        return locationRepository.findAll();
    }
}
