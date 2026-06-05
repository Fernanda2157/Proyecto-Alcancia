package com.example.alcancia.entity;

import jakarta.persistence.*;

// CAPA: Entidad — Modelo Anémico (solo datos, sin lógica)
@Entity
@Table(name = "alcancia")
public class AlcanciaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreAhorrista;

    @Column(nullable = false)
    private Double saldo;

    @Column(nullable = false)
    private Double meta;

    // SETTER PELIGROSO: cualquiera puede cambiar estado sin validación
    @Column(nullable = false)
    private String estado;

    public AlcanciaEntity() {}

    public AlcanciaEntity(String nombreAhorrista, Double meta) {
        this.nombreAhorrista = nombreAhorrista;
        this.saldo = 0.0;
        this.meta = meta;
        this.estado = "ACTIVA";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreAhorrista() { return nombreAhorrista; }
    public void setNombreAhorrista(String n) { this.nombreAhorrista = n; }
    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }   // sin validación
    public Double getMeta() { return meta; }
    public void setMeta(Double meta) { this.meta = meta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; } // sin validación

    public double getPorcentaje() {
        return (meta > 0) ? Math.min((saldo / meta) * 100, 100) : 0;
    }
    public double getFaltante() {
        double f = meta - saldo; return f < 0 ? 0 : f;
    }
    public boolean isCompletada() { return "COMPLETADA".equals(estado); }
}
