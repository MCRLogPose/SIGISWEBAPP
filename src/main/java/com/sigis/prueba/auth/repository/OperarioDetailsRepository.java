package com.sigis.prueba.auth.repository;

import com.sigis.prueba.auth.model.OperarioDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperarioDetailsRepository extends JpaRepository<OperarioDetails, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
