package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * Richiede la disconnessione
 * @author Tornaghi Omar
 * @version 1.0
 */
public class UserDisconnectRequest extends Packet{
    private static final long serialVersionUID = 807811250722829231L;

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
