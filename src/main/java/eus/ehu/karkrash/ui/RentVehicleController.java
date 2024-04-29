package eus.ehu.karkrash.ui;

import eus.ehu.karkrash.businessLogic.BlFacade;
import eus.ehu.karkrash.model.VehicleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RentVehicleController {

    private BlFacade bl;

    private ObservableList<VehicleModel> vehicles = FXCollections.observableArrayList();

    @FXML
    private TableView<VehicleModel> vehiclesTableView;

    @FXML
    private TableColumn<VehicleModel, String> brandColumn;

    @FXML
    private TableColumn<VehicleModel, String> modelColumn;

    @FXML
    private TableColumn<VehicleModel, String> plateColumn;

    @FXML
    private TableColumn<VehicleModel, Number> priceColumn;

    @FXML
    private ComboBox<?> brandComboBox;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<?> modelComboBox;

    @FXML
    private ComboBox<?> officeComboBox;

    @FXML
    private Button payButton;

    @FXML
    private Label paymentInfoLabel;

    @FXML
    void initialize() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Sample data - replace this with data retrieved from your DataAccess class
        vehicles.add(new VehicleModel("1234ABC", "Audi", "A4", 50.0));
        vehicles.add(new VehicleModel("5678DEF", "Renault", "Clio", 30.0));

        // Set the data into the TableView
        vehiclesTableView.setItems(vehicles);

    }

    public void setBl(BlFacade bl) {
        this.bl = bl;
    }
}
