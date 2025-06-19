import java.util.*;
import java.util.stream.*;

class Encuesta {
    private String paciente;
    private String comentario;
    private int calificacion;

    public Encuesta(String paciente, String comentario, int calificacion) {
        this.paciente = paciente;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }
}

class Sucursal {
    private String nombre;
    private List<Encuesta> encuestas;

    public Sucursal(String nombre, List<Encuesta> encuestas) {
        this.nombre = nombre;
        this.encuestas = encuestas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Sucursal> sucursales = Arrays.asList(
            new Sucursal("Centro", Arrays.asList(
                new Encuesta("Carlos", "La espera fue muy larga", 2),
                new Encuesta("Diana", null, 4),
                new Encuesta("Lucía", "El trato no fue cordial", 3)
            )),
            new Sucursal("Norte", Arrays.asList(
                new Encuesta("Mauricio", "Buena atención, pero falta limpieza", 3),
                new Encuesta("Oscar", null, 5),
                new Encuesta("Luis", null, 2)
            )),
            new Sucursal("Sur", Arrays.asList(
                new Encuesta("Eduardo", "No se respetó la cita", 1),
                new Encuesta("Diego", null, 3),
                new Encuesta("Paula", "Poca disponibilidad de personal", 3)
            ))
        );

        sucursales.stream()
            .flatMap(sucursal -> 
                sucursal.getEncuestas().stream()
                    .filter(encuesta -> encuesta.getCalificacion() <= 3)
                    .map(encuesta -> Map.entry(sucursal.getNombre(), encuesta.getComentario()))
            )
            .filter(entry -> entry.getValue().isPresent())
            .map(entry -> "Sucursal " + entry.getKey() + ": Seguimiento necesario por comentario: \"" + entry.getValue().get() + "\"")
            .forEach(System.out::println);
    }
}
