package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
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
