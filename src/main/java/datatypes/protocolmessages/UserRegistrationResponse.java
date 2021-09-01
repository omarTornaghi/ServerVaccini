package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * Contiene l'esito della registrazione di un cittadino
 * @author Tornaghi Omar
 * @version 1.0
 */
public class UserRegistrationResponse extends PacketACK{
    private static final long serialVersionUID = 180845655808868841L;

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
