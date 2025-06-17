package com.sigis.prueba.asignaciones.repository;

import com.sigis.prueba.asignaciones.model.AsignacionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignacionRepository extends JpaRepository <AsignacionModel, Long> {
    List<AsignacionModel> findByIncidencyModelId(Long incidencyId);

}
