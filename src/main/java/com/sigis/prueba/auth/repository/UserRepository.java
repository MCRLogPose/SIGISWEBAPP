package com.sigis.prueba.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sigis.prueba.auth.model.UserModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByCorreo(String correo);
    boolean existsByUsername(String username);
    boolean existsByCorreo(String correo);
}
