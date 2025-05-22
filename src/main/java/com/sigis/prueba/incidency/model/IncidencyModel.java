package com.sigis.prueba.incidency.model;

import com.sigis.prueba.auth.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table (name = "incidencias")

public class IncidencyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String title;
    private String descripcion;
    private String fechaEmision;
    private String prioridad;
    private String imagen;
    private String fechaAccept;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private CategoryModel categoria;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    private LocationModel ubicacion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

}
