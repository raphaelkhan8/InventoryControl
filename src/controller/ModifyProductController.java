package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
        AssociatedPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Populate Product fields with the selected Product data
        Product modifiedProduct = Inventory.getAllProducts().get(modifiedProductIndex);
        IDModifyProductText.setText(Integer.toString(modifiedProduct.getId()));
        NameModifyProductText.setText(modifiedProduct.getName());
        InventoryModifyProductText.setText(Integer.toString(modifiedProduct.getStock()));
        PriceCostModifyProductText.setText(Double.toString(modifiedProduct.getPrice()));
        MinModifyProductText.setText(Integer.toString(modifiedProduct.getMax()));
        MaxModifyProductText.setText(Integer.toString(modifiedProduct.getMin()));
    }
}
