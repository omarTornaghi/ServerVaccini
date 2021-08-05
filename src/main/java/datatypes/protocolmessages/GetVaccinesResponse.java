package datatypes.protocolmessages;

import datatypes.Vaccino;

import java.util.List;

public class GetVaccinesResponse extends PacketACK{
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
