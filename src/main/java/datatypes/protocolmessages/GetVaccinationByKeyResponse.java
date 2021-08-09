package datatypes.protocolmessages;

import datatypes.Vaccinazione;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetVaccinationByKeyResponse extends PacketACK{
    private static final long serialVersionUID = -6404116392736468648L;
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
