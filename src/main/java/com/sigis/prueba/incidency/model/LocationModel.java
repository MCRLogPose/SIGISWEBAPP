    package com.sigis.prueba.incidency.model;

    import jakarta.persistence.*;
    import lombok.Data;

    @Data
    @Entity
    @Table (name = "ubicacion")

    public class LocationModel {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)

        private long id;
        private String pabellon;
        private int piso;
        private String descripcion;
    }
