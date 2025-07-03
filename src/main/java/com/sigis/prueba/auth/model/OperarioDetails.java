package com.sigis.prueba.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class OperarioDetails {
    @Id
    private Long id; // mismo id que UserModel

    @OneToOne
    @MapsId
    @JsonBackReference
    private UserModel user;

    private String especialidad;

}
