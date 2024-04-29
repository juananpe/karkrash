package eus.ehu.karkrash.businessLogic;

import eus.ehu.karkrash.configuration.Config;
import eus.ehu.karkrash.dataAccess.DataAccess;
import eus.ehu.karkrash.domain.Office;

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
}
