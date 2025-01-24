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
    private Button iniciarSesionButton;  // Vinculación del botón con el fx:id en el FXML
    @FXML
    private TextField usuarioField;      // Campo para el nombre de usuario
    @FXML
    private PasswordField contrasenaField;  // Campo para la contraseña
    @FXML
    private Label mensajeError;         // Etiqueta para mostrar errores

    // Método que se invoca al hacer clic en el botón
    @FXML
    private void manejarInicioSesion() {
        // Lógica de validación de usuario y contraseña
        String usuario = usuarioField.getText();
        String contrasena = contrasenaField.getText();

        // Validamos si el usuario existe en el repositorio
        Usuario usuarioAutenticado = Usuario.UsuarioRepository.validarUsuario(usuario, contrasena);

        if (usuarioAutenticado != null) {
            mensajeError.setVisible(false);  // Ocultamos el mensaje de error

            if (usuarioAutenticado.esAdmin()) {
                // Si es admin, cargamos la pantalla principal
                cargarPantallaAdmin();
            } else {
                // Si es un user normal, redirigimos a una pantalla diferente
                cargarPantallaUser();
            }
        } else {
            mensajeError.setText("Usuario o contraseña incorrectos");
            mensajeError.setVisible(true);  // Mostramos el mensaje de error
        }
    }



    // Este método carga la pantalla principal después del login exitoso
    private void cargarPantallaAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constantes.PAGINA_ADMIN.getDescripcion()));
            Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Pantalla Principal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void cargarPantallaUser() {
        try {
            // Cargamos el archivo FXML para la vista de usuario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/videoclub/user-view.fxml"));
            Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Vista de Usuario");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Este método se ejecuta cuando el controlador se inicializa
    @FXML
    public void initialize() {
        if (iniciarSesionButton != null) {
            // Aquí puedes realizar cualquier inicialización extra si es necesario
            System.out.println("Botón 'Iniciar Sesión' inicializado correctamente");
        } else {
            System.out.println("El botón 'Iniciar Sesión' no se ha inicializado");
        }
    }
}

