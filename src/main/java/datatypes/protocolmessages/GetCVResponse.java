package datatypes.protocolmessages;

import datatypes.CentroVaccinale;

import java.util.List;

public class GetCVResponse extends PacketACK{
    List<CentroVaccinale> cvList;

    public GetCVResponse() {
    }

    public GetCVResponse(boolean esito) {
        super(esito);
    }

    public GetCVResponse(List<CentroVaccinale> centri) {
        this.cvList = centri;
    }

    public GetCVResponse(boolean esito, List<CentroVaccinale> centri) {
        super(esito);
        this.cvList = centri;
    }

    public List<CentroVaccinale> getCvList() {
        return cvList;
    }

    public void setCvList(List<CentroVaccinale> cvList) {
        this.cvList = cvList;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
