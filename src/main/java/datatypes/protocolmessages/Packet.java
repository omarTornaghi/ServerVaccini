package datatypes.protocolmessages;

import java.io.Serializable;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public abstract class Packet implements Serializable {

    private static final long serialVersionUID = -2683731630367166754L;

    /**
     * Permette di ottenere il nome del pacchetto
     * @return stringa contenente il nome del pacchetto
     */
    public abstract String getPacketName();
}
