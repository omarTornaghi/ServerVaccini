package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class UserRegistrationResponse extends PacketACK{
    public UserRegistrationResponse(boolean esito) {
        super(esito);
    }

    public UserRegistrationResponse() {
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
