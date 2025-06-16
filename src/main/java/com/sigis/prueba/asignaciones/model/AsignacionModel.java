package com.sigis.prueba.asignaciones.model;

import com.sigis.prueba.auth.model.UserModel;
import com.sigis.prueba.incidency.model.IncidencyModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "asignaciones")
public class AsignacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String response;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion = new Date();

    @ManyToOne
    @JoinColumn(name = "incidencia_id", referencedColumnName = "id")
    private IncidencyModel incidencyModel;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;
}
