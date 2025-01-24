package com.example.videoclub.utils;

public enum Constantes {
    // Constantes para las rutas de las páginas
    GESTIONAR_PELICULAS("/com/example/videoclub/gestionar-peliculas.fxml"),
    FILTRAR_PELICULAS("/com/example/videoclub/filtrar-peliculas.fxml"),
    GESTIONAR_ALQUILERES("/com/example/videoclub/gestionar-alquileres.fxml"),

    // Constantes para los títulos
    TITULO_GESTIONAR_PELICULAS("Gestionar Películas"),
    TITULO_FILTRAR_PELICULAS("Filtrar Películas"),
    TITULO_GESTIONAR_ALQUILERES("Gestionar Alquileres");

    private final String descripcion;

    // Constructor para asociar una descripción a cada constante
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
