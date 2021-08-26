package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class CheckVaccinatedCVResponse extends PacketACK{
    private static final long serialVersionUID = -6971077161341996246L;

    public CheckVaccinatedCVResponse(boolean esito) {
        super(esito);
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
