package com.example.videoclub.utils;

public enum Constantes {
    PAGINA_ADMIN("/com/example/videoclub/admin.fxml"),
    TITULO_PAGINA_INICIAL("Página Inicial");

    private final String descripcion;

    // Constructor para asociar una descripción a cada constante
    Constantes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
