package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by user on 22/11/2015.
 */
public class ServerMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("Server.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Server.fxml"));
        Parent root = loader.load();
        // Loading the controller
        ServerController controller = loader.getController();
        controller.setMain(this);
        Server server = new Server(controller);
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


        public static void main(String[] args) {

            launch(args);


        }
    }


