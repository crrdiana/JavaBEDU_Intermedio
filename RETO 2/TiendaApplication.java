package com.ejemplo.tienda;

import com.ejemplo.tienda.modelo.Marca;
import com.ejemplo.tienda.modelo.Producto;
import com.ejemplo.tienda.repositorio.MarcaRepository;
import com.ejemplo.tienda.repositorio.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TiendaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TiendaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(MarcaRepository marcaRepo, ProductoRepository productoRepo) {
        return args -> {
            Marca apple = marcaRepo.save(new Marca("Apple"));
            Marca samsung = marcaRepo.save(new Marca("Samsung"));

            productoRepo.save(new Producto("iPhone 15", apple));
            productoRepo.save(new Producto("iPad Pro", apple));
            productoRepo.save(new Producto("Galaxy S23", samsung));
            productoRepo.save(new Producto("Smart TV", samsung));

            System.out.println("📚 Productos por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println("🏷️ " + marca.getNombre() + ":");
                productoRepo.findAll().stream()
                    .filter(p -> p.getMarca().getId().equals(marca.getId()))
                    .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}
