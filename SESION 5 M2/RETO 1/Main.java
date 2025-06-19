import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    private static final AtomicInteger alertasGlobales = new AtomicInteger(0);
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando monitoreo de sistemas críticos en Meridian Prime...");

        programar("Tráfico", 500, () -> {
            int nivel = ThreadLocalRandom.current().nextInt(101);
            if (nivel > 70) emitirAlerta("Congestión al " + nivel + "% en avenidas", nivel);
        });

        programar("Contaminación", 600, () -> {
            int pm = ThreadLocalRandom.current().nextInt(101);
            if (pm > 50) emitirAlerta("Contaminación PM2.5 a " + pm + " µg/m3", pm);
        });

        programar("Accidentes", 800, () -> {
            String[] prioridades = {"Baja", "Media", "Alta"};
            String p = prioridades[ThreadLocalRandom.current().nextInt(3)];
            if ("Alta".equals(p)) emitirAlerta("Accidente vial con prioridad Alta", 0);
        });

        programar("Trenes", 700, () -> {
            int retraso = ThreadLocalRandom.current().nextInt(11);
            if (retraso > 5) emitirAlerta("Retraso en tren maglev de " + retraso + " minutos", retraso);
        });

        programarSemaforo(400);

        Thread.sleep(10000);
        scheduler.shutdownNow();
        System.out.println("Monitoreo detenido.");
    }

    private static void programar(String nombre, int periodoMs, Runnable tarea) {
        scheduler.scheduleAtFixedRate(tarea, 0, periodoMs, TimeUnit.MILLISECONDS);
    }

    private static void emitirAlerta(String msg, int valor) {
        System.out.println("ALERTA [" + Thread.currentThread().getName() +
            "]: " + msg);
        if (alertasGlobales.incrementAndGet() == 3) {
            System.out.println(">>> Alerta GLOBAL: tres o más eventos críticos detectados simultáneamente");
        }
    }

    private static void programarSemaforo(int periodoMs) {
        final Deque<String> ultimos = new ArrayDeque<>();
        scheduler.scheduleAtFixedRate(() -> {
            String[] estados = {"Verde", "Amarillo", "Rojo"};
            String estado = estados[ThreadLocalRandom.current().nextInt(3)];
            ultimos.addLast(estado);
            if (ultimos.size() > 3) ultimos.removeFirst();
            if (ultimos.size() == 3 && ultimos.stream().allMatch(s -> s.equals("Rojo"))) {
                emitirAlerta("Semáforo en rojo detectado 3 veces seguidas", 0);
            }
        }, 0, periodoMs, TimeUnit.MILLISECONDS);
    }
}
