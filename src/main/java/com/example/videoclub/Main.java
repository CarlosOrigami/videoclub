package com.example.videoclub;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Ruta al archivo FXML desde el classpath
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoclub/hello-view.fxml"));
        VBox vbox = loader.load();

        // Crear la escena
        Scene scene = new Scene(vbox, 300, 500);

        // Configurar la ventana
        stage.setTitle("Videoclub - Gestión de Películas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
