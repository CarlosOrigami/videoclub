package com.example.videoclub.utils;

public enum Constantes {
    // Constantes para las rutas de las páginas
    ADMIN("admin.fxml"),


    // Constantes para los títulos
    TITULO_ADMIN("Gestionar Películas");


    private final String descripcion;

    // Constructor para asociar una descripción a cada constante
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
