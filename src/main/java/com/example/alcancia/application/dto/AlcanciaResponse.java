package com.universidad.alcancia.application.dto;

import com.universidad.alcancia.domain.model.Alcancia;

public class AlcanciaResponse {
    private Long id;
    private String nombreAhorrista, estado, mensaje;
    private double saldo, meta, porcentaje, faltante;

    public AlcanciaResponse(Alcancia a, String msg) {
        id = a.getId(); nombreAhorrista = a.getNombreAhorrista();
        saldo = a.getSaldo(); meta = a.getMeta();
        estado = a.getEstado().name(); porcentaje = a.getPorcentaje();
        faltante = a.getFaltante(); mensaje = msg;
    }

    public Long getId()                  { return id; }
    public String getNombreAhorrista()   { return nombreAhorrista; }
    public double getSaldo()             { return saldo; }
    public double getMeta()              { return meta; }
    public String getEstado()            { return estado; }
    public double getPorcentaje()        { return porcentaje; }
    public double getFaltante()          { return faltante; }
    public String getMensaje()           { return mensaje; }
    public boolean isCompletada()        { return "COMPLETADA".equals(estado); }
}
