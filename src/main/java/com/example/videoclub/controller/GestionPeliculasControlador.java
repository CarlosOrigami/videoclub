package com.example.videoclub.controller;

import com.example.videoclub.model.Pelicula;
import com.example.videoclub.utils.Constantes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionPeliculasControlador extends BaseControlador {
    @FXML
    private TextField tituloField;

    @FXML
    private ComboBox<String> generoComboBox;

    @FXML
    private Spinner<Integer> anioSpinner;

    @FXML
    private ListView<Pelicula> listaPeliculas;

    @FXML
    private Label resultadoLabel;

    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarAnioSpinner();
        configurarComboBox();
        listaPeliculas.setItems(peliculas);
    }

    private void configurarAnioSpinner() {
        anioSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, 2023));
    }

    private void configurarComboBox() {
        ObservableList<String> generos = FXCollections.observableArrayList("Acción", "Ciencia ficción", "Drama", "Comedia", "Terror");
        generoComboBox.setItems(generos);
    }

    @FXML
    private void agregarPelicula() {
        String titulo = tituloField.getText().trim();
        String genero = generoComboBox.getValue();
        Integer anio = anioSpinner.getValue();

        if (titulo.isEmpty() || genero == null || genero.isEmpty()) {
            mostrarError(resultadoLabel, "Todos los campos son obligatorios.");
            return;
        }

        Pelicula pelicula = new Pelicula(titulo, genero, anio);
        peliculas.add(pelicula);
        mostrarResultado(resultadoLabel, "Película agregada: " + pelicula);
        limpiarCampos();
    }

    @FXML
    private void eliminarPelicula() {
        Pelicula seleccionada = listaPeliculas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarError(resultadoLabel, "Seleccione una película para eliminar.");
            return;
        }

        peliculas.remove(seleccionada);
        mostrarResultado(resultadoLabel, "Película eliminada: " + seleccionada);
    }

    private void limpiarCampos() {
        tituloField.clear();
        generoComboBox.setValue(null);
        if (anioSpinner.getValueFactory() != null) {
            anioSpinner.getValueFactory().setValue(2023);
        }
    }

    // Métodos para cambiar de pantalla
    @FXML
    private void irAFiltrarPeliculas() {
        cargarPantalla(Constantes.FILTRAR_PELICULAS.getDescripcion(), Constantes.TITULO_FILTRAR_PELICULAS.getDescripcion());
    }

    @FXML
    private void irAGestionarAlquileres() {
        cargarPantalla(Constantes.GESTIONAR_ALQUILERES.getDescripcion(), Constantes.TITULO_GESTIONAR_ALQUILERES.getDescripcion());
    }

    // Método genérico para cargar pantallas
    private void cargarPantalla(String rutaFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = (Stage) listaPeliculas.getScene().getWindow(); // Obtener ventana actual desde un nodo
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("No se pudo cargar la pantalla: " + rutaFXML);
        }
    }

    // Métodos auxiliares para mostrar mensajes
    public void mostrarResultado(Label label, String mensaje) {
        label.setText(mensaje);
        label.setStyle("-fx-text-fill: green;");
    }

    public void mostrarError(Label label, String mensaje) {
        label.setText(mensaje);
        label.setStyle("-fx-text-fill: red;");
    }
}
