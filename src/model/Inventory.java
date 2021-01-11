package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.concurrent.atomic.AtomicBoolean;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // add the passed-in part to the parts array
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    // add the passed-in product to the products array
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    // loop through Parts array and return the part if it's id matches the passed-in id
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            int id = part.getId();
            if (id == partId) {
                return part;
            }
        }
        return null;
    }

    // loop through Products array and return the product if it's id matches the passed-in product
    public Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            int id = product.getId();
            if (id == productId) {
                return product;
            }
        }
        return null;
    }

    // loop through Parts array and return the part (as an array) if it's name matches the passed-in name
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        for (Part part: allParts) {
            if (part.getName().equals(partName)) {
                foundParts.add(part);
            }
        }
        return foundParts;
    }

    // loop through Products array and return the product (as an array) if it's name matches the passed-in name
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        for (Product product: allProducts) {
            if (product.getName().equals(productName)) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    // Replace the part at the passed-in index of the Parts array with the passed-in selectedPart
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    // Replace the part at the passed-in index of the Parts array with the passed-in selectedPart
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    // Remove the Part and return a bool indicating whether or not the Part was deleted
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    // Remove the Product and return a bool indicating whether or not the Product was deleted
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    // used to get last part or product's ID in order to set a new ID
    public static int getLastId(int partsOrProduct) {
        int lastID;
        // 0 stands for Part
        if (partsOrProduct == 0) {
            int length = allParts.size();
            lastID = allParts.get(length - 1).getId();
        } else {
            // 1 stands for Product
            int length = allProducts.size();
            lastID = allProducts.get(length - 1).getId();
        }
        return lastID;
    }

    // for message pop-ups
    public static void alertMessage(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // pop-up to confirm deletion of data
    public static AtomicBoolean confirmMessage(String title, String header, String message) {
        AtomicBoolean proceed = new AtomicBoolean(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                proceed.set(true);
            }});
        return proceed;
    }
}
