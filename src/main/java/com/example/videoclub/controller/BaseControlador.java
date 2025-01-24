package com.example.videoclub.controller;

import javafx.scene.control.Label;

public abstract class BaseControlador {
    protected void mostrarError(Label resultadoLabel, String mensaje) {
        resultadoLabel.setText("Error: " + mensaje);
        resultadoLabel.setStyle("-fx-text-fill: red;");
    }

    protected void mostrarResultado(Label resultadoLabel, String mensaje) {
        resultadoLabel.setText(mensaje);
        resultadoLabel.setStyle("-fx-text-fill: green;");
    }
}
