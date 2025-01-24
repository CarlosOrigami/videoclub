package com.example.videoclub.controller;

import com.example.videoclub.model.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestionAlquileresControlador extends BaseControlador {
    @FXML
    private ListView<Pelicula> listaPeliculas;

    @FXML
    private CheckBox unDiaCheckBox;

    @FXML
    private CheckBox tresDiasCheckBox;

    @FXML
    private CheckBox sieteDiasCheckBox;

    @FXML
    private Label resultadoLabel;

    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listaPeliculas.setItems(peliculas);
    }

    @FXML
    private void asignarPelicula() {
        Pelicula seleccionada = listaPeliculas.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarError(resultadoLabel, "No hay ninguna película seleccionada.");
            return;
        }

        int dias = unDiaCheckBox.isSelected() ? 1 : tresDiasCheckBox.isSelected() ? 3 : sieteDiasCheckBox.isSelected() ? 7 : 0;

        if (dias == 0) {
            mostrarError(resultadoLabel, "Seleccione un período de alquiler.");
            return;
        }

        mostrarResultado(resultadoLabel, "Película asignada: " + seleccionada.getTitulo() + " por " + dias + " días.");
    }
}
