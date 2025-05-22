package com.sigis.prueba.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class OperarioDetails {
    @Id
    private Long id; // mismo id que UserModel

    @OneToOne
    @MapsId
    private UserModel user;

    private String especialidad;

}
