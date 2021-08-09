package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetEVTypologiesRequest extends Packet{
    private static final long serialVersionUID = 3402743595483568010L;

    public GetEVTypologiesRequest() {
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
