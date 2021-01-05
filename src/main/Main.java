package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
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
}
