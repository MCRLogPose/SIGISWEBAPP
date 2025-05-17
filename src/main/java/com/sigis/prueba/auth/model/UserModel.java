package com.sigis.prueba.auth.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String apellidos;
    private String telefono;
    private String dni;
    private String username;
    private String password;
    private String correo;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RolModel rol;

    @OneToOne(mappedBy = "user")
    private TwoFAModel user2FA;

    @OneToOne(mappedBy = "user")
    private CredentialsModel userCredentials;

    @OneToOne(mappedBy = "user")
    private TokenModel tokenModel;

    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
