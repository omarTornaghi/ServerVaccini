package datatypes.protocolmessages;

public class RegistrationEVResponse extends PacketACK{
    public RegistrationEVResponse() {
    }

    public RegistrationEVResponse(boolean esito) {
        super(esito);
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
