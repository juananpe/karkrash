package eus.ehu.karkrash.businessLogic;

// import eus.ehu.template.domain.Hello;

import eus.ehu.karkrash.domain.Office;

import java.util.List;

/**
 * Interface that specifies the business logic.
 */
public interface BlFacade  {
    List<Office> getOffices();

    List<String> getModels();

    List<String> getBrands();

    // ===== Define the public interface of the BL

}
