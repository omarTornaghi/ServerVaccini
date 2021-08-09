package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class CheckUserIdResponse extends PacketACK{
    private static final long serialVersionUID = -5381325773986273361L;

    public CheckUserIdResponse(boolean esito) {
        super(esito);
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
