package datatypes.protocolmessages;

public class LoginResponse extends Packet {
    private static final long serialVersionUID = -3902911082916587221L;
    private boolean esito;

    public LoginResponse(boolean esito) {
        this.esito = esito;
    }

    public boolean isEsito() {
        return esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
