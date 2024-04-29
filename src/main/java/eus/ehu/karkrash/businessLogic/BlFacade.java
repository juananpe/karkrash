package eus.ehu.karkrash.businessLogic;

// import eus.ehu.template.domain.Hello;

import eus.ehu.karkrash.domain.Office;
import eus.ehu.karkrash.model.VehicleModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface that specifies the business logic.
 */
public interface BlFacade  {
    List<Office> getOffices();

    List<String> getModels();

    List<String> getBrands();

    List<VehicleModel> getAvailableVehicles(String brand, String model, Office office, LocalDate endDate);

    void rentVehicle(VehicleModel vehicle, Long days, Double pricePerDay);

    // ===== Define the public interface of the BL

}
