package datatypes.protocolmessages;

public class UserRegistrationResponse extends PacketACK{
    public UserRegistrationResponse(boolean esito) {
        super(esito);
    }

    public UserRegistrationResponse() {
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
