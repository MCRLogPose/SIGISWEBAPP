package com.sigis.prueba.auth.repository;

import com.sigis.prueba.auth.model.ModuloModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuloRepository extends JpaRepository<ModuloModel, Long> {
}
