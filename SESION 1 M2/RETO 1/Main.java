import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void mostrarOrdenes(List<? extends OrdenProduccion> lista) {
        for (OrdenProduccion orden : lista) {
            orden.mostrarResumen();
        }
        System.out.println();
    }

    public static void procesarPersonalizadas(List<? super OrdenPersonalizada> lista, int costoAdicional) {
        System.out.println("💰 Procesando órdenes personalizadas...");
        for (Object obj : lista) {
            if (obj instanceof OrdenPersonalizada) {
                OrdenPersonalizada op = (OrdenPersonalizada) obj;
                op.aplicarCostoAdicional(costoAdicional);
            }
        }
        System.out.println();
    }

    public static void contarOrdenes(List<OrdenProduccion> todas) {
        int masa = 0, pers = 0, proto = 0;
        for (OrdenProduccion o : todas) {
            if (o instanceof OrdenMasa) masa++;
            else if (o instanceof OrdenPersonalizada) pers++;
            else if (o instanceof OrdenPrototipo) proto++;
        }

        System.out.println(" Resumen total de órdenes:");
        System.out.println(" Producción en masa: " + masa);
        System.out.println(" Personalizadas: " + pers);
        System.out.println(" Prototipos: " + proto);
    }

    public static void main(String[] args) {
        List<OrdenMasa> ordenesMasa = new ArrayList<>();
        ordenesMasa.add(new OrdenMasa("A123", 500));
        ordenesMasa.add(new OrdenMasa("A124", 750));

        List<OrdenPersonalizada> ordenesPersonalizadas = new ArrayList<>();
        ordenesPersonalizadas.add(new OrdenPersonalizada("P456", 100, "ClienteX"));
        ordenesPersonalizadas.add(new OrdenPersonalizada("P789", 150, "ClienteY"));

        List<OrdenPrototipo> ordenesPrototipo = new ArrayList<>();
        ordenesPrototipo.add(new OrdenPrototipo("T789", 10, "Diseño"));
        ordenesPrototipo.add(new OrdenPrototipo("T790", 5, "Pruebas"));

        System.out.println("Órdenes registradas:");
        mostrarOrdenes(ordenesMasa);

        System.out.println("Órdenes registradas:");
        mostrarOrdenes(ordenesPersonalizadas);

        System.out.println("Órdenes registradas:");
        mostrarOrdenes(ordenesPrototipo);

        procesarPersonalizadas(new ArrayList<>(ordenesPersonalizadas), 200);

        List<OrdenProduccion> todas = new ArrayList<>();
        todas.addAll(ordenesMasa);
        todas.addAll(ordenesPersonalizadas);
        todas.addAll(ordenesPrototipo);

        contarOrdenes(todas);
    }
}
