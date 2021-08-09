package datatypes.protocolmessages;


import datatypes.Vaccinazione;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class RegistrationVaccinatedRequest extends Packet{
    private static final long serialVersionUID = -5958503256939101020L;
    private Vaccinazione vaccinazione;

    public RegistrationVaccinatedRequest() {
    }

    public RegistrationVaccinatedRequest(Vaccinazione vaccinazione) {
        this.vaccinazione = vaccinazione;
    }

    public Vaccinazione getVaccinazione() {
        return vaccinazione;
    }

    public void setVaccinazione(Vaccinazione vaccinazione) {
        this.vaccinazione = vaccinazione;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
