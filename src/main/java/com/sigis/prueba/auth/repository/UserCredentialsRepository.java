package com.sigis.prueba.auth.repository;

import com.sigis.prueba.auth.model.CredentialsModel;
import com.sigis.prueba.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<CredentialsModel, Long> {
    CredentialsModel findByUser(UserModel user);
}
