package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetVaccinationByKeyRequest extends Packet{
    private static final long serialVersionUID = 8415106386104463159L;
    private String key;

    public GetVaccinationByKeyRequest() {
    }

    public GetVaccinationByKeyRequest(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
