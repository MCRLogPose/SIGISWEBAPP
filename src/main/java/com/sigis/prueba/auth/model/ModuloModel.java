package com.sigis.prueba.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "modulos")
@Data
public class ModuloModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;  // ej. "Crear Incidencia", "Aceptar Incidencia", "Asignar Incidencia"

    private String descripcion;

    @ManyToMany(mappedBy = "modulos")
    @JsonIgnore  // Para evitar ciclos si es que se llegara a serializar
    private List<RolModel> roles;

}
