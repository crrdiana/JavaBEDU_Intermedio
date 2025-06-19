import java.util.concurrent.*;

class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Navegación: trayectoria corregida con éxito.";
    }
}

class SistemaSoporteVital implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(800);
        return "Soporte vital: presión y oxígeno dentro de parámetros normales.";
    }
}

class SistemaControlTermico implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1200);
        return "Control térmico: temperatura estable (22°C).";
    }
}

class SistemaComunicaciones implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(600);
        return "Comunicaciones: enlace con estación terrestre establecido.";
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> vida = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());
        Future<String> com = executor.submit(new SistemaComunicaciones());

        System.out.println(com.get());
        System.out.println(vida.get());
        System.out.println(termico.get());
        System.out.println(nav.get());

        executor.shutdown();

        System.out.println("Todos los sistemas reportan estado operativo.");
    }
}
