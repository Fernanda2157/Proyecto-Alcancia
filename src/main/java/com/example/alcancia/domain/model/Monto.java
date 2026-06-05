package com.example.alcancia.domain.model;

import com.example.alcancia.domain.exception.AlcanciaException;


public final class Monto {
    private final double valor;

    public Monto(double valor) {
        if (valor <= 0)
            throw new AlcanciaException("El monto debe ser mayor a cero. Recibido: $" + valor);
        this.valor = valor;
    }

    public double getValor() { return valor; }
    public boolean esMayorOIgualA(Monto otro) { return this.valor >= otro.valor; }

    @Override public String toString() { return "$" + valor; }
}
