package eus.ehu.karkrash.ui;

import eus.ehu.karkrash.businessLogic.BlFacade;
import eus.ehu.karkrash.domain.Office;
import eus.ehu.karkrash.model.VehicleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
        bl.getBrands().forEach(modelComboBox.getItems()::add);

        // Sample data - replace this with data retrieved from your DataAccess class
        vehicles.add(new VehicleModel("1234ABC", "Audi", "A4", 50.0));
        vehicles.add(new VehicleModel("5678DEF", "Renault", "Clio", 30.0));

        // Set the data into the TableView
        vehiclesTableView.setItems(vehicles);

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
