package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class RegistrationCVResponse extends PacketACK{
    private static final long serialVersionUID = -874624498039063616L;

    public RegistrationCVResponse(){ }
    public RegistrationCVResponse(boolean esito){ super(esito); }
    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
