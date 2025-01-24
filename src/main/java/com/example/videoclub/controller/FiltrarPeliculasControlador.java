package com.example.videoclub.controller;

import com.example.videoclub.model.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FiltrarPeliculasControlador {
    @FXML
    private TextField tituloFiltroField;

    @FXML
    private ComboBox<String> generoFiltroComboBox;

    @FXML
    private Spinner<Integer> anioFiltroSpinner;

    @FXML
    private ListView<Pelicula> listaPeliculas;

    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();
    private final ObservableList<Pelicula> peliculasFiltradas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarFiltros();
        listaPeliculas.setItems(peliculasFiltradas);
    }

    private void configurarFiltros() {
        ObservableList<String> generos = FXCollections.observableArrayList("Todos", "Acción", "Ciencia ficción", "Drama", "Comedia", "Terror");
        generoFiltroComboBox.setItems(generos);
        generoFiltroComboBox.setValue("Todos");

        tituloFiltroField.textProperty().addListener((observable, oldValue, newValue) -> filtrarPeliculas());
        generoFiltroComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filtrarPeliculas());
        anioFiltroSpinner.valueProperty().addListener((observable, oldValue, newValue) -> filtrarPeliculas());
    }

    private void filtrarPeliculas() {
        String filtroTitulo = tituloFiltroField.getText().trim().toLowerCase();
        String filtroGenero = generoFiltroComboBox.getValue();
        Integer filtroAnio = anioFiltroSpinner.getValue();

        peliculasFiltradas.setAll(peliculas.filtered(pelicula -> {
            boolean coincideTitulo = pelicula.getTitulo().toLowerCase().contains(filtroTitulo);
            boolean coincideGenero = filtroGenero.equals("Todos") || pelicula.getGenero().equals(filtroGenero);
            boolean coincideAnio = filtroAnio == null || pelicula.getAnio() == filtroAnio;
            return coincideTitulo && coincideGenero && coincideAnio;
        }));
    }
}
