import java.util.concurrent.*;
import java.util.concurrent.ThreadLocalRandom;

class AeropuertoControl {

    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2500);
                boolean disponible = Math.random() < 0.8;
                System.out.println("Pista operativa: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al verificar pista");
            }
        });
    }

    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2300);
                boolean favorable = Math.random() < 0.85;
                System.out.println("Condiciones climáticas adecuadas: " + favorable);
                return favorable;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al verificar clima");
            }
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                boolean despejado = Math.random() < 0.9;
                System.out.println("Tráfico aéreo controlado: " + despejado);
                return despejado;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al verificar tráfico aéreo");
            }
        });
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2100);
                boolean disponible = Math.random() < 0.95;
                System.out.println("Personal de tierra disponible: " + disponible);
                return disponible;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Error al verificar personal en tierra");
            }
        });
    }

    public void procesarAterrizaje() {
        System.out.println("Iniciando verificación de condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = verificarPista();
        CompletableFuture<Boolean> clima = verificarClima();
        CompletableFuture<Boolean> trafico = verificarTraficoAereo();
        CompletableFuture<Boolean> personal = verificarPersonalTierra();

        CompletableFuture<Void> allChecks = CompletableFuture.allOf(pista, clima, trafico, personal);

        allChecks.thenRun(() -> {
            try {
                boolean condicionesOk = pista.get() && clima.get() && trafico.get() && personal.get();
                System.out.println();
                if (condicionesOk) {
                    System.out.println("Aterrizaje aprobado: todas las condiciones están dentro de los parámetros.");
                } else {
                    System.out.println("Aterrizaje rechazado: alguna condición no es favorable.");
                }
            } catch (Exception e) {
                System.out.println("Error en la verificación de condiciones: " + e.getMessage());
            }
        }).exceptionally(e -> {
            System.out.println("Error crítico durante el proceso de aterrizaje: " + e.getMessage());
            return null;
        });
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AeropuertoControl control = new AeropuertoControl();
        control.procesarAterrizaje();

        Thread.sleep(5000); 
    }
}
