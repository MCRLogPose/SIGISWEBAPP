package com.sigis.prueba.assignment.repository;

import com.sigis.prueba.assignment.model.AssignmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository <AssignmentModel, Long> {
    List<AssignmentModel> findByIncidencyModelId(Long incidencyId);

}
