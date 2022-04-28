package com.example.midispositivo.entidades;

public class Usuario {

    private String usuario;
    private int tUsuario;
    private int idUsuario;

    public Usuario() {
    }

    public Usuario(int idUsuario, int tUsuario, String usuario){
        this.idUsuario = idUsuario;
        this.tUsuario = tUsuario;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getTUsuario() {
        return tUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public void settUsuario(int tUsuario) { this.tUsuario = tUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}
