package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class UserDisconnectResponse extends PacketACK{
    private static final long serialVersionUID = -6787983178992653013L;

    public UserDisconnectResponse(boolean esito) {
        super(esito);
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
