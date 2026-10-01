package com.sistemacitas;

public class Administrador {

    private String idAdministrador;
    private String contrasena;

    public Administrador(String idAdministrador, String contrasena) {
        this.idAdministrador = idAdministrador;
        this.contrasena = contrasena;
    }

    public String getIdAdministrador() {
        return idAdministrador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean validarAcceso(String id, String contrasena) {
        return this.idAdministrador.equals(id)
                && this.contrasena.equals(contrasena);
    }

    @Override
    public String toString() {
        return idAdministrador + "," + contrasena;
    }
}
