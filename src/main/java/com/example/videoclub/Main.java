package com.example.videoclub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoclub/hello-view.fxml"));
            VBox vbox = loader.load();

            // Crear la escena con un tamaño dinámico
            Scene scene = new Scene(vbox, 800, 600);  // 800x600 píxeles como ejemplo

            // Configurar la ventana principal
            stage.setTitle("Videoclub - Gestión de Películas");
            stage.setScene(scene);

            // Establecer tamaño mínimo de la ventana
            stage.setMinWidth(800);
            stage.setMinHeight(600);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar la aplicación: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

