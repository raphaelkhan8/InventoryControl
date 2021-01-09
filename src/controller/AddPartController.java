package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

    Stage stage;
    Parent scene;

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
    // When In-House radio button is clicked, set label to Machine ID
    void InHouseHandler(MouseEvent event) {
        DynamicAddPartLabel.setText("Machine ID");
    }

    @FXML
    // When Outsourced radio button is clicked, set label to Company Name
    void OutsourcedHandler(MouseEvent event) {
        DynamicAddPartLabel.setText("Company Name");
    }

    @FXML
    void cancelView(MouseEvent event) throws IOException {
        // go back to Main Form when Cancel button is clicked
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void savePart(MouseEvent event) {

    }

}


