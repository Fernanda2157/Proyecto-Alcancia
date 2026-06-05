package com.example.alcancia.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "alcancia")
public class AlcanciaJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String nombreAhorrista;
    @Column(nullable = false) private Double saldo;
    @Column(nullable = false) private Double meta;
    @Column(nullable = false) private String estado;

    public AlcanciaJpaEntity() {}
    public AlcanciaJpaEntity(String n, Double s, Double m, String e) {
        nombreAhorrista=n; saldo=s; meta=m; estado=e;
    }

    public Long getId() { return id; } public void setId(Long id) { this.id=id; }
    public String getNombreAhorrista() { return nombreAhorrista; } public void setNombreAhorrista(String n) { nombreAhorrista=n; }
    public Double getSaldo() { return saldo; } public void setSaldo(Double s) { saldo=s; }
    public Double getMeta() { return meta; } public void setMeta(Double m) { meta=m; }
    public String getEstado() { return estado; } public void setEstado(String e) { estado=e; }
}
