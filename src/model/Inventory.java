package model;

import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart) { }

    public void addProduct(Product newProduct) { }

    public Part lookupPart(int partId) {
        return null;
    }

    public Product lookupProduct(int productId) {
        return null;
    }

    public ObservableList<Part> lookupPart(String partName) {
        return null;
    }

    public ObservableList<Part> lookupProduct(String productName) {
        return null;
    }

    public void updatePart(int index, Part selectedPart) { }

    public void updateProduct(int index, Product selectedProduct) { }

    public boolean deletePart(Part selectedPart) {
        return false;
    }

    public boolean deleteProduct(Product selectedProduct) {
        return false;
    }

    public ObservableList<Part> getAllParts() {
        return this.allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return this.allProducts;
    }
}
