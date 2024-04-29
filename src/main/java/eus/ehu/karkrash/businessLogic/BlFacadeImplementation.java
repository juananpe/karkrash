package eus.ehu.karkrash.businessLogic;

import eus.ehu.karkrash.configuration.Config;
import eus.ehu.karkrash.dataAccess.DataAccess;
import eus.ehu.karkrash.domain.Office;
import eus.ehu.karkrash.model.VehicleModel;

import java.time.LocalDate;
import java.util.List;


/**
 * Implements the business logic as a web service.
 */
public class BlFacadeImplementation implements BlFacade {

    DataAccess dbManager;
    Config config = Config.getInstance();

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
}
