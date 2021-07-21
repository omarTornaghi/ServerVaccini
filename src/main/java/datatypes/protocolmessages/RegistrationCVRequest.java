package datatypes.protocolmessages;

import datatypes.CentroVaccinale;

public class RegistrationCVRequest extends Packet{
    private CentroVaccinale cv;

    public RegistrationCVRequest() {
    }

    public RegistrationCVRequest(CentroVaccinale cv) {
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
