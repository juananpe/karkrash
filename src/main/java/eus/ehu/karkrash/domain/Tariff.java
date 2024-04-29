package eus.ehu.karkrash.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private Double pricePerDay;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private Double priceExtraKm;
    private Integer maxKmPerDay;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPriceExtraKm() {
        return priceExtraKm;
    }

    public void setPriceExtraKm(Double priceExtraKm) {
        this.priceExtraKm = priceExtraKm;
    }

    public Integer getMaxKmPerDay() {
        return maxKmPerDay;
    }

    public void setMaxKmPerDay(Integer maxKmPerDay) {
        this.maxKmPerDay = maxKmPerDay;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

}
