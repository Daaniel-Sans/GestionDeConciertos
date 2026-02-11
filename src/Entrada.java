public class Entrada {

    //Atributos concierto (de tipo Concierto)
    public enum TipoEntrada { PISTA, GRADA, VIP }

    /*Atributo tipo (enumerado que puede ser Pista, Grada o VIP).
    Yo llamaré tipoEntrada al tipo para evitar redundancias.*/

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



}
