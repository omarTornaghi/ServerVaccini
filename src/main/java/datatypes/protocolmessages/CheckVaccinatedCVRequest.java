package datatypes.protocolmessages;

import datatypes.CentroVaccinale;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class CheckVaccinatedCVRequest extends Packet{
    private static final long serialVersionUID = 8372097054693803770L;
    private CentroVaccinale centroVaccinale;

    public CheckVaccinatedCVRequest(CentroVaccinale centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    public CentroVaccinale getCentroVaccinale() {
        return centroVaccinale;
    }

    public void setCentroVaccinale(CentroVaccinale centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
