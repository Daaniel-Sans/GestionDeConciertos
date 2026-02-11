import java.util.ArrayList;
import java.util.Objects;

public class Concierto {

    // Atributos

    private String artista;
    private String ciudad;
    private double precioBase;
    private int aforoMaximo;
    private ArrayList<Entrada> entradasVendidas;
    private boolean activo;

    // Constructor vacío

    public Concierto() {
    }

    // Constructor completo

    public Concierto(String artista, String ciudad, double precioBase, int aforoMaximo, ArrayList<Entrada> entradasVendidas, boolean activo) {
        this.artista = artista;
        this.ciudad = ciudad;
        this.precioBase = precioBase;
        this.aforoMaximo = aforoMaximo;
        this.entradasVendidas = entradasVendidas;
        this.activo = activo;
    }

    //=============Getters y setters=================

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public ArrayList<Entrada> getEntradasVendidas() {
        return entradasVendidas;
    }

    public void setEntradasVendidas(ArrayList<Entrada> entradasVendidas) {
        this.entradasVendidas = entradasVendidas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    //=====================Métodos extra===================

    public double calcularRecaudacion() {
        double total = 0;
        for (Entrada e : entradasVendidas) {
            total += e.getPrecioTotal();
        }
        return total;
    }

    public double calcularPrecioMedio() {
        if (entradasVendidas.isEmpty()) {
            return 0; // Si el concierto se acaba de crear y no hay entradas vendidas devuelverá 0. Con esto trato de evitar que se divida por 0 y se lance una excepción
        }
        return calcularRecaudacion() / entradasVendidas.size();
    }

    public int entradasDisponibles() {
        return aforoMaximo - entradasVendidas.size();
    }

    //========================toString=================
    @Override
    public String toString() {
        return "Concierto de " + artista + " en " + ciudad;
    }

    //=====================Equals y hashCode=================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concierto concierto = (Concierto) o;
        // He vuelto a crear el equals sin la lista de entradas para evitar errores de memoria
        return Double.compare(concierto.precioBase, precioBase) == 0 && aforoMaximo == concierto.aforoMaximo && activo == concierto.activo && Objects.equals(artista, concierto.artista) && Objects.equals(ciudad, concierto.ciudad);
    }

    @Override
    public int hashCode() {
        // He vuelto a crear el hashCode sin la lista de entradas para que el Main funcione
        return Objects.hash(artista, ciudad, precioBase, aforoMaximo, activo);
    }
}
