import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

class MovilidadApp {

    public CompletableFuture<String> calcularRuta() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Calculando ruta...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
                return "Centro -> Norte";
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al calcular ruta");
            }
        });
    }

    public CompletableFuture<Double> estimarTarifa() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Estimando tarifa...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
                return 94.00;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al estimar tarifa");
            }
        });
    }

    public void procesarSolicitud() {
        CompletableFuture<String> rutaFuture = calcularRuta();
        CompletableFuture<Double> tarifaFuture = estimarTarifa();

        rutaFuture.thenCombine(tarifaFuture, (ruta, tarifa) ->
            "Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa
        ).exceptionally(e -> 
            "Error en el procesamiento del viaje: " + e.getMessage()
        ).thenAccept(resultado -> 
            System.out.println("Confirmación para usuario: " + resultado)
        );
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MovilidadApp app = new MovilidadApp();
        app.procesarSolicitud();

        Thread.sleep(4000); // Espera suficiente para que completen las tareas asincrónicas
    }
}
