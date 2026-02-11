import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Usuario {

    //Atributos

    private String nombre;
    private int edad;
    private ArrayList<Entrada> entradasCompradas;
    private HashSet<Concierto> conciertosAsistidos;
    private HashMap<Concierto, Integer> valoraciones;

    //Constructor vacío

    public Usuario() {
    }

    //Constructor completo

    public Usuario(String nombre, int edad, ArrayList<Entrada> entradasCompradas, HashSet<Concierto> conciertosAsistidos, HashMap<Concierto, Integer> valoraciones) {
        this.nombre = nombre;
        this.edad = edad;
        this.entradasCompradas = entradasCompradas;
        this.conciertosAsistidos = conciertosAsistidos;
        this.valoraciones = valoraciones;
    }

    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Entrada> getEntradasCompradas() {
        return entradasCompradas;
    }

    public void setEntradasCompradas(ArrayList<Entrada> entradasCompradas) {
        this.entradasCompradas = entradasCompradas;
    }

    public HashSet<Concierto> getConciertosAsistidos() {
        return conciertosAsistidos;
    }

    public void setConciertosAsistidos(HashSet<Concierto> conciertosAsistidos) {
        this.conciertosAsistidos = conciertosAsistidos;
    }

    public HashMap<Concierto, Integer> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(HashMap<Concierto, Integer> valoraciones) {
        this.valoraciones = valoraciones;
    }

    //Métodos extra

        // 1. Comprar una entrada
    public void comprarEntrada(Concierto concierto, Entrada.TipoEntrada tipo) {

        // 1.1 Comprobar si el concierto está activo
        if (!concierto.isActivo()) {
            System.out.println("Error: El concierto no está activo.");
            return;
        }

        // 1.2 Comprobar si el usuario ya ha asistido (está en el HashSet)
        if (conciertosAsistidos.contains(concierto)) {
            System.out.println("Error: Ya has asistido a este concierto.");
            return;
        }

        // 1.3 Comprobar si hay entradas disponibles
        if (concierto.entradasDisponibles() <= 0) {
            System.out.println("Error: No quedan entradas disponibles.");
            return;
        }

        // 1.4 Crear el objeto Entrada
        Entrada nuevaEntrada = new Entrada(concierto, tipo);

        // 1.5 Añadir la entrada al concierto
        concierto.getEntradasVendidas().add(nuevaEntrada);

        // 1.6 Añadir la entrada al usuario
        this.entradasCompradas.add(nuevaEntrada);

        // 1.7 Añadir el concierto a la lista de asistidos
        this.conciertosAsistidos.add(concierto);

        System.out.println(this.nombre + " ha comprado con éxito una entrada de tipo " + tipo + " para " + concierto.getArtista() + "!");
    }

        // 2. Valorar un concierto
    public void valorar(Concierto concierto, int valoracion) throws Exception {

        // 2.1 Comprobar si asistió al concierto
        if (concierto == null || !conciertosAsistidos.contains(concierto)) {
            // 2. LANZAMOS la excepción en lugar de solo imprimir
            throw new Exception("No puedes valorar un concierto al que no has asistido.");
        }

        // 2.2 Comprobar que la nota esté entre 0 y 10
        if (valoracion < 0 || valoracion > 10) {
            System.out.println("Error: La valoración debe estar entre 0 y 10.");
            return;
        }

        // 2.3 Añadir o modificar la valoración en el HashMap
        valoraciones.put(concierto, valoracion);
        System.out.println("Valoración guardada: " + valoracion);
    }

    @Override
    public String toString() {
        return nombre + " (ha asistido a " + conciertosAsistidos.size() + " conciertos)";
    }


    //Equals y hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        // He vuelto a crear el equals sin las listas ni el mapa para evitar el StackOverflow
        return edad == usuario.edad && Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        // He vuelto a crear el hashCode solo con nombre y edad para evitar que el programa se cuelgue
        return Objects.hash(nombre, edad);
    }
}
