package com.sigis.prueba.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="user_2fa")
public class TwoFAModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @OneToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    @JsonBackReference
    private UserModel user;

    private String secretKey;
    private Boolean enabled = false;
    private LocalDateTime createdAt;

}
