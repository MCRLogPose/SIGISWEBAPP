package com.sigis.prueba.incidency.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name = "categorias")

public class CategoryModel {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private long id;
    private String tipoCategoria;
}
