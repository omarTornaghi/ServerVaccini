package datatypes.protocolmessages;

public class GetVaccinesRequest extends Packet{
    public GetVaccinesRequest() {
    }
    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
