package datatypes.protocolmessages;

public class GetEVTypologiesRequest extends Packet{
    public GetEVTypologiesRequest() {
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
