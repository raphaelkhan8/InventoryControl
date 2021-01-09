package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private Button exitButton;

    @FXML
    private TextField partSearchInputBox;

    @FXML
    private TableView<Part> partsTable;

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
    private TableView<Product> productTable;

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
    void addPart(MouseEvent event) throws IOException {
        // open up the AddPart form when part table Add button is clicked
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void addProduct(MouseEvent event) throws IOException {
        // open up the AddProduct form when product table Add button is clicked
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void clearText(MouseEvent event) {

    }

    @FXML
    void deletePart(MouseEvent event) {
        // delete the selected Part from parts table and alert user after part is removed
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Part part = partsTable.getSelectionModel().getSelectedItem();
        String deletedName = part.getName();
        String deletedPartAlert = deletedName += " has been deleted";
        Inventory.deletePart(part);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deleted");
        alert.setHeaderText("Part Deleted");
        alert.setContentText(deletedPartAlert);
        alert.showAndWait();
    }

    @FXML
    void deleteProduct(MouseEvent event) {
        // delete the selected Product from products table and alert user after product is removed
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Product product = productTable.getSelectionModel().getSelectedItem();
        String deletedName = product.getName();
        String deletedProductAlert = deletedName += " has been deleted";
        Inventory.deleteProduct(product);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deleted");
        alert.setHeaderText("Product Deleted");
        alert.setContentText(deletedProductAlert);
        alert.showAndWait();
    }

    @FXML
    void exitProgram(MouseEvent event) {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
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

    // override JavaFX's initialize to populate main form with sample data
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Populate Parts table
        partsTable.setItems(Inventory.getAllParts());
        PartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceCostPerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populate Products table
        productTable.setItems(Inventory.getAllProducts());
        ProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
