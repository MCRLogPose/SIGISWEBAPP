package com.sigis.prueba.assignment.model;

import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.incidency.model.IncidencyModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "assignments")
public class AssignmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String response;
    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAssignment = new Date();

    @ManyToOne
    @JoinColumn(name = "incidencia_id", referencedColumnName = "id")
    private IncidencyModel incidencyModel;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;
}
