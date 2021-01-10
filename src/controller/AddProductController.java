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
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;

    // Container for parts added to product
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

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
    private TableColumn<?, ?> AssociatedInventoryLevel;

    @FXML
    private TableColumn<?, ?> AssociatedPricePerUnit;

    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private Button partChoicesAddButton;

    @FXML
    private Button AddProductSearchButton;

    @FXML
    void clearText(MouseEvent event) {
        SearchPartText.setText("");
    }

    @FXML
    void cancelView(MouseEvent event) throws IOException {
        AddPartController controller = new AddPartController();
        controller.cancelView(event);
    }

    @FXML
    void addPart(MouseEvent event) {
        int chosenIndex = Inventory.getAllParts().indexOf((AllPartsTable.getSelectionModel().getSelectedItem()));
        associatedParts.add(Inventory.getAllParts().get(chosenIndex));
    }

    @FXML
    void lookupPart(MouseEvent event) {
        String partToSearch = SearchPartText.getText();
        ObservableList<Part> foundPartList;
        foundPartList = Inventory.lookupPart(partToSearch);
        // if searched Part isn't found, alert the user
        if(foundPartList.isEmpty()) {
            Inventory.alertMessage("Error", "Part not found", partToSearch + " was not found :(");
        } else {
            AllPartsTable.setItems(foundPartList);
        }
    }

    @FXML
    void removePart(MouseEvent event) {
        Part removedPart = AssociatedPartsTable.getSelectionModel().getSelectedItem();
        String name = removedPart.getName();
        Inventory.confirmMessage("Removal", "Removal Confirmation", "Are you sure you want to remove the associated part?");
        associatedParts.remove(removedPart);
        Inventory.alertMessage("Removed", "Part successfully removed", name + " was successfully removed.");

    }

    @FXML
    void saveProduct(MouseEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();

        // Call getLastId (passing in 1 for Product) to get the last product's index
        int productID = Inventory.getLastId(1) + 1;
        // Capture user input:
        String productName = NameAddProductText.getText();
        double productPrice = Double.parseDouble(PriceCostAddProductText.getText());
        int productStock = Integer.parseInt(InventoryAddProductText.getText());
        int productMin = Integer.parseInt(MinAddProductText.getText());
        int productMax = Integer.parseInt(MaxAddProductText.getText());
        // Create a new Product instance using user input and add it to the Inventory
        Product product = new Product(productID, productName, productPrice, productStock, productMin, productMax);
        Inventory.addProduct(product);

        // Go back to Main Form after Product is added
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
        AssociatedPartsTable.setItems(associatedParts);
        AssociatedPartID.setCellValueFactory(new PropertyValueFactory<>("id"));;
        AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
