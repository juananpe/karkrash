package eus.ehu.karkrash.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class VehicleModel {
    private final SimpleStringProperty plate;
    private final SimpleStringProperty brand;
    private final SimpleStringProperty model;
    private final SimpleDoubleProperty price;

    public VehicleModel(String plate, String brand, String model, double price) {
        this.plate = new SimpleStringProperty(plate);
        this.brand = new SimpleStringProperty(brand);
        this.model = new SimpleStringProperty(model);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getPlate() {
        return plate.get();
    }

    public SimpleStringProperty plateProperty() {
        return plate;
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }


}
