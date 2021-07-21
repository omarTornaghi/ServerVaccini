package datatypes.protocolmessages;

public abstract class PacketACK extends Packet{
    private boolean esito;
    public PacketACK(){}
    public PacketACK(boolean esito){this.esito = esito;}

    public boolean isEsito() {
        return esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    public abstract String getPacketName();
}
