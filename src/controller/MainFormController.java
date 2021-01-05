package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class MainFormController {

    @FXML
    private Button exitButton;

    @FXML
    private TextField partSearchInputBox;

    @FXML
    private TableView<?> partsTable;

    @FXML
    private TableColumn<?, ?> PartID;

    @FXML
    private TableColumn<?, ?> PartName;

    @FXML
    private TableColumn<?, ?> PartInventoryLevel;

    @FXML
    private TableColumn<?, ?> PriceCostPerUnit;

    @FXML
    private Button AddPartButton;

    @FXML
    private Button ModifyPartButton;

    @FXML
    private Button DeletePartButton;

    @FXML
    private Button partSearchButton;

    @FXML
    private TextField productSearchInputBox;

    @FXML
    private TableView<?> productTable;

    @FXML
    private TableColumn<?, ?> ProductID;

    @FXML
    private TableColumn<?, ?> ProductName;

    @FXML
    private TableColumn<?, ?> ProductInventoryLevel;

    @FXML
    private TableColumn<?, ?> PricePerUnit;

    @FXML
    private Button AddProductButton;

    @FXML
    private Button ModifyProductButton;

    @FXML
    private Button DeleteProductButton;

    @FXML
    private Button productSearchButton;

    @FXML
    void addPart(MouseEvent event) {

    }

    @FXML
    void addProduct(MouseEvent event) {

    }

    @FXML
    void clearText(MouseEvent event) {

    }

    @FXML
    void deletePart(MouseEvent event) {

    }

    @FXML
    void deleteProduct(MouseEvent event) {

    }

    @FXML
    void exitProgram(ActionEvent event) {

    }

    @FXML
    void exitProgramButton(MouseEvent event) {

    }

    @FXML
    void modifyPart(MouseEvent event) {

    }

    @FXML
    void modifyProduct(MouseEvent event) {

    }

    @FXML
    void searchPart(MouseEvent event) {

    }

    @FXML
    void searchProduct(MouseEvent event) {

    }

}
