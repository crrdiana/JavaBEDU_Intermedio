package com.ejemplo.inventario;

import com.ejemplo.inventario.model.Producto;
import com.ejemplo.inventario.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ProductoRepository productoRepository) {
        return args -> {
            productoRepository.saveAll(List.of(
                new Producto("Laptop Lenovo", "Portátil con 16GB RAM", 12500.0),
                new Producto("Mouse Logitech", "Mouse inalámbrico", 350.0),
                new Producto("Teclado Mecánico", "Retroiluminado RGB", 950.0),
                new Producto("Monitor", "Pantalla 24 pulgadas", 3200.0)
            ));

            System.out.println("=== Productos con precio superior a 500 ===");
            productoRepository.findByPrecioGreaterThan(500).forEach(System.out::println);

            System.out.println("\n=== Productos que contienen 'lap' en el nombre ===");
            productoRepository.findByNombreContainingIgnoreCase("lap").forEach(System.out::println);

            System.out.println("\n=== Productos con precio entre 400 y 1000 ===");
            productoRepository.findByPrecioBetween(400, 1000).forEach(System.out::println);

            System.out.println("\n=== Productos cuyo nombre comienza con 'm' o 'M' ===");
            productoRepository.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
        };
    }
}
