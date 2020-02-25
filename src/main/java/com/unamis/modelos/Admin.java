package com.unamis.modelos;

/**
 *
 * @author Marvin
 */
public class Admin {
    
    private String nombre, contrasena;
    private int tipoAdmin;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTipoAdmin() {
        return tipoAdmin;
    }

    public void setTipoAdmin(int tipoAdmin) {
        this.tipoAdmin = tipoAdmin;
    }
    
}
