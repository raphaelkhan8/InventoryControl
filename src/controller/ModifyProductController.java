package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ModifyProductController {

    @FXML
    private TextField NameModifyProductText;

    @FXML
    private TextField InventoryModifyProductText;

    @FXML
    private TextField PriceCostModifyProductText;

    @FXML
    private TextField IDModifyProductText;

    @FXML
    private TextField SearchPartText;

    @FXML
    private TextField MaxModifyProductText;

    @FXML
    private TextField MinModifyProductText;

    @FXML
    private Button ModifyProductCancelButton;

    @FXML
    private Button ModifyProductSaveButton;

    @FXML
    private TableView<?> PartChoicesModifyTable;

    @FXML
    private TableColumn<?, ?> ChoicesTableModifyPartID;

    @FXML
    private TableColumn<?, ?> ChoicesTableModifyPartName;

    @FXML
    private TableColumn<?, ?> ChoicesTableModifyInventoryLevel;

    @FXML
    private TableColumn<?, ?> ChoicesTableModifyPricePerUnit;

    @FXML
    private TableView<?> AssociatedPartsTable;

    @FXML
    private TableColumn<?, ?> removePartID;

    @FXML
    private TableColumn<?, ?> removePartName;

    @FXML
    private TableColumn<?, ?> removeInventoryLevel;

    @FXML
    private TableColumn<?, ?> removePricePerUnit;

    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private Button partChoicesAddButton;

    @FXML
    private Button ModifyProductSearchButton;

    @FXML
    void addPart(MouseEvent event) {

    }

    @FXML
    void cancelView(MouseEvent event) {

    }

    @FXML
    void lookupPart(MouseEvent event) {

    }

    @FXML
    void removePart(MouseEvent event) {

    }

    @FXML
    void saveProduct(MouseEvent event) {

    }

}
