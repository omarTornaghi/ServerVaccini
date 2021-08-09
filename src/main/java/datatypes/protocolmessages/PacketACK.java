package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public abstract class PacketACK extends Packet{
    private static final long serialVersionUID = 184085356701110105L;
    /**
     * Esito dell'operazione
     */
    private boolean esito;
    public PacketACK(){}
    public PacketACK(boolean esito){this.esito = esito;}

    public boolean isEsito() {
        return esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }
    /**
     * Permette di ottenere il nome del pacchetto
     * @return stringa contenente il nome del pacchetto
     */
    public abstract String getPacketName();
}
