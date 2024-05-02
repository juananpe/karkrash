package eus.ehu.karkrash.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Renting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Temporal(TemporalType.TIMESTAMP)
    private Date starting;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expectedReturning;
    private Double paid;
    private Double totalToPay;
    private Integer initialKm;
    private Integer finalKm;
    @Temporal(TemporalType.TIMESTAMP)
    private Date realReturningDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @ManyToOne
    @JoinColumn(name = "vehicle_license_plate")
    private Vehicle vehicle;

    public Renting(LocalDate now, LocalDate endDate, Vehicle v, double totalToPay, Client client) {
        this.starting = Date.from(now.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        this.expectedReturning = Date.from(endDate.atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        this.vehicle = v;
        this.totalToPay = totalToPay;
        this.client = client;
    }

    public Renting() {
    }

    // Getters and setters...


    public Long getCode() {
        return code;
    }

    public Date getStarting() {
        return starting;
    }

    public Date getExpectedReturning() {
        return expectedReturning;
    }

    public Double getPaid() {
        return paid;
    }

    public Double getTotalToPay() {
        return totalToPay;
    }

    public Integer getInitialKm() {
        return initialKm;
    }

    public Integer getFinalKm() {
        return finalKm;
    }

    public Date getRealReturningDate() {
        return realReturningDate;
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setCode(Long id) {
        this.code = id;
    }

    public void setStarting(Date starting) {
        this.starting = starting;
    }

    public void setExpectedReturning(Date expectedReturning) {
        this.expectedReturning = expectedReturning;
    }

    public void setPaid(Double paid) {
        this.paid = paid;
    }

    public void setTotalToPay(Double totalToPay) {
        this.totalToPay = totalToPay;
    }

    public void setInitialKm(Integer initialKm) {
        this.initialKm = initialKm;
    }

    public void setFinalKm(Integer finalKm) {
        this.finalKm = finalKm;
    }

    public void setRealReturningDate(Date realReturningDate) {
        this.realReturningDate = realReturningDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
