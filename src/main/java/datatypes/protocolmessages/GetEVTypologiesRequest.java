package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetEVTypologiesRequest extends Packet{
    public GetEVTypologiesRequest() {
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
