package com.sigis.prueba.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sigis.prueba.auth.model.RolModel;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Long> {

    Optional<RolModel> findByTipoRol(String tipoRol);
}
