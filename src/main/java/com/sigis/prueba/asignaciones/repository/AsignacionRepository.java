package com.sigis.prueba.asignaciones.repository;

import com.sigis.prueba.asignaciones.model.AsignacionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignacionRepository extends JpaRepository <AsignacionModel, Long> {
}
