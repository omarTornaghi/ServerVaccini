package datatypes.protocolmessages;

public class GetAllCVRequest extends Packet{
    public GetAllCVRequest() {
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
