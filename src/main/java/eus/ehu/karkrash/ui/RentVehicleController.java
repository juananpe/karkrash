package eus.ehu.karkrash.ui;

import eus.ehu.karkrash.businessLogic.BlFacade;
import eus.ehu.karkrash.domain.Office;
import eus.ehu.karkrash.model.VehicleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private ComboBox<String> brandComboBox;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> modelComboBox;

    @FXML
    private ComboBox<Office> officeComboBox;

    @FXML
    private Button payButton;

    @FXML
    private Label paymentInfoLabel;

    @FXML
    void initialize() {
        setColumns();
        setOfficeCombobox();

        bl.getModels().forEach(modelComboBox.getItems()::add);
        bl.getBrands().forEach(brandComboBox.getItems()::add);

        // Sample data - replace this with data retrieved from your DataAccess class
        // vehicles.add(new VehicleModel("1234ABC", "Audi", "A4", 50.0));
        // vehicles.add(new VehicleModel("5678DEF", "Renault", "Clio", 30.0));


        // get selected row data
        payButton.setOnAction(event -> {
            // get the selected row
            VehicleModel vehicle = vehiclesTableView.getSelectionModel().getSelectedItem();
            Long days = getDays();
            if (vehicle != null && days != null) {
                // Call the BL to rent the vehicle
                bl.rentVehicle(vehicle, days, vehicle.getPrice());
                paymentInfoLabel.setText("Vehicle rented successfully");

                // remove the rented vehicle from the list
                vehicles.remove(vehicle);
            }


        });

        // get row data
        vehiclesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, vehicle) -> {
            if (vehicle == null) {
                return;
            }

            Long days = getDays();
            System.out.println("Days: " + days);

            paymentInfoLabel.setText("You'll have to pay 20% of " + vehicle.getPrice() * days + "€ = " +  vehicle.getPrice() * days * 0.20 + "€ now");
        });

        // Set the data into the TableView
        vehiclesTableView.setItems(vehicles);

        // select first office
        officeComboBox.getSelectionModel().selectFirst();
        // select first brand and model
        brandComboBox.getSelectionModel().selectFirst();
        modelComboBox.getSelectionModel().selectFirst();
        // select today's date + 3 days
        endDatePicker.setValue(LocalDate.now().plusDays(3));


    }

    private Long getDays() {
        LocalDate endDate = endDatePicker.getValue();
        LocalDate now = LocalDate.now();
        if (endDate == null || endDate.isBefore(now)) {
            paymentInfoLabel.setText("Please select a valid end date");
            return null;
        }
        // get number of days between now and end date
        long days = now.until(endDate).getDays();
        return days;
    }

    @FXML
    void searchAvailability(ActionEvent event) {
        String brand = brandComboBox.getValue();
        String model = modelComboBox.getValue();
        Office office = officeComboBox.getValue();
        LocalDate endDate = endDatePicker.getValue();

        // Call the BL to get the available vehicles
        List<VehicleModel> availableVehicles = bl.getAvailableVehicles(brand, model, office, endDate);

        // Set the data into the TableView
        vehicles.clear();
        vehicles.addAll(availableVehicles);
    }

    private void setOfficeCombobox() {
        officeComboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Office> call(ListView<Office> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Office item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });

        officeComboBox.setConverter(new StringConverter<Office>() {
            @Override
            public String toString(Office office) {
                return office == null ? null : office.getName();
            }

            @Override
            public Office fromString(String string) {
                // This is not needed for a ComboBox
                return null;
            }
        });

        List<Office> offices = bl.getOffices();
        for (Office office : offices) {
            officeComboBox.getItems().add(office);
        }

    }

    private void setColumns() {
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("plate"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public RentVehicleController(BlFacade bl) {
        this.bl = bl;
    }
}
