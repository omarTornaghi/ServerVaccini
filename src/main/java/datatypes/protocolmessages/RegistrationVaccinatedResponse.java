package datatypes.protocolmessages;

public class RegistrationVaccinatedResponse extends PacketACK{
    private String chiave;

    public RegistrationVaccinatedResponse() {
    }

    public RegistrationVaccinatedResponse(boolean esito, String chiave) {
        super(esito);
        this.chiave = chiave;
    }

    public String getChiave() {
        return chiave;
    }

    public void setChiave(String chiave) {
        this.chiave = chiave;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
