package com.sigis.prueba.auth.repository;

import com.sigis.prueba.auth.model.TwoFAModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User2FARepository extends JpaRepository<TwoFAModel, Long> {
    TwoFAModel findByUser_Id(Long userId);
}
