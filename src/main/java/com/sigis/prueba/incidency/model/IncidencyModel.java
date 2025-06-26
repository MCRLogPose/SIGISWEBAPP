package com.sigis.prueba.incidency.model;

import com.sigis.prueba.auth.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table (name = "incidents")

public class IncidencyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String title;
    private String description;
    private String dateEmision;
    private String priority;
    private String image;
    private String dateAccept;
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private CategoryModel category;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationModel location;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

}
