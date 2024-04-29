package eus.ehu.karkrash.ui;

import eus.ehu.karkrash.businessLogic.BlFacade;
import eus.ehu.karkrash.businessLogic.BlFacadeImplementation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RentVehicle extends Application {

    private static final Logger logger = LogManager.getLogger("HelloApplication");
    private BlFacade bl = BlFacadeImplementation.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        logger.info("Starting HelloApplication");
        FXMLLoader fxmlLoader = new FXMLLoader(RentVehicle.class.getResource("rent-vehicle.fxml"));
        Parent root = fxmlLoader.load();
        // get controller
        RentVehicleController controller = fxmlLoader.getController();
        controller.setBl(bl);
        Scene scene = new Scene(root);
        stage.setTitle("Kar Krash / Rent Vehicle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
