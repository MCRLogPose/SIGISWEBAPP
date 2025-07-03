package com.sigis.prueba.assignment.controller;

import com.sigis.prueba.assignment.dto.AssignmentHistoryRequest;
import com.sigis.prueba.assignment.dto.AssignmentHistoryResponse;
import com.sigis.prueba.assignment.model.AssignmentHistoryModel;
import com.sigis.prueba.assignment.service.AssignmentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class AssignmentHistoryController {

    @Autowired
    private AssignmentHistoryService service;

    @PostMapping
    public AssignmentHistoryResponse createHistory(@RequestBody AssignmentHistoryRequest request) {
        return service.saveHistory(request);
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<AssignmentHistoryResponse> getByAssignment(@PathVariable Long assignmentId) {
        return service.getByAssignmentId(assignmentId);
    }

}
