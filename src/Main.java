import exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        Concierto c1 = new Concierto("Perrate de Utrera", "Utrera", 40.0, 80, new ArrayList<>(), true);
        Concierto c2 = new Concierto("David Palomar", "Cádiz", 35.0, 120, new ArrayList<>(), true);
        Concierto c3 = new Concierto("Estrella Morente", "Granada", 55.0, 150, new ArrayList<>(), true);

        Usuario u1 = new Usuario("Rocío", 29, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario u2 = new Usuario("Joaquín", 55, new ArrayList<>(), new HashSet<>(), new HashMap<>());
        Usuario u3 = new Usuario("Cristina", 24, new ArrayList<>(), new HashSet<>(), new HashMap<>());

        // Captura de excepciones en el flujo normal de compra
        System.out.println("--- PROCESANDO COMPRAS INICIALES ---");
        try {
            u1.comprarEntrada(c1, Entrada.TipoEntrada.VIP);
            u1.comprarEntrada(c3, Entrada.TipoEntrada.PISTA);
            u2.comprarEntrada(c2, Entrada.TipoEntrada.GRADA);
            u2.comprarEntrada(c3, Entrada.TipoEntrada.VIP);
            u3.comprarEntrada(c1, Entrada.TipoEntrada.PISTA);
            u3.comprarEntrada(c2, Entrada.TipoEntrada.GRADA);
        } catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) {
            System.out.println("Error en compras iniciales: " + e.getMessage());
        }

        // SECCIÓN DE PRUEBAS: Provocar el lanzamiento de las excepciones y capturarlas con mensajes amigables
        System.out.println("\n--- PROVOCANDO LANZAMIENTO DE EXCEPCIONES ---");

        // ValoracionIncorrectaException
        try {
            System.out.print("Probando valoración incorrecta (nota 15): ");
            u1.valorar(c1, 15);
        } catch (ValoracionIncorrectaException | ConciertoNoAsistidoException e) {
            System.out.println("CAPTURADA -> " + e.getMessage());
        }

        // ConciertoYaAsistidoException
        try {
            System.out.print("Probando compra duplicada: ");
            u1.comprarEntrada(c1, Entrada.TipoEntrada.PISTA);
        } catch (ConciertoYaAsistidoException | ConciertoInactivoException | AforoCompletoException e) {
            System.out.println("CAPTURADA -> " + e.getMessage());
        }

        // ConciertoInactivoException
        try {
            System.out.print("Probando concierto inactivo: ");
            c2.setActivo(false);
            u1.comprarEntrada(c2, Entrada.TipoEntrada.GRADA);
        } catch (ConciertoInactivoException | ConciertoYaAsistidoException | AforoCompletoException e) {
            System.out.println("CAPTURADA -> " + e.getMessage());
        }

        // CeroEntradasException
        try {
            System.out.print("Probando cálculo precio medio sin ventas: ");
            Concierto cVacio = new Concierto("Nuevo Artista", "Sevilla", 20.0, 10, new ArrayList<>(), true);
            cVacio.calcularPrecioMedio();
        } catch (CeroEntradasException e) {
            System.out.println("CAPTURADA -> " + e.getMessage());
        }

        System.out.println("\n--- REGISTRANDO VALORACIONES REALES ---");
        try {
            u1.valorar(c3, 10);
            u2.valorar(c2, 9);
            u3.valorar(c1, 9);
        } catch (ConciertoNoAsistidoException | ValoracionIncorrectaException e) {
            System.out.println("[!] Error capturado: " + e.getMessage());
        }

        int totalEntradas = 0;
        int totalPista = 0, totalGrada = 0, totalVIP = 0;
        double recaudacionTotal = 0;

        ArrayList<Concierto> cartelera = new ArrayList<>();
        cartelera.add(c1);
        cartelera.add(c2);
        cartelera.add(c3);

        Concierto masVendido = c1;
        Concierto menosVendido = c1;

        System.out.println("\n========== ESTADÍSTICAS DE CONCIERTOS ==========");
        for (Concierto c : cartelera) {
            int cantidadVendida = c.getEntradasVendidas().size();
            totalEntradas += cantidadVendida;
            recaudacionTotal += c.calcularRecaudacion();

            for (Entrada e : c.getEntradasVendidas()) {
                if (e.getTipo() == Entrada.TipoEntrada.PISTA) totalPista++;
                else if (e.getTipo() == Entrada.TipoEntrada.GRADA) totalGrada++;
                else if (e.getTipo() == Entrada.TipoEntrada.VIP) totalVIP++;
            }

            if (cantidadVendida > masVendido.getEntradasVendidas().size()) masVendido = c;
            if (cantidadVendida < menosVendido.getEntradasVendidas().size()) menosVendido = c;

            //CeroEntradasException
            try {
                double precioM = c.calcularPrecioMedio();
                System.out.println(c.getArtista() + " - Precio medio: " + String.format("%.2f", precioM) + "€");
            } catch (CeroEntradasException e) {
                System.out.println(c.getArtista() + " - Precio medio: N/A (" + e.getMessage() + ")");
            }
        }

        System.out.println("------------------------------------------------");
        System.out.println("Número de entradas vendidas en total: " + totalEntradas);
        System.out.println("Entradas por tipo -> Pista: " + totalPista + " | Grada: " + totalGrada + " | VIP: " + totalVIP);
        System.out.println("Recaudación total: " + String.format("%.2f", recaudacionTotal) + "€");

        if (totalEntradas > 0) {
            double precioMedioGlobal = recaudacionTotal / totalEntradas;
            System.out.println("Precio medio de las entradas: " + String.format("%.2f", precioMedioGlobal) + "€");
        }

        System.out.println("Concierto con más entradas vendidas: " + masVendido.getArtista());
        System.out.println("Concierto con menos entradas vendidas: " + menosVendido.getArtista());
        System.out.println("================================================");

        System.out.println("\n--- ESTADO FINAL DE LOS USUARIOS ---");
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(u3);
    }
}