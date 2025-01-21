package com.example.videoclub.utils;

public enum Constantes {
    PAGINA_INICIAL("first.fxml"),
    PAGINA_SEGUNDA_PANTALLA("second.fxml"),
    TITULO_PAGINA_INICIAL("Página Inicial"),
    TITULO_SEGUNDA_PANTALLA("Página Segunda");

    private final String descripcion;

    // Constructor para asociar una descripción a cada constante
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
