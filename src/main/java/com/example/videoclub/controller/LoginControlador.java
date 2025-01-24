package com.example.videoclub.controller;

import com.example.videoclub.utils.Constantes;
import com.example.videoclub.utils.PantallaUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControlador {

    @FXML
    private Button iniciarSesionButton;  // Botón para iniciar sesión
    @FXML
    private TextField usuarioField;      // Campo para el nombre de usuario
    @FXML
    private PasswordField contrasenaField;  // Campo para la contraseña
    @FXML
    private Label mensajeError;
    // Etiqueta para mostrar errores
    private void cargarPantallaAdmin() {
        try {
            // Obtenemos el stage actual desde el botón de inicio de sesión
            Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();

            // Usamos el método showEstaPantalla de PantallaUtils
            PantallaUtils pantallaUtils = new PantallaUtils();
            pantallaUtils.showEstaPantalla(
                    stage,
                    Constantes.ADMIN.getDescripcion(),  // Ruta definida en la constante
                    Constantes.TITULO_ADMIN.getDescripcion(),  // Título definido en la constante
                    800, 600  // Ancho y alto
            );
        } catch (IOException e) {
            System.err.println("Error al cargar la pantalla de administración: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
