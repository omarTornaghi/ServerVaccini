package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetVaccinesRequest extends Packet{
    private static final long serialVersionUID = -6608599340089735955L;

    public GetVaccinesRequest() {
    }
    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
