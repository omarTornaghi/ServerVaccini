package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class RegistrationCVResponse extends PacketACK{
    public RegistrationCVResponse(){ }
    public RegistrationCVResponse(boolean esito){ super(esito); }
    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
