package com.sigis.prueba.incidency.repository;

import com.sigis.prueba.incidency.model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationModel, Long> {

    //Optional<LocationModel> findByUbicacionId(Long id);
}
