package eus.ehu.karkrash.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vehicle {
    @Id
    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleState state;

    @ManyToOne
    @JoinColumn(name = "vehicleType_code")
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle")
    private List<Renting> rentings;

    // Getters and setters...

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleState getState() {
        return state;
    }

    public void setState(VehicleState state) {
        this.state = state;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<Renting> getRentings() {
        return rentings;
    }

    public void setRentings(List<Renting> rentings) {
        this.rentings = rentings;
    }

    public void addRenting(Renting renting) {
        this.rentings.add(renting);
    }
}
