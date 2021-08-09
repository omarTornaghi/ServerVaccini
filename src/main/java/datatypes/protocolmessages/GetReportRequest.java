package datatypes.protocolmessages;

import datatypes.CentroVaccinale;

/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class GetReportRequest extends Packet{
    private static final long serialVersionUID = -2836363273338011219L;
    private CentroVaccinale cv;

    public GetReportRequest() {
    }

    public GetReportRequest(CentroVaccinale cv) {
        this.cv = cv;
    }

    public CentroVaccinale getCv() {
        return cv;
    }

    public void setCv(CentroVaccinale cv) {
        this.cv = cv;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
