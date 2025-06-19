import java.util.*;
import java.util.function.Predicate;

abstract class MaterialCurso {
    protected String titulo;
    protected String autor;

    public MaterialCurso(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public abstract void mostrarDetalle();
}

class Video extends MaterialCurso {
    private int duracion;

    public Video(String titulo, String autor, int duracion) {
        super(titulo, autor);
        this.duracion = duracion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void mostrarDetalle() {
        System.out.println("Video: " + titulo + " - Autor: " + autor + " - Duración: " + duracion + " min");
    }
}

class Articulo extends MaterialCurso {
    private int palabras;

    public Articulo(String titulo, String autor, int palabras) {
        super(titulo, autor);
        this.palabras = palabras;
    }

    public void mostrarDetalle() {
        System.out.println("Artículo: " + titulo + " - Autor: " + autor + " - Palabras: " + palabras);
    }
}

class Ejercicio extends MaterialCurso {
    private boolean revisado;

    public Ejercicio(String titulo, String autor) {
        super(titulo, autor);
        this.revisado = false;
    }

    public void marcarRevisado() {
        this.revisado = true;
    }

    public boolean isRevisado() {
        return revisado;
    }

    public void mostrarDetalle() {
        System.out.println("Ejercicio: " + titulo + " - Autor: " + autor + " - Revisado: " + revisado);
    }
}

public class Main {

    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        for (MaterialCurso m : lista) {
            m.mostrarDetalle();
        }
        System.out.println();
    }

    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video v : lista) {
            total += v.getDuracion();
        }
        System.out.println("Duración total de videos: " + total + " minutos\n");
    }

    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio e) {
                e.marcarRevisado();
                System.out.println("Ejercicio '" + e.titulo + "' marcado como revisado.");
            }
        }
        System.out.println();
    }

    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, Predicate<MaterialCurso> filtro) {
        for (MaterialCurso m : lista) {
            if (filtro.test(m)) {
                m.mostrarDetalle();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();
        materiales.add(new Video("Introducción a Java", "Diana", 15));
        materiales.add(new Video("POO en Java", "Carlos", 20));
        materiales.add(new Articulo("Historia de Java", "Karyme", 1200));
        materiales.add(new Articulo("Tipos de datos", "Mauricio", 800));
        materiales.add(new Ejercicio("Variables y tipos", "Oscar"));
        materiales.add(new Ejercicio("Condicionales", "Eduardo"));

        System.out.println("Materiales para el curso:");
        mostrarMateriales(materiales);

        List<Video> videos = new ArrayList<>();
        for (MaterialCurso m : materiales) {
            if (m instanceof Video v) videos.add(v);
        }
        contarDuracionVideos(videos);

        marcarEjerciciosRevisados(materiales);

        System.out.println("Filtrando materiales por autor:");
        filtrarPorAutor(materiales, m -> m.autor.equals("Dianna"));
    }
}
