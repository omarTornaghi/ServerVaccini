package datatypes.protocolmessages;

public class GetCVByMunicipalityTypologyRequest extends Packet {
    private String municipality;
    private String typology;

    public GetCVByMunicipalityTypologyRequest() {
    }

    public GetCVByMunicipalityTypologyRequest(String municipality, String typology) {
        this.municipality = municipality;
        this.typology = typology;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
