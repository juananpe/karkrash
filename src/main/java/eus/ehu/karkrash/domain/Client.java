package eus.ehu.karkrash.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Client {
    @Id
    private String dni;

    private String username;
    private String password;
    private String creditCard;
    private String email;
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Renting> rentings;

    // Getters and setters...

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Renting> getRentings() {
        return rentings;
    }

    public void setRentings(List<Renting> rentings) {
        this.rentings = rentings;
    }
}
