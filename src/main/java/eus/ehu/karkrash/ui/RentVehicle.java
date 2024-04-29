package eus.ehu.karkrash.ui;

import eus.ehu.karkrash.businessLogic.BlFacade;
import eus.ehu.karkrash.businessLogic.BlFacadeImplementation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Constructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RentVehicle extends Application {

    private static final Logger logger = LogManager.getLogger("HelloApplication");
    private BlFacade businessLogic = BlFacadeImplementation.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Starting HelloApplication");
        FXMLLoader fxmlLoader = new FXMLLoader(RentVehicle.class.getResource("rent-vehicle.fxml"));
        fxmlLoader.setControllerFactory(controllerClass -> {

            try {
                Constructor<?> cons = controllerClass.getConstructor(BlFacade.class);
                // Return a new instance of the controller
                return cons.newInstance(businessLogic);

            } catch (Exception exc) {
                exc.printStackTrace();
                throw new RuntimeException(exc); // fatal, just bail...
            }

        });

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Kar Krash / Rent Vehicle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
