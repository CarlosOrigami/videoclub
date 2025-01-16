package com.example.videoclub.view;

import com.example.videoclub.model.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;


public class PeliculaView {

    private final TextField tituloField;
    private final TextField generoField;
    private final Spinner<Integer> anioSpinner;
    private final ListView<Pelicula> listaPeliculas;
    private final Label resultadoLabel;

    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();

    public PeliculaView(TextField tituloField, TextField generoField, Spinner<Integer> anioSpinner,
                        ListView<Pelicula> listaPeliculas, Label resultadoLabel) {
        this.tituloField = tituloField;
        this.generoField = generoField;
        this.anioSpinner = anioSpinner;
        this.listaPeliculas = listaPeliculas;
        this.resultadoLabel = resultadoLabel;
    }

    public void inicializarComponentes() {
        anioSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, 2023));
        listaPeliculas.setItems(peliculas);
    }

    public void agregarPelicula() {
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
    public void eliminarPelicula() {
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
