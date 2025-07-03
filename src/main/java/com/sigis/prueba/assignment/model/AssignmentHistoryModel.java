package com.sigis.prueba.assignment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sigis.prueba.auth.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "assignment_history")
public class AssignmentHistoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String response;
    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    @JsonManagedReference
    private AssignmentModel assignment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private UserModel user;
}
