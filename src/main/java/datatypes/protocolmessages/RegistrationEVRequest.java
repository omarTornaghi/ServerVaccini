package datatypes.protocolmessages;

import datatypes.EventoAvverso;
import datatypes.Vaccinato;

public class RegistrationEVRequest extends Packet{
    private EventoAvverso eventoAvverso;
    private Vaccinato vaccinato;

    public RegistrationEVRequest() {
    }

    public RegistrationEVRequest(EventoAvverso eventoAvverso, Vaccinato vaccinato) {
        this.eventoAvverso = eventoAvverso;
        this.vaccinato = vaccinato;
    }

    public EventoAvverso getEventoAvverso() {
        return eventoAvverso;
    }

    public void setEventoAvverso(EventoAvverso eventoAvverso) {
        this.eventoAvverso = eventoAvverso;
    }

    public Vaccinato getVaccinato() {
        return vaccinato;
    }

    public void setVaccinato(Vaccinato vaccinato) {
        this.vaccinato = vaccinato;
    }

    @Override
    public String getPacketName() {
        return null;
    }
}
