package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class AddPartController {

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
    private TextField IDAddPartText1;

    @FXML
    void InHouseHandler(MouseEvent event) {

    }

    @FXML
    void OutsourcedHandler(MouseEvent event) {

    }

    @FXML
    void cancelView(MouseEvent event) {

    }

    @FXML
    void savePart(MouseEvent event) {

    }

}


