package eus.ehu.karkrash.businessLogic;

import eus.ehu.karkrash.configuration.Config;
import eus.ehu.karkrash.dataAccess.DataAccess;
import eus.ehu.karkrash.domain.Client;
import eus.ehu.karkrash.domain.Office;
import eus.ehu.karkrash.model.VehicleModel;

import java.time.LocalDate;
import java.util.List;


/**
 * Implements the business logic as a web service.
 */
public class BlFacadeImplementation implements BlFacade {

    private DataAccess dbManager;
    private Config config = Config.getInstance();
    private Client currentUser;


    private static BlFacadeImplementation bl = new BlFacadeImplementation();

    public static BlFacadeImplementation getInstance() {
        return bl;
    }

    private BlFacadeImplementation() {
        System.out.println("Creating BlFacadeImplementation instance");
        boolean initialize = config.getDataBaseOpenMode().equals("initialize");
        dbManager = new DataAccess();
        if (initialize)
            dbManager.initializeDB();
        // dbManager.close();

        currentUser = dbManager.getClient("12345678A");
    }


    @Override
    public List<Office> getOffices() {
        return dbManager.getOffices();
    }

    @Override
    public List<String> getModels() {
        return dbManager.getModels();
    }

    @Override
    public List<String> getBrands() {
        return dbManager.getBrands();
    }

    @Override
    public List<VehicleModel> getAvailableVehicles(String brand, String model, Office office, LocalDate endDate) {
        return dbManager.getAvailableVehicles(brand, model, office, endDate);
    }

    @Override
    public void rentVehicle(VehicleModel vehicle, Long days, Double pricePerDay) {
        dbManager.rentVehicle(vehicle, days, pricePerDay, currentUser);
    }
}
