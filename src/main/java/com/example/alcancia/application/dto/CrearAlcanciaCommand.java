package com.example.alcancia.application.dto;

public class CrearAlcanciaCommand {
    private String nombreAhorrista;
    private double meta;
    public CrearAlcanciaCommand() {}
    public String getNombreAhorrista() { return nombreAhorrista; }
    public void setNombreAhorrista(String n) { this.nombreAhorrista = n; }
    public double getMeta() { return meta; }
    public void setMeta(double m) { this.meta = m; }
}
