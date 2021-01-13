package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {

    Inventory sampleInv = new Inventory();

    @Override
    public void start(Stage primaryStage) throws Exception{
        addSampleData(sampleInv);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainForm.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Control");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    /** Add sample data */
    void addSampleData(Inventory inv){

        // InHouse Parts //
        // create in-house part instances
        Part inHouse1 = new InHouse(1, "IP1", 2.50, 50, 25, 150, 101);
        Part inHouse2 = new InHouse(2, "IP2", 5.00, 40, 10, 100, 102);
        Part inHouse3 = new InHouse(3, "IP3", 10.99, 5, 4, 20, 103);
        Part inHouse4 = new InHouse(4, "IP4", 6.00, 12, 10, 80, 104);
        // add the parts to inventory
        Inventory.addPart(inHouse1);
        Inventory.addPart(inHouse2);
        Inventory.addPart(inHouse3);
        Inventory.addPart(inHouse4);

        // Outsourced Parts //
        // create outsourced part instances
        Part outsourced1 = new Outsourced(5, "OP1", 4.50, 48, 25, 150, "Acme Co");
        Part outsourced2 = new Outsourced(6, "OP2", 5.50, 28, 10, 100, "Company Co");
        Part outsourced3 = new Outsourced(7, "OP3", 6.50, 18, 4, 20, "Business Co");
        Part outsourced4 = new Outsourced(8, "OP4", 8.50, 48, 45, 80, "Co Co");
        // add the outsourced parts to inventory
        Inventory.addPart(outsourced1);
        Inventory.addPart(outsourced2);
        Inventory.addPart(outsourced3);
        Inventory.addPart(outsourced4);

        // Products //
        // create product instances
        Product p1 = new Product(1, "P1", 10.00, 48, 20, 120);
        Product p2 = new Product(2, "P2", 15.00, 28, 10, 100);
        Product p3 = new Product(3, "P3", 20.00, 5, 2, 80);
        // associate parts with each product
        p1.addAssociatedPart(inHouse1);
        p1.addAssociatedPart(outsourced1);
        p2.addAssociatedPart(inHouse2);
        p2.addAssociatedPart(outsourced2);
        p3.addAssociatedPart(inHouse3);
        p3.addAssociatedPart(outsourced3);
        // add the products to inventory
        Inventory.addProduct(p1);
        Inventory.addProduct(p2);
        Inventory.addProduct(p3);
    }
}
