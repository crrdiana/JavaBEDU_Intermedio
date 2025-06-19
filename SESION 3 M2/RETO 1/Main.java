import java.util.*;
import java.util.stream.*;

class Pedido {
    private String cliente;
    private String tipoEntrega;
    private String telefono;

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("Diana", "domicilio", "55-1234-5678"),
            new Pedido("Karyme", "local", "55-8888-1234"),
            new Pedido("Omar", "domicilio", null),
            new Pedido("Carolina", "domicilio", "55-5678-2222"),
            new Pedido("Luisa", "domicilio", null)
        );

        pedidos.stream()
            .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio"))
            .map(Pedido::getTelefono)
            .flatMap(Optional::stream)
            .map(t -> "Confirmación enviada al número: " + t)
            .forEach(System.out::println);
    }

}
