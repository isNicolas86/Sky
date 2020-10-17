package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = getClass().getClassLoader().getResource("css.fxml");
        // maybe handle if url is == null? This might throw a NullPointerException
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Sky");
        primaryStage.setScene(new Scene(root, 375, 475));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
