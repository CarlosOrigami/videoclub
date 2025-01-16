package com.example.videoclub.controller;
import com.example.videoclub.model.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controlador {
    @FXML
    private TextField tituloField;

    @FXML
    private TextField generoField;

    @FXML
    private Spinner<Integer> anioSpinner;

    @FXML
    private ListView<Pelicula> listaPeliculas;

    @FXML
    private Label resultadoLabel;

    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configuración inicial del Spinner
        anioSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, 2023));
        listaPeliculas.setItems(peliculas);
    }

    @FXML
    private void agregarPelicula() {
        String titulo = tituloField.getText();
        String genero = generoField.getText();
        int anio = anioSpinner.getValue();

        if (titulo.isEmpty() || genero.isEmpty()) {
            resultadoLabel.setText("Error: Todos los campos son obligatorios.");
        } else {
            Pelicula pelicula = new Pelicula(titulo, genero, anio);
            peliculas.add(pelicula);
            resultadoLabel.setText("Película agregada: " + pelicula);
            limpiarCampos();
        }
    }

    @FXML
    private void eliminarPelicula() {
        Pelicula seleccionada = listaPeliculas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            peliculas.remove(seleccionada);
            resultadoLabel.setText("Película eliminada: " + seleccionada);
        } else {
            resultadoLabel.setText("Error: Seleccione una película para eliminar.");
        }
    }

    private void limpiarCampos() {
        tituloField.clear();
        generoField.clear();
        anioSpinner.getValueFactory().setValue(2023);
    }
}

