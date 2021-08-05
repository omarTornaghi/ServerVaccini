package datatypes.protocolmessages;

public class GetVaccinationByKeyRequest extends Packet{
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
