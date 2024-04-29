package eus.ehu.karkrash.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for auto-increment
    private Long id;  // Use Long for the ID


    private String brand;
    private String model;

    @OneToMany(mappedBy = "vehicleType")
    private List<Vehicle> vehicles;

    public VehicleType() {
    }

    public Long getCode() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
