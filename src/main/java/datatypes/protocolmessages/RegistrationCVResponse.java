package datatypes.protocolmessages;

public class RegistrationCVResponse extends PacketACK{
    public RegistrationCVResponse(){ }
    public RegistrationCVResponse(boolean esito){ super(esito); }
    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
