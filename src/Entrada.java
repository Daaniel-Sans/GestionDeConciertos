import java.util.Objects;

public class Entrada {

    //Atributos concierto (de tipo Concierto)

    public enum TipoEntrada { PISTA, GRADA, VIP }

    /*Atributo tipo (enumerado que puede ser Pista, Grada o VIP).
    Yo llamaré tipoEntrada al tipo (de dato) para evitar redundancias.*/

    private Concierto concierto;
    private TipoEntrada tipo;


    //Constructor vacío

    public Entrada() {
    }

    //Constructor completo

    public Entrada(Concierto concierto, TipoEntrada tipo) {
        this.concierto = concierto;
        this.tipo = tipo;
    }

    //=======Getters y Setters========

    public Concierto getConcierto() {
        return concierto;
    }

    public void setConcierto(Concierto concierto) {
        this.concierto = concierto;
    }

    public TipoEntrada getTipo() {
        return tipo;
    }

    public void setTipo(TipoEntrada tipo) {
        this.tipo = tipo;
    }

    //=====================Métodos extra========================

    public double getPrecioTotal() {

        double precioBase = concierto.getPrecioBase();

        if (tipo == TipoEntrada.PISTA) {
            return precioBase * 1.10;
        } else if (tipo == TipoEntrada.VIP) {
            return precioBase * 1.20;
        } else {
            return precioBase;
        }
    }
    //=====================toString=====================
    @Override
    public String toString() {
        return "Entrada de " + getPrecioTotal() + " €";
    }

    //=========Equals y HashCode=========

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        // He vuelto a crear el equals sin el objeto concierto completo para evitar la excepcion StackOverflow
        return tipo == entrada.tipo &&
                Objects.equals(concierto.getArtista(), entrada.concierto.getArtista()) &&
                Objects.equals(concierto.getCiudad(), entrada.concierto.getCiudad());
    }

    @Override
    public int hashCode() {
        // He vuelto a crear el hashCode usando solo datos simples para evitar la excepcion StackOverflow
        return Objects.hash(concierto.getArtista(), concierto.getCiudad(), tipo);
    }
}
