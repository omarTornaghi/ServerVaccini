package datatypes.protocolmessages;

public class GetCVByNameRequest extends Packet{
    private String nome;

    public GetCVByNameRequest() {
    }

    public GetCVByNameRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
