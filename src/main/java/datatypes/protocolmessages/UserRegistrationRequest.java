package datatypes.protocolmessages;

import datatypes.Vaccinato;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class UserRegistrationRequest extends Packet{
    private static final long serialVersionUID = 2761386278526330531L;
    private Vaccinato vaccinato;
    private String key;

    public UserRegistrationRequest() {
    }

    public UserRegistrationRequest(Vaccinato vaccinato, String key) {
        this.vaccinato = vaccinato;
        this.key = key;
    }

    public Vaccinato getVaccinato() {
        return vaccinato;
    }

    public void setVaccinato(Vaccinato vaccinato) {
        this.vaccinato = vaccinato;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
