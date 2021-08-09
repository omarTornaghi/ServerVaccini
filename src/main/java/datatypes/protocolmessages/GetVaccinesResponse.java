package datatypes.protocolmessages;

import datatypes.Vaccino;

import java.util.List;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetVaccinesResponse extends PacketACK{
    private static final long serialVersionUID = 96939311853176429L;
    List<Vaccino> vaccines;

    public GetVaccinesResponse(boolean esito, List<Vaccino> vaccines) {
        super(esito);
        this.vaccines = vaccines;
    }

    public List<Vaccino> getVaccines() {
        return vaccines;
    }

    public void setVaccines(List<Vaccino> vaccines) {
        this.vaccines = vaccines;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
