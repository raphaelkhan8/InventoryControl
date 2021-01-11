package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    // Constructor
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /// Setters ///
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setMin(int min) { this.min = min; }
    public void setMax(int max) { this.max = max; }

    /// Getters ///
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public int getMin() { return min; }
    public int getMax() { return max; }

    /** Add Part to Product */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** Delete Part from Product if it is in associatedParts array */
    public boolean deleteAssociatedPart(Part part) {
        if (associatedParts.contains(part)) {
            associatedParts.remove(part);
            return true;
        } else {
            return false;
        }
    }

    /** Return a list of all Parts in Product */
    public ObservableList<Part> getAllAssociatedParts() {
        return this.associatedParts;
    }
}
