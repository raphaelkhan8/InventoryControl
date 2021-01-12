package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.concurrent.atomic.AtomicBoolean;

public class MainFormController implements Initializable {

    Stage stage;
    Parent scene;
    // container for a modified Part/Product's index
    protected static int chosenIndex;

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
        // clear search input box when clicked on
        Object source = event.getSource();
        if (source.equals(partSearchInputBox)) {
            partSearchInputBox.setText("");
        } else {
            productSearchInputBox.setText("");
        }
    }

    @FXML
    void deletePart(MouseEvent event) {
        // delete the selected Part from parts table and alert user after part is removed
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Part part = partsTable.getSelectionModel().getSelectedItem();
        String deletedName = part.getName();
        AtomicBoolean proceed = Inventory.confirmMessage("Confirm", "Confirm Part Deletion", "Are you sure you want to delete " + deletedName + "?");
        if (!proceed.get()) {
            return;
        } else {
            Inventory.deletePart(part);
            Inventory.alertMessage("Deleted", "Part Deleted", deletedName + " has been deleted");
        }
    }

    @FXML
    void deleteProduct(MouseEvent event) {
        // delete the selected Product from products table and alert user after product is removed
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Product product = productTable.getSelectionModel().getSelectedItem();
        int numberOfAssociatedParts = product.getAllAssociatedParts().size();
        // prevent products with associated parts from being deleted
        if (numberOfAssociatedParts > 0) {
            Inventory.alertMessage("Error", "Product contains associated parts", "Delete associated parts before deleting product");
        } else {
            String deletedName = product.getName();
            AtomicBoolean proceed = Inventory.confirmMessage("Confirm", "Confirm Product Deletion", "Are you sure you want to delete " + deletedName + "?");
            if (!proceed.get()) {
                return;
            } else {
                Inventory.deleteProduct(product);
                Inventory.alertMessage("Deleted", "Product Deleted", deletedName + " has been deleted");
            }
        }
    }

    @FXML
    void exitProgram(MouseEvent event) {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void modifyPart(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        // capture the chosen Part to be modified
        chosenIndex = Inventory.getAllParts().indexOf((partsTable.getSelectionModel().getSelectedItem()));
        // open up the Modify Product form when part table's Modify button is clicked
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void modifyProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        // capture the chosen Product to be modified
        chosenIndex = Inventory.getAllProducts().indexOf((productTable.getSelectionModel().getSelectedItem()));
        // open up the Modify Product form when product table's Modify button is clicked
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void searchPart(MouseEvent event) {
        // Container for search output
        ObservableList<Part> foundPart = FXCollections.observableArrayList();
        // Parts Table search box functionality:
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        // Capture search box input
        String partSearchInput = partSearchInputBox.getText();
        // if the search input is empty, re-populate parts table with all available Parts
        if (partSearchInput.isEmpty()) {
            partsTable.setItems(Inventory.getAllParts());
        }
        try {
            // if search input starts with a number, use the ID lookupParts method
            if (Character.isDigit(partSearchInput.charAt(0))) {
                Part part = Inventory.lookupPart(Integer.parseInt(partSearchInput));
                if (!part.getName().isEmpty()) {
                    foundPart.add(Inventory.lookupPart(Integer.parseInt(partSearchInput)));
                }
            }
            // else (if search input is a string), use the Name lookupParts method
            else {
                foundPart = Inventory.lookupPart(partSearchInput);
            }
            // alert the user that the input Part was not found
            if (foundPart.isEmpty()) {
                String nameSearchErrorText = "The part with name " + partSearchInput + " was not found";
                Inventory.alertMessage("Error", "Part not Found :(", nameSearchErrorText);
            // if Part is found, populate Parts table with only the found like-Part(s)
            } else {
                partsTable.setItems(foundPart);
            }
        } catch (NullPointerException e) {
            String idSearchErrorText = "The part with id " + partSearchInput + " was not found";
            Inventory.alertMessage("Error", "Part not Found :(", idSearchErrorText);
        }
    }

    @FXML
    void searchProduct(MouseEvent event) {
        // Container for search output:
        ObservableList<Product> foundProduct = FXCollections.observableArrayList();
        // Products Table search box functionality:
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        // Capture search box input
        String productSearchInput = productSearchInputBox.getText();
        // if the search input is empty, re-populate products table with all available Products
        if (productSearchInput.isEmpty()) {
            productTable.setItems(Inventory.getAllProducts());
        }
        try {
            // if search input starts with a number, use the ID lookupProduct method
            if (Character.isDigit(productSearchInput.charAt(0))) {
                Product product = Inventory.lookupProduct(Integer.parseInt(productSearchInput));
                if (!product.getName().isEmpty()) {
                    foundProduct.add(Inventory.lookupProduct(Integer.parseInt(productSearchInput)));
                }
            }
            // else (if search input is a string), use the Name lookupProduct method
            else {
                foundProduct = Inventory.lookupProduct(productSearchInput);
            }
            // alert the user that the input Product was not found
            if (foundProduct.isEmpty()) {
                String nameSearchErrorText = "The product with name " + productSearchInput + " was not found";
                Inventory.alertMessage("Error", "Product not Found :(", nameSearchErrorText);
                // if Product is found, populate Products table with only the found like-Product(s)
            } else {
                productTable.setItems(foundProduct);
            }
        } catch (NullPointerException e) {
            String idSearchErrorText = "The product with id " + productSearchInput + " was not found";
            Inventory.alertMessage("Error", "Product not Found :(", idSearchErrorText);
        }
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
