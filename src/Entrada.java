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

    //Métodos

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

    @Override
    public String toString() {
        return "Entrada de " + getPrecioTotal() + " €";
    }

    //=========Equals y HashCode=========

    @Override
    public boolean equals(Object o) {
        //Comprobación de identidad añadida después de usar el generador automático de IntelliJ
        if (this == o) return true;
        //El metodo equals como venía por defecto.
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        return Objects.equals(concierto, entrada.concierto) && tipo == entrada.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(concierto, tipo);
    }
}
