import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        // 1. Crear 3 conciertos
        Concierto c1 = new Concierto("Perrate de Utrera", "Utrera", 40.0, 80, new ArrayList<>(), true);
        Concierto c2 = new Concierto("David Palomar", "Cádiz", 35.0, 120, new ArrayList<>(), true);
        Concierto c3 = new Concierto("Estrella Morente", "Granada", 55.0, 150, new ArrayList<>(), true);

        // 2. Crear 3 usuarios
        Usuario u1 = new Usuario("Rocío", 29, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario u2 = new Usuario("Joaquín", 55, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario u3 = new Usuario("Cristina", 24, new ArrayList<>(), new HashSet<>(), new HashMap<>());

        // 3. Cada usuario compra entradas para 2 conciertos
        u1.comprarEntrada(c1, Entrada.TipoEntrada.VIP);
        u1.comprarEntrada(c3, Entrada.TipoEntrada.PISTA);

        u2.comprarEntrada(c2, Entrada.TipoEntrada.GRADA);
        u2.comprarEntrada(c3, Entrada.TipoEntrada.VIP);

        u3.comprarEntrada(c1, Entrada.TipoEntrada.PISTA);
        u3.comprarEntrada(c2, Entrada.TipoEntrada.GRADA);
    }
}