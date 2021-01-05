package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // add the passed-in part to the parts array
    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    // add the passed-in product to the products array
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    // loop through Parts array and return the part if it's id matches the passed-in id
    public Part lookupPart(int partId) {
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
    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        for (Part part: allParts) {
            String name = part.getName();
            if (name == partName) {
                foundParts.add(part);
            }
        }
        return foundParts;
    }

    // loop through Products array and return the product (as an array) if it's name matches the passed-in name
    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        for (Product product: allProducts) {
            String name = product.getName();
            if (name == productName) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    // Replace the part at the passed-in index of the Parts array with the passed-in selectedPart
    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    // Replace the part at the passed-in index of the Parts array with the passed-in selectedPart
    public void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    // Remove the Part and return a bool indicating whether or not the Part was deleted
    public boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    // Remove the Product and return a bool indicating whether or not the Product was deleted
    public boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
