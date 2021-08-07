package datatypes.protocolmessages;

import datatypes.Vaccinato;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class UserLoginResponse extends PacketACK {
    private static final long serialVersionUID = -3902911082916587221L;
    private Vaccinato vaccinato;

    public UserLoginResponse(boolean esito, Vaccinato vaccinato) {
        super(esito);
        this.vaccinato = vaccinato;
    }

    public UserLoginResponse(boolean esito) {
        super(esito);
    }

    public Vaccinato getVaccinato() {
        return vaccinato;
    }

    public void setVaccinato(Vaccinato vaccinato) {
        this.vaccinato = vaccinato;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
