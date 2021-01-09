package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;
    String currentView;

    AddPartController addPartController = new AddPartController();

    @FXML
    private RadioButton InHouseRadioButton;

    @FXML
    private ToggleGroup RadioButton;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private TextField NameModifyPartText;

    @FXML
    private TextField InventoryModifyPartText;

    @FXML
    private TextField PriceCostModifyPartText;

    @FXML
    private TextField IDModifyPartText;

    @FXML
    private TextField MaxModifyPartText;

    @FXML
    private TextField MinModifyPartText;

    @FXML
    private Button CancelButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Label DynamicModifyPartLabel;

    @FXML
    private TextField DynamicModifyPartText;

    @FXML
    void InHouseHandler(MouseEvent event) {
        addPartController.InHouseHandler(event);
    }

    @FXML
    void OutsourcedHandler(MouseEvent event) {
        addPartController.OutsourcedHandler(event);
    }

    @FXML
    void cancelView(MouseEvent event) throws IOException {
        addPartController.cancelView(event);
    }

    @FXML
    void savePart(MouseEvent event) throws IOException {

    }

    // override JavaFX's initialize to populate modify form with selected Part's data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int modifiedPartID = MainFormController.chosenIndex;
        Part modifiedPart = Inventory.getAllParts().get(modifiedPartID);
        IDModifyPartText.setText(Integer.toString(modifiedPart.getId()));
        NameModifyPartText.setText(modifiedPart.getName());
        PriceCostModifyPartText.setText(Double.toString(modifiedPart.getPrice()));
        InventoryModifyPartText.setText(Integer.toString(modifiedPart.getStock()));
        MinModifyPartText.setText(Integer.toString(modifiedPart.getMin()));
        MaxModifyPartText.setText(Integer.toString(modifiedPart.getMax()));
        if(modifiedPart instanceof InHouse){
            InHouseRadioButton.setSelected(true);
            DynamicModifyPartLabel.setText("Machine ID");
            DynamicModifyPartText.setText(Integer.toString(((InHouse)Inventory.getAllParts().get(modifiedPartID)).getMachineId()));
        } else {
            OutsourcedRadioButton.setSelected(true);
            DynamicModifyPartLabel.setText("Company Name");
            DynamicModifyPartText.setText(((Outsourced)Inventory.getAllParts().get(modifiedPartID)).getCompanyName());
        }
    }

}
