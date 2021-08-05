package datatypes.protocolmessages;

import datatypes.TipologiaEventoAvverso;

import java.util.List;

public class GetEvTypologiesResponse extends PacketACK{
    List<TipologiaEventoAvverso> typologies;

    public GetEvTypologiesResponse(boolean esito, List<TipologiaEventoAvverso> typologies) {
        super(esito);
        this.typologies = typologies;
    }

    public List<TipologiaEventoAvverso> getTypologies() {
        return typologies;
    }

    public void setTypologies(List<TipologiaEventoAvverso> typologies) {
        this.typologies = typologies;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
