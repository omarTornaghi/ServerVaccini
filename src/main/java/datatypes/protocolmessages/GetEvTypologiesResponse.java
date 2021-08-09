package datatypes.protocolmessages;

import datatypes.TipologiaEventoAvverso;

import java.util.List;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetEvTypologiesResponse extends PacketACK{
    private static final long serialVersionUID = 6416639449007779623L;
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
