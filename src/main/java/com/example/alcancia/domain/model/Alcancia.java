package com.example.alcancia.domain.model;

import com.example.alcancia.domain.exception.AlcanciaException;

public class Alcancia {

    private Long id;
    private final String nombreAhorrista;
    private double saldo;
    private final double meta;
    private EstadoAlcancia estado;

    public Alcancia(String nombreAhorrista, double meta) {
        if (nombreAhorrista == null || nombreAhorrista.isBlank())
            throw new AlcanciaException("El nombre no puede estar vacío");
        new Monto(meta); // valida que meta > 0
        this.nombreAhorrista = nombreAhorrista;
        this.meta = meta;
        this.saldo = 0.0;
        this.estado = EstadoAlcancia.ACTIVA;
    }

    public Alcancia(Long id, String nombreAhorrista, double saldo, double meta, EstadoAlcancia estado) {
        this.id = id; 
        this.nombreAhorrista = nombreAhorrista;
        this.saldo = saldo; 
        this.meta = meta; 
        this.estado = estado;
    }

    public void depositar(Monto monto) {
    // 1. Regla: no depositar en COMPLETADA
    if (this.estado == EstadoAlcancia.COMPLETADA)
        throw new AlcanciaException("La alcancía de '" + nombreAhorrista + "' ya está COMPLETADA.");

    // 2. NUEVA LÓGICA DE BLOQUEO:
    double cantidadADepositar = monto.getValor();
    double faltante = getFaltante();

    // Si el depósito es mayor a lo que falta, lanzamos la excepción y NO hacemos nada más
    if (cantidadADepositar > faltante) {
        throw new AlcanciaException("Depósito no permitido: excede la meta. Solo faltan: $" + faltante);
    }

    // 3. Si llega aquí, es porque el depósito es válido
    this.saldo += cantidadADepositar;

    // 4. Si llegamos exactamente a la meta, cambiamos el estado
    if (this.saldo >= this.meta) {
        this.estado = EstadoAlcancia.COMPLETADA;
    }
}
    public Long getId() { return id; }
    public void setId(Long id) { if (this.id != null) throw new AlcanciaException("ID ya asignado"); this.id = id; }
    public String getNombreAhorrista() { return nombreAhorrista; }
    public double getSaldo() { return saldo; }
    public double getMeta() { return meta; }
    public EstadoAlcancia getEstado() { return estado; }
    public boolean estaCompletada() { return estado == EstadoAlcancia.COMPLETADA; }
    public double getPorcentaje() { return meta > 0 ? Math.min((saldo / meta) * 100, 100) : 0; }
    public double getFaltante() { double f = meta - saldo; return f < 0 ? 0 : f; }
}