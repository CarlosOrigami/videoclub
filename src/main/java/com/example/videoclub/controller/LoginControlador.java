package com.example.videoclub.controller;

import com.example.videoclub.utils.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControlador {

    @FXML
    private Button iniciarSesionButton;  // Botón para iniciar sesión
    @FXML
    private TextField usuarioField;      // Campo para el nombre de usuario
    @FXML
    private PasswordField contrasenaField;  // Campo para la contraseña
    @FXML
    private Label mensajeError;         // Etiqueta para mostrar errores

    // Credenciales predefinidas para el administrador
    private static final String ADMIN_USUARIO = "admin";
    private static final String ADMIN_CONTRASENA = "1234";

    // Método que se invoca al hacer clic en el botón
    @FXML
    private void manejarInicioSesion() {
        String usuario = usuarioField.getText();
        String contrasena = contrasenaField.getText();

        // Validación de credenciales
        if (ADMIN_USUARIO.equals(usuario) && ADMIN_CONTRASENA.equals(contrasena)) {
            mensajeError.setVisible(false);  // Ocultamos el mensaje de error
            cargarPantallaAdmin();
        } else {
            mensajeError.setText("Usuario o contraseña incorrectos");
            mensajeError.setVisible(true);  // Mostramos el mensaje de error
        }
    }

    // Método para cargar la pantalla principal del administrador
    private void cargarPantallaAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.GESTIONAR_PELICULAS.getDescripcion()));
            Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Pantalla Principal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método que se ejecuta cuando el controlador se inicializa
    @FXML
    public void initialize() {
        if (iniciarSesionButton != null) {
            System.out.println("Botón 'Iniciar Sesión' inicializado correctamente");
        } else {
            System.out.println("El botón 'Iniciar Sesión' no se ha inicializado");
        }
    }
}
