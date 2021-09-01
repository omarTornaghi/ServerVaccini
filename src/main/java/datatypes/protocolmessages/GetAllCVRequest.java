package datatypes.protocolmessages;

/**
 * Classe model per scambio di messaggi
 * richiede la lista di tutti i centri vaccinali
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetAllCVRequest extends Packet{
    private static final long serialVersionUID = -8498848924803063312L;

    public GetAllCVRequest() {
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
