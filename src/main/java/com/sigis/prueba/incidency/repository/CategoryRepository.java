package com.sigis.prueba.incidency.repository;

import com.sigis.prueba.incidency.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
    //Optional<CategoryModel> findByCategoriaId(Long id);

}
