package com.sigis.prueba.incidency.repository;

import com.sigis.prueba.incidency.model.IncidencyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IncidencyRepository extends JpaRepository<IncidencyModel, Long> {
    //Optional<IncidencyModel> findByIncidency (String id);
    List<IncidencyModel> findByState(String estado);

}
