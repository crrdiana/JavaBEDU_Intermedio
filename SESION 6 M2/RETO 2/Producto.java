package com.ejemplo.tienda.modelo;

import jakarta.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Producto() {}

    public Producto(String nombre, Marca marca) {
        this.nombre = nombre;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Marca getMarca() {
        return marca;
    }
}
 Producto {
    
}
