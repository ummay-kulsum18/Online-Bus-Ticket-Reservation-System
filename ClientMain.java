package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ClientMain extends Application {
    ClientController controller;
    NetworkUtil nc;
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        //Parent root = FXMLLoader.load(getC;lass().getResource("client.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("client.fxml"));
        Parent root = loader.load();
        controller=loader.getController();
        stage.setTitle("Client");
        stage.setScene(new Scene(root, 750, 550));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
        Platform.exit();
    }
}
