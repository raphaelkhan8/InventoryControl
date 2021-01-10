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
    // modified part's index
    int modifiedPartIndex = MainFormController.chosenIndex;

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
        DynamicModifyPartLabel.setText("Machine ID");
        currentView = "InHouse";
    }

    @FXML
    void OutsourcedHandler(MouseEvent event) {
        DynamicModifyPartLabel.setText("Company Name");
        currentView = "Outsourced";
    }

    @FXML
    void cancelView(MouseEvent event) throws IOException {
        AddPartController addPartController = new AddPartController();
        addPartController.cancelView(event);
    }

    @FXML
    void savePart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        // capture user input:
        int id = Integer.parseInt(IDModifyPartText.getText());
        String newName = NameModifyPartText.getText();
        double newPrice = Double.parseDouble(PriceCostModifyPartText.getText());
        int newStock = Integer.parseInt(InventoryModifyPartText.getText());
        int newMin = Integer.parseInt(MinModifyPartText.getText());
        int newMax = Integer.parseInt(MaxModifyPartText.getText());
        // If current view is InHouse, create a new InHouse instance and replace the old InHouse with the new one
        if (currentView.equals("InHouse")) {
            int newMachineID = Integer.parseInt(DynamicModifyPartText.getText());
            InHouse iPart = new InHouse(id, newName, newPrice, newStock, newMin, newMax, newMachineID);
            Inventory.updatePart(modifiedPartIndex, iPart);
            Inventory.alertMessage("Modified", "Part Modified", "The In-House Part was modified.");
        } else {
            // Else, create a new Outsourced instance and replace old Outsourced with the new one
            String newCompanyName = DynamicModifyPartText.getText();
            Outsourced oPart = new Outsourced(id, newName, newPrice, newStock, newMin, newMax, newCompanyName);
            Inventory.updatePart(modifiedPartIndex, oPart);
            Inventory.alertMessage("Modified", "Part Modified", "The Outsourced Part was modified.");
        }
        // Go back to Main Form after Part has been modified
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    // override JavaFX's initialize to populate modify form with selected Part's data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Part modifiedPart = Inventory.getAllParts().get(modifiedPartIndex);
        IDModifyPartText.setText(Integer.toString(modifiedPart.getId()));
        NameModifyPartText.setText(modifiedPart.getName());
        PriceCostModifyPartText.setText(Double.toString(modifiedPart.getPrice()));
        InventoryModifyPartText.setText(Integer.toString(modifiedPart.getStock()));
        MinModifyPartText.setText(Integer.toString(modifiedPart.getMin()));
        MaxModifyPartText.setText(Integer.toString(modifiedPart.getMax()));
        if(modifiedPart instanceof InHouse){
            currentView = "InHouse";
            InHouseRadioButton.setSelected(true);
            DynamicModifyPartLabel.setText("Machine ID");
            DynamicModifyPartText.setText(Integer.toString(((InHouse)Inventory.getAllParts().get(modifiedPartIndex)).getMachineId()));
        } else {
            currentView = "Outsourced";
            OutsourcedRadioButton.setSelected(true);
            DynamicModifyPartLabel.setText("Company Name");
            DynamicModifyPartText.setText(((Outsourced)Inventory.getAllParts().get(modifiedPartIndex)).getCompanyName());
        }
    }

}
