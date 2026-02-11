import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // 1. Crear 3 conciertos
        Concierto c1 = new Concierto("Perrate de Utrera", "Utrera", 40.0, 80, new ArrayList<>(), true);
        Concierto c2 = new Concierto("David Palomar", "Cádiz", 35.0, 120, new ArrayList<>(), true);
        Concierto c3 = new Concierto("Estrella Morente", "Granada", 55.0, 150, new ArrayList<>(), true);

    }
}