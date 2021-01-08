package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddProductController {

    @FXML
    private TextField NameAddProductText;

    @FXML
    private TextField InventoryAddProductText;

    @FXML
    private TextField PriceCostAddProductText;

    @FXML
    private TextField IDAddProductText;

    @FXML
    private TextField SearchPartText;

    @FXML
    private TextField MaxAddProductText;

    @FXML
    private TextField MinAddProductText;

    @FXML
    private Button AddProductCancelButton;

    @FXML
    private Button AddProductSaveButton;

    @FXML
    private TableView<?> PartChoicesAddTable;

    @FXML
    private TableColumn<?, ?> ChoicesTableAddPartID;

    @FXML
    private TableColumn<?, ?> ChoicesTableAddPartName;

    @FXML
    private TableColumn<?, ?> ChoicesTableAddInventoryLevel;

    @FXML
    private TableColumn<?, ?> ChoicesTableAddPricePerUnit;

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
    private Button AddProductSearchButton;

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
