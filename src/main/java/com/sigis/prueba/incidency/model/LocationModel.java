    package com.sigis.prueba.incidency.model;

    import jakarta.persistence.*;
    import lombok.Data;

    @Data
    @Entity
    @Table (name = "location")

    public class LocationModel {

        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)

        private long id;
        private String pavilion;
        private int floor;
        private String reference;
    }
