package com.example.videoclub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Cargamos el archivo FXML de login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoclub/login.fxml"));
        Parent root = loader.load();

        // Creamos la escena y la asignamos al escenario principal
        Scene scene = new Scene(root, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login - Videoclub");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
