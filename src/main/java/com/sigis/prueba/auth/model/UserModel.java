package com.sigis.prueba.auth.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String estado;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    @JsonManagedReference
    private RolModel rol;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private TwoFAModel user2FA;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private CredentialsModel userCredentials;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TokenModel> tokens;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private OperarioDetails operarioDetails;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();
}
