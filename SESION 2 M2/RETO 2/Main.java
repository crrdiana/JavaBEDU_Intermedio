import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class RecursoMedico {
    private final String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        System.out.println(profesional + " está intentando acceder a " + nombre);
        lock.lock();
        try {
            System.out.println(profesional + " ha ingresado a " + nombre);
            Thread.sleep(1000); // Simula el uso del recurso
            System.out.println(profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.out.println("Interrupción durante el uso del recurso por " + profesional);
        } finally {
            lock.unlock();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando acceso a la Sala de cirugía...");

        RecursoMedico sala = new RecursoMedico("Sala de cirugía");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Runnable p1 = () -> sala.usar("Dra. Rodriguez");
        Runnable p2 = () -> sala.usar("Dr. Ruiz");
        Runnable p3 = () -> sala.usar("Dra. Revilla");
        Runnable p4 = () -> sala.usar("Dr. Dominguez");
        Runnable p5 = () -> sala.usar("Dra. Sanchez");
        Runnable p6 = () -> sala.usar("Enfermera Garcia");

        executor.execute(p1);
        executor.execute(p2);
        executor.execute(p3);
        executor.execute(p4);
        executor.execute(p5);
        executor.execute(p6);

        executor.shutdown();
    }
}
