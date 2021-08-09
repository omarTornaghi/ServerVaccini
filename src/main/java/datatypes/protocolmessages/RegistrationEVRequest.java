package datatypes.protocolmessages;

import datatypes.EventoAvverso;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class RegistrationEVRequest extends Packet{
    private static final long serialVersionUID = 7524697263590799705L;
    private EventoAvverso eventoAvverso;

    public RegistrationEVRequest() {
    }

    public RegistrationEVRequest(EventoAvverso eventoAvverso) {
        this.eventoAvverso = eventoAvverso;
    }

    public EventoAvverso getEventoAvverso() {
        return eventoAvverso;
    }

    public void setEventoAvverso(EventoAvverso eventoAvverso) {
        this.eventoAvverso = eventoAvverso;
    }

    @Override
    public String getPacketName() {
        return null;
    }
}
