package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;

public class AddPartController {

    Stage stage;
    Parent scene;
    String currentView;

    @FXML
    private RadioButton InHouseRadioButton;

    @FXML
    private ToggleGroup RadioButton;

    @FXML
    private RadioButton OutsourcedRadioButton;

    @FXML
    private TextField NameAddPartText;

    @FXML
    private TextField InventoryAddPartText;

    @FXML
    private TextField PriceCostAddPartText;

    @FXML
    private TextField IDAddPartText;

    @FXML
    private TextField MaxAddPartText;

    @FXML
    private TextField MinAddPartText;

    @FXML
    private Button AddPartCancelButton;

    @FXML
    private Button AddPartSaveButton;

    @FXML
    private Label DynamicAddPartLabel;

    @FXML
    private TextField DynamicAddPartText;

    @FXML
    /** When In-House radio button is clicked, set label to Machine ID */
    void InHouseHandler(MouseEvent event) {
        DynamicAddPartLabel.setText("Machine ID");
        currentView = "InHouse";
    }

    @FXML
    /** When Outsourced radio button is clicked, set label to Company Name */
    void OutsourcedHandler(MouseEvent event) {
        DynamicAddPartLabel.setText("Company Name");
        currentView = "Outsourced";
    }

    @FXML
    /** Go back to Main Form when Cancel button is clicked */
    void cancelView(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void savePart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();

        try {
            // Capture user input:
            String partName = new String(NameAddPartText.getText());
            double partPrice = Double.parseDouble(PriceCostAddPartText.getText());
            int partInventory = Integer.parseInt(InventoryAddPartText.getText());
            int partMin = Integer.parseInt(MinAddPartText.getText());
            int partMax = Integer.parseInt(MaxAddPartText.getText());
            // get the last Parts ID # and add 1 to it to create the new part's ID
            int partID = Inventory.getLastId(0) + 1;

            // Input validation:
            if (partName.isEmpty()) {
                Inventory.alertMessage("Error", "Name field is blank", "Name must be filled out. Please try again.");
                return;
            }
            if (partMin > partMax) {
                Inventory.alertMessage("Error", "Min/Max Error", "Min must be less than Max. Please try again.");
            }
            else if (partInventory < partMin || partInventory > partMax) {
                Inventory.alertMessage("Error", "Inventory Error", "Inventory amount must be in-between Min and Max.");
            }
            else {
                // if InHouse radio button clicked
                if (currentView.equals("InHouse")) {
                    // get the input machine ID number
                    int machineId = Integer.parseInt(DynamicAddPartText.getText());
                    // Create a new InHouse instance and add it to Inventory
                    InHouse newIPart = new InHouse(partID, partName, partPrice, partInventory, partMin, partMax, machineId);
                    Inventory.addPart(newIPart);
                    Inventory.alertMessage("Added", "In-House Part Added", partName + " was added to the Inventory");
                    // go back to main form after the Part is added
                }
                // if Outsourced radio button clicked:
                else {
                    String companyName = DynamicAddPartText.getText();
                    Outsourced newOPart = new Outsourced(partID, partName, partPrice, partInventory, partMin, partMax, companyName);
                    Inventory.addPart(newOPart);
                    Inventory.alertMessage("Added", "Outsourced Part Added", partName + " was added to the Inventory");
                }
                // go back to main form after the Part is added
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NumberFormatException e) {
            Inventory.alertMessage("Error", "Input Field Error", "One or more field inputs are invalid. Please try again.");
        } catch (NullPointerException e) {
            Inventory.alertMessage("Error", "Empty Field Error", "One or more fields are empty. Please try again.");
        }
    }

}


