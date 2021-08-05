package datatypes.protocolmessages;

import datatypes.CentroVaccinale;

public class GetReportRequest extends Packet{
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
