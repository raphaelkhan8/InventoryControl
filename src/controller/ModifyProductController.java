package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    // Container for parts added to product
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    // modified product's index
    int modifiedProductIndex = MainFormController.chosenIndex;

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
    private TableView<Part> AllPartsTable;

    @FXML
    private TableColumn<?, ?> AllPartsPartID;

    @FXML
    private TableColumn<?, ?> AllPartsPartName;

    @FXML
    private TableColumn<?, ?> AllPartsInventoryLevel;

    @FXML
    private TableColumn<?, ?> AllPartsPricePerUnit;

    @FXML
    private TableView<Part> AssociatedPartsTable;

    @FXML
    private TableColumn<?, ?> AssociatedPartID;

    @FXML
    private TableColumn<?, ?> AssociatedPartName;

    @FXML
    private TableColumn<?, ?> AssociatedPartInventoryLevel;

    @FXML
    private TableColumn<?, ?> AssociatedPartPricePerUnit;

    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private Button partChoicesAddButton;

    @FXML
    private Button ModifyProductSearchButton;

    @FXML
    void cancelView(MouseEvent event) throws IOException {
        AddPartController controller = new AddPartController();
        controller.cancelView(event);
    }

    @FXML
    void clearText(MouseEvent event) {
        SearchPartText.setText("");
    }

    @FXML
    void addPart(MouseEvent event) {
        int chosenPartIndex = Inventory.getAllParts().indexOf((AllPartsTable.getSelectionModel().getSelectedItem()));
        Part chosenPart = Inventory.getAllParts().get(chosenPartIndex);
        Inventory.getAllProducts().get(modifiedProductIndex).addAssociatedPart(chosenPart);
    }

    @FXML
    void lookupPart(MouseEvent event) {
        String partToSearch = SearchPartText.getText();
            ObservableList<Part> foundPartList;
            foundPartList = Inventory.lookupPart(partToSearch);
            // if searched Part isn't found, alert the user
            if (foundPartList.isEmpty()) {
                Inventory.alertMessage("Error", "Part not found", partToSearch + " was not found :(");
            } else {
                AllPartsTable.setItems(foundPartList);
            }
    }

    @FXML
    void removePart(MouseEvent event) {
        Part part = AssociatedPartsTable.getSelectionModel().getSelectedItem();
        String name = part.getName();
        AtomicBoolean proceed = Inventory.confirmMessage("Removal", "Removal Confirmation", "Are you sure you want to remove the associated part?");
        if (!proceed.get()) {
            return;
        } else {
            Inventory.getAllProducts().get(modifiedProductIndex).deleteAssociatedPart(part);
            Inventory.alertMessage("Removed", "Part successfully removed", name + " was successfully removed.");
        }
    }

    @FXML
    void saveProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();

        try {
            // Capture user input:
            int productID = Integer.parseInt(IDModifyProductText.getText());
            String productName = NameModifyProductText.getText();
            double productPrice = Double.parseDouble(PriceCostModifyProductText.getText());
            int productStock = Integer.parseInt(InventoryModifyProductText.getText());
            int productMin = Integer.parseInt(MinModifyProductText.getText());
            int productMax = Integer.parseInt(MaxModifyProductText.getText());
            // Input validation
            if (productName.isEmpty()) {
                Inventory.alertMessage("Error", "Name field is blank", "Name must be filled out. Please try again.");
                return;
            }
            if (productMin > productMax) {
                Inventory.alertMessage("Error", "Min/Max Error", "Min must be less than Max. Please try again.");
            }
            else if (productStock < productMin || productStock > productMax) {
                Inventory.alertMessage("Error", "Inventory Error", "Inventory amount must be in-between Min and Max.");
            } else {
                // Create a new Product instance using user input
                Product product = new Product(productID, productName, productPrice, productStock, productMin, productMax);
                // Loop through current product and add all it's associated parts to new Product
                ObservableList<Part> partsList = Inventory.getAllProducts().get(modifiedProductIndex).getAllAssociatedParts();
                for (Part part : partsList) {
                    product.addAssociatedPart(part);
                }
                // Update current product with new one
                Inventory.updateProduct(modifiedProductIndex, product);
                // Go back to Main Form after Product is added
                scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NullPointerException e) {
            Inventory.alertMessage("Error", "Empty Field Error", "One or more fields are empty. Please try again.");
        } catch (NumberFormatException e) {
            Inventory.alertMessage("Error", "Input Field Error", "One or more field inputs are invalid. Please try again.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate All Parts table
        AllPartsTable.setItems(Inventory.getAllParts());
        AllPartsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AllPartsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AllPartsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AllPartsPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Populate Associated Parts table
        AssociatedPartsTable.setItems(Inventory.getAllProducts().get(modifiedProductIndex).getAllAssociatedParts());
        AssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));;
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Populate Product fields with the selected Product data
        Product modifiedProduct = Inventory.getAllProducts().get(modifiedProductIndex);
        IDModifyProductText.setText(Integer.toString(modifiedProduct.getId()));
        NameModifyProductText.setText(modifiedProduct.getName());
        InventoryModifyProductText.setText(Integer.toString(modifiedProduct.getStock()));
        PriceCostModifyProductText.setText(Double.toString(modifiedProduct.getPrice()));
        MinModifyProductText.setText(Integer.toString(modifiedProduct.getMin()));
        MaxModifyProductText.setText(Integer.toString(modifiedProduct.getMax()));
    }
}
