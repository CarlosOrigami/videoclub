package com.example.videoclub;

import com.example.videoclub.utils.PantallaUtils;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Mostrar la pantalla de login al inicio
            new PantallaUtils().showEstaPantalla(
                    stage,
                    "/com/example/videoclub/login.fxml", // Ruta del FXML para el login
                    "Videoclub - Inicio de Sesión",
                    400,  // Ancho de la ventana del login
                    300   // Alto de la ventana del login
            );

            // Configurar tamaño mínimo de la ventana
            stage.setMinWidth(400);
            stage.setMinHeight(300);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

