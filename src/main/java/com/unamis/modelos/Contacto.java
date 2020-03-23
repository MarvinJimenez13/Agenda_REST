package com.unamis.modelos;

import java.sql.Date;

/**
 *
 * @author Marvin
 */
public class Contacto {
    
    private int idContacto, idAdmin;
    private String nombre, apellidos, numeroCelular, lugarComun, avenida, colonia, estado, pais, comentario, adminRegistro;
    private java.sql.Date fechaRegistro;

    public String getAdminRegistro() {
        return adminRegistro;
    }

    public void setAdminRegistro(String adminRegistro) {
        this.adminRegistro = adminRegistro;
    }

    public int getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(int idContacto) {
        this.idContacto = idContacto;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCeular) {
        this.numeroCelular = numeroCeular;
    }

    public String getLugarComun() {
        return lugarComun;
    }

    public void setLugarComun(String lugarComun) {
        this.lugarComun = lugarComun;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
