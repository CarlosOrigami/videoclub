package com.example.videoclub.controller;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nombreUsuario;
    private String contrasena;
    private String rol;

    // Constructor
    public Usuario(String nombreUsuario, String contrasena, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }

    // Método para comprobar si el rol es admin
    public boolean esAdmin() {
        return "admin".equalsIgnoreCase(rol);
    }

    // Clase interna para gestionar el repositorio de usuarios
    public static class UsuarioRepository {

        // Lista de usuarios predefinidos
        private static final List<Usuario> usuarios = new ArrayList<>();

        static {
            // Añadimos 3 usuarios: 1 admin y 2 users
            usuarios.add(new Usuario("a", "1", "admin"));
            usuarios.add(new Usuario("user1", "abcd", "user"));
            usuarios.add(new Usuario("user2", "efgh", "user"));
        }

        // Método para validar usuario y contraseña
        public static Usuario validarUsuario(String nombreUsuario, String contrasena) {
            for (Usuario usuario : usuarios) {
                if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                    return usuario;
                }
            }
            return null; // Retorna null si no se encuentra el usuario
        }
    }
}
