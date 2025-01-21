package com.example.videoclub.controller;

import com.example.videoclub.model.Pelicula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controlador {
    @FXML
    private TextField tituloFiltroField;
    @FXML
    private ComboBox<String> generoFiltroComboBox;
    @FXML
    private Spinner<Integer> anioFiltroSpinner;

    @FXML
    private RadioButton unDiaRadioButton;
    @FXML
    private RadioButton tresDiasRadioButton;
    @FXML
    private RadioButton sieteDiasRadioButton;

    @FXML
    private CheckBox unDiaCheckBox;
    @FXML
    private CheckBox tresDiasCheckBox;
    @FXML
    private CheckBox sieteDiasCheckBox;

    @FXML
    private TextField tituloField;

    @FXML
    private ComboBox<String> generoComboBox;  // Cambiar de TextField a ComboBox

    @FXML
    private Spinner<Integer> anioSpinner;

    @FXML
    private ListView<Pelicula> listaPeliculas;

    @FXML
    private Label resultadoLabel;

    private final ObservableList<Pelicula> peliculas = FXCollections.observableArrayList();
    private final ObservableList<Pelicula> peliculasFiltradas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarPeriodoGroup();
        configurarAnioSpinner();
        configurarComboBoxes();
        inicializarPeliculas();
        configurarFiltros();
    }

    private void configurarPeriodoGroup() {
        ToggleGroup periodoGroup = new ToggleGroup();
        unDiaRadioButton.setToggleGroup(periodoGroup);
        tresDiasRadioButton.setToggleGroup(periodoGroup);
        sieteDiasRadioButton.setToggleGroup(periodoGroup);
    }

    private void configurarAnioSpinner() {
        // Configuración inicial del Spinner para los años
        anioSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, 2023));
    }

    private void configurarComboBoxes() {
        // Inicializamos el ComboBox con algunos géneros, incluyendo "Todos" al inicio
        ObservableList<String> generos = FXCollections.observableArrayList("Todos", "Acción", "Ciencia ficción", "Drama", "Comedia", "Terror");
        generoComboBox.setItems(generos);
        generoFiltroComboBox.setItems(generos);

        // Seleccionar la opción "Todos" por defecto
        generoComboBox.setValue("Todos");
        generoFiltroComboBox.setValue("Todos");
    }

    private void inicializarPeliculas() {
        // Inicializamos el ArrayList de películas con algunas películas de ejemplo
        peliculas.add(new Pelicula("Inception", "Ciencia ficción", 2010));
        peliculas.add(new Pelicula("The Dark Knight", "Acción", 2008));
        peliculas.add(new Pelicula("The Shawshank Redemption", "Drama", 1994));
        peliculas.add(new Pelicula("Forrest Gump", "Drama", 1994));
        peliculas.add(new Pelicula("The Matrix", "Ciencia ficción", 1999));

        // Mostrar todas las películas en el ListView al inicio
        peliculasFiltradas.setAll(peliculas);
        listaPeliculas.setItems(peliculasFiltradas);
    }

    private void configurarFiltros() {
        // Escucha cambios en los filtros y aplica la función filtrarPeliculas()
        tituloFiltroField.textProperty().addListener((observable, oldValue, newValue) -> filtrarPeliculas());
        generoFiltroComboBox.valueProperty().addListener((observable, oldValue, newValue) -> filtrarPeliculas());
        anioSpinner.valueProperty().addListener((observable, oldValue, newValue) -> filtrarPeliculas());
    }

    @FXML
    private void agregarPelicula() {
        String titulo = tituloField.getText().trim();
        String genero = generoComboBox.getValue();  // Obtén el valor seleccionado del ComboBox

        // Obtiene el año del Spinner
        Integer anio = anioSpinner.getValue();

        // Validación de campos
        if (titulo.isEmpty() || genero == null || genero.isEmpty()) {  // Verifica que el ComboBox no esté vacío
            mostrarError("Todos los campos son obligatorios.");
            return;
        }

        // Crea y agrega la película
        Pelicula pelicula = new Pelicula(titulo, genero, anio);
        peliculas.add(pelicula);

        // Muestra el resultado y limpia los campos
        resultadoLabel.setText("Película agregada: " + pelicula);
        resultadoLabel.setStyle("-fx-text-fill: green;");
        limpiarCampos();
        filtrarPeliculas();  // Aplicar los filtros después de agregar
    }

    @FXML
    private void eliminarPelicula() {
        Pelicula seleccionada = listaPeliculas.getSelectionModel().getSelectedItem();

        // Comprueba si se seleccionó una película
        if (seleccionada == null) {
            mostrarError("Seleccione una película para eliminar.");
            return;
        }

        // Elimina la película seleccionada
        peliculas.remove(seleccionada);
        resultadoLabel.setText("Película eliminada: " + seleccionada);
        resultadoLabel.setStyle("-fx-text-fill: green;");
        filtrarPeliculas();  // Aplicar los filtros después de eliminar
    }

    @FXML
    private void filtrarPeliculas() {
        String filtroTitulo = tituloFiltroField.getText().trim().toLowerCase();
        String filtroGenero = generoFiltroComboBox.getValue();
        Integer filtroAnio = anioFiltroSpinner.getValue();

        // Filtrar las películas
        ObservableList<Pelicula> filtradas = FXCollections.observableArrayList();
        for (Pelicula pelicula : peliculas) {
            boolean tituloCoincide = pelicula.getTitulo().toLowerCase().contains(filtroTitulo);
            boolean generoCoincide = (filtroGenero == null || filtroGenero.equals("Todos") || pelicula.getGenero().equals(filtroGenero));
            boolean anioCoincide = (filtroAnio == null || pelicula.getAnio() == filtroAnio);

            if (tituloCoincide && generoCoincide && anioCoincide) {
                filtradas.add(pelicula);
            }
        }

        // Actualizar las películas filtradas en el ListView
        peliculasFiltradas.setAll(filtradas);
    }

    private void limpiarCampos() {
        tituloField.clear();
        generoComboBox.setValue(null);  // Reinicia el ComboBox

        // Resetea el Spinner al valor predeterminado
        if (anioSpinner.getValueFactory() != null) {
            anioSpinner.getValueFactory().setValue(2023);
        }
    }

    private void mostrarError(String mensaje) {
        resultadoLabel.setText("Error: " + mensaje);
        resultadoLabel.setStyle("-fx-text-fill: red;");
    }

    public void asignarPelicula(ActionEvent actionEvent) {
        Pelicula seleccionada = listaPeliculas.getSelectionModel().getSelectedItem();

        // Validar si hay una película seleccionada
        if (seleccionada == null) {
            resultadoLabel.setText("Error: No hay ninguna película seleccionada.");
            return;
        }

        // Validar si se ha seleccionado un período de alquiler
        if (unDiaCheckBox.isSelected() || tresDiasCheckBox.isSelected() || sieteDiasCheckBox.isSelected()) {
            // Lógica para determinar el período de alquiler
            int periodo = 0;
            if (unDiaCheckBox.isSelected()) {
                periodo = 1;
            } else if (tresDiasCheckBox.isSelected()) {
                periodo = 3;
            } else if (sieteDiasCheckBox.isSelected()) {
                periodo = 7;
            }

            // Realizar la asignación (puedes modificar esta parte según tu lógica)
            // Aquí solo mostramos un mensaje de confirmación
            resultadoLabel.setText("Película asignada: " + seleccionada.getTitulo() + " por " + periodo + " día(s).");
        } else {
            resultadoLabel.setText("Error: Seleccione un período de alquiler.");
        }
    }
}
