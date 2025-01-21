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
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField contrasenaField;

    @FXML
    private Button iniciarSesionButton;

    @FXML
    private Label mensajeError;

    private final List<Usuario> usuarios = new ArrayList<>();

    @FXML
    public void initialize() {
        // Inicializamos la lista de usuarios
        inicializarUsuarios();

        // Configuramos el botón de iniciar sesión
        iniciarSesionButton.setOnAction(event -> {
            try {
                validarCredenciales();
            } catch (IOException e) {
                mensajeError.setText("Error al cambiar de pantalla.");
            }
        });
    }

    private void inicializarUsuarios() {
        usuarios.add(new Usuario("admin", "1234", "admin"));
        usuarios.add(new Usuario("user1", "abcd", "normal"));
        usuarios.add(new Usuario("user2", "asd", "normal"));
    }

    private void validarCredenciales() throws IOException {
        String usuarioIngresado = usuarioField.getText();
        String contrasenaIngresada = contrasenaField.getText();

        Usuario usuarioValidado = buscarUsuario(usuarioIngresado, contrasenaIngresada);

        if (usuarioValidado != null) {
            Stage stage = (Stage) iniciarSesionButton.getScene().getWindow();
            if ("admin".equals(usuarioValidado.getRol())) {
                // Cargar pantalla de administración
                new PantallaUtils().showEstaPantalla(
                        stage,
                        Constantes.PAGINA_INICIAL.getDescripcion(),
                        "Panel de Administración",
                        800,
                        600
                );
            } else {
                // Cargar pantalla de usuario normal
                new PantallaUtils().showEstaPantalla(
                        stage,
                        Constantes.PAGINA_SEGUNDA_PANTALLA.getDescripcion(),
                        "Panel de Usuario",
                        800,
                        600
                );
            }
        } else {
            mensajeError.setText("Usuario o contraseña incorrectos.");
        }
    }

    private Usuario buscarUsuario(String nombre, String contrasena) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContrasena().equals(contrasena)) {
                return usuario;
            }
        }
        return null;
    }

    // Clase interna para manejar usuarios
    private static class Usuario {
        private final String nombre;
        private final String contrasena;
        private final String rol;

        public Usuario(String nombre, String contrasena, String rol) {
            this.nombre = nombre;
            this.contrasena = contrasena;
            this.rol = rol;
        }

        public String getNombre() {
            return nombre;
        }

        public String getContrasena() {
            return contrasena;
        }

        public String getRol() {
            return rol;
        }
    }
}