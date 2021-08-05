package datatypes.protocolmessages;

import datatypes.Vaccinazione;

public class GetVaccinationByKeyResponse extends PacketACK{
    private Vaccinazione vaccination;

    public GetVaccinationByKeyResponse(boolean esito, Vaccinazione vaccination) {
        super(esito);
        this.vaccination = vaccination;
    }

    public Vaccinazione getVaccination() {
        return vaccination;
    }

    public void setVaccination(Vaccinazione vaccination) {
        this.vaccination = vaccination;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
