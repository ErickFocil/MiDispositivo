package com.example.midispositivo.entidades;

public class Solicitud {

    private int id;
    private int idUsuario;
    private String nombre;
    private String equipo;
    private String reporte;
    private String analisis;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEquipo() { return equipo; }

    public void setEquipo(String equipo) { this.equipo = equipo; }

    public String getReporte() { return reporte; }

    public void setReporte(String reporte) { this.reporte = reporte; }

    public String getAnalisis() { return analisis; }

    public void setAnalisis(String analisis) { this.analisis = analisis; }
}
