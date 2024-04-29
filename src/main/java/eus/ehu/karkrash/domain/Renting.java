package eus.ehu.karkrash.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Renting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date starting;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expectedReturning;
    private Boolean paid;
    private Double totalToPay;
    private String code;
    private Integer initialKm;
    private Integer finalKm;
    @Temporal(TemporalType.TIMESTAMP)
    private Date realReturningDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @ManyToOne
    @JoinColumn(name = "vehicle_license_plate")
    private Vehicle vehicle;

    // Getters and setters...
}
