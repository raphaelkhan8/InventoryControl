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
        controller.MainFormController controller = new controller.MainFormController();
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Control");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    // Add sample data
    void addSampleData(Inventory inv){

        // InHouse Parts
        // create in-house part instances
        Part inHouse1 = new InHouse(1, "Part 1", 2.50, 50, 25, 150, 101);
        Part inHouse2 = new InHouse(2, "Part 2", 5.00, 40, 10, 100, 102);
        Part inHouse3 = new InHouse(3, "Part 3", 10.99, 5, 4, 20, 103);
        Part inHouse4 = new InHouse(4, "Part 4", 6.00, 12, 10, 80, 104);
        Part inHouse5 = new InHouse(5, "Part 5", 14.99, 5, 2, 20, 105);
        // add the parts to inventory
        Inventory.addPart(inHouse1);
        Inventory.addPart(inHouse2);
        Inventory.addPart(inHouse3);
        Inventory.addPart(inHouse4);
        Inventory.addPart(inHouse5);

        // Outsourced Parts
        // create outsourced part instances
        Part outsourced1 = new Outsourced(6, "Part 6", 4.50, 48, 25, 150, "Acme Co");
        Part outsourced2 = new Outsourced(7, "Part 7", 5.50, 28, 10, 100, "Company Co");
        Part outsourced3 = new Outsourced(8, "Part 8", 6.50, 18, 4, 20, "Business Co");
        Part outsourced4 = new Outsourced(9, "Part 9", 7.50, 14, 10, 80, "Acme Co");
        Part outsourced5 = new Outsourced(10, "Part 10", 8.50, 48, 45, 80, "Co Co");
        // add the outsourced parts to inventory
        Inventory.addPart(outsourced1);
        Inventory.addPart(outsourced2);
        Inventory.addPart(outsourced3);
        Inventory.addPart(outsourced4);
        Inventory.addPart(outsourced5);

        // Products //
        // create product instances
        Product p1 = new Product(1, "Product 1", 10.00, 48, 20, 120);
        Product p2 = new Product(2, "Product 2", 15.00, 28, 10, 100);
        Product p3 = new Product(3, "Product 3", 20.00, 5, 2, 80);
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
