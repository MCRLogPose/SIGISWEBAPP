package com.sigis.prueba.assignment.repository;

import com.sigis.prueba.assignment.model.AssignmentHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssignmentHistoryRepository extends JpaRepository<AssignmentHistoryModel, Long> {
    List<AssignmentHistoryModel> findByAssignmentId(Long assignmentId);
}