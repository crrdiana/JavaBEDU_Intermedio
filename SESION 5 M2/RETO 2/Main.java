import java.util.Random;
import java.util.concurrent.*;

public class Main {

    private static final ScheduledExecutorService generador = Executors.newScheduledThreadPool(3);
    private static final ExecutorService procesador = Executors.newSingleThreadExecutor();
    private static final BlockingQueue<String> eventosCriticos = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Monitoreo UCI en tiempo real iniciado...");

        for (int i = 1; i <= 3; i++) {
            int pacienteId = i;
            generador.scheduleAtFixedRate(() -> {
                String evento = generarEvento(pacienteId);
                if (evento != null) {
                    eventosCriticos.offer(evento);
                }
            }, 0, 300, TimeUnit.MILLISECONDS);
        }

        procesador.submit(() -> {
            try {
                while (true) {
                    String alerta = eventosCriticos.take();
                    System.out.println(alerta);
                    Thread.sleep(1000); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread.sleep(15000);
        generador.shutdownNow();
        procesador.shutdownNow();
        System.out.println("Monitoreo finalizado.");
    }

    private static String generarEvento(int paciente) {
        Random rand = new Random();
        int fc = 40 + rand.nextInt(100);       
        int paSist = 80 + rand.nextInt(80);    
        int paDiast = 50 + rand.nextInt(50);   
        int spo2 = 85 + rand.nextInt(15);      

        if (fc < 50 || fc > 120) {
            return "[ALERTA] Paciente " + paciente + " - Frecuencia cardíaca fuera de rango: " + fc + " bpm";
        }

        if (paSist < 90 || paDiast < 60 || paSist > 140 || paDiast > 90) {
            return "[ALERTA] Paciente " + paciente + " - Presión arterial crítica: " + paSist + "/" + paDiast + " mmHg";
        }

        if (spo2 < 90) {
            return "[ALERTA] Paciente " + paciente + " - Oxígeno bajo: " + spo2 + "%";
        }

        return null;
    }
}
