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
    private String mailVaccinato;

    public RegistrationVaccinatedRequest() {
    }

    public RegistrationVaccinatedRequest(Vaccinazione vaccinazione, String mailVaccinato) {
        this.vaccinazione = vaccinazione;
        this.mailVaccinato = mailVaccinato;
    }

    public Vaccinazione getVaccinazione() {
        return vaccinazione;
    }

    public void setVaccinazione(Vaccinazione vaccinazione) {
        this.vaccinazione = vaccinazione;
    }

    public String getMailVaccinato() {
        return mailVaccinato;
    }

    public void setMailVaccinato(String mailVaccinato) {
        this.mailVaccinato = mailVaccinato;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
