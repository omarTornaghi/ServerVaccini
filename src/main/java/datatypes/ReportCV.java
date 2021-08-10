package datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe che rappresenta il report di un centro vaccinale
 * @author Tornaghi Omar
 * @version 1.0
 */
public class ReportCV implements Serializable {
    private static final long serialVersionUID = 4356133682696788126L;
    private CentroVaccinale centroVaccinale; // Forse inutile
    /**
     * Mappa che serve per correlare una tipologia di evento avverso
     * con quanti casi ha avuto e la severità media
     */
    private final Map<String, ReportData> eventiAvversi;
    /**
     * Numero di eventi avversi totali di un centro vaccinale
     */
    private int numEventiAvversi;
    /**
     * Severità media complessiva di un centro vaccinale
     */
    private double severitaMediaComplessiva;

    public ReportCV(){eventiAvversi = new HashMap<>(); }

    public CentroVaccinale getCentroVaccinale() {
        return centroVaccinale;
    }

    public void setCentroVaccinale(CentroVaccinale centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    /**
     * Inserisce nell'HashMap una nuova tipologia con dentro cont casi e severita media
     * @param tipo tipologia
     * @param count contatore dei casi
     * @param media severità media
     */
    public void setCountEV(String tipo, int count, double media)
    {
        eventiAvversi.put(tipo, new ReportData(count, media));
    }

    public int getNumEventiAvversi() {
        return numEventiAvversi;
    }

    public void setNumEventiAvversi(int numEventiAvversi) {
        this.numEventiAvversi = numEventiAvversi;
    }

    public double getSeveritaMediaComplessiva() {
        return severitaMediaComplessiva;
    }

    public void setSeveritaMediaComplessiva(double severitaMediaComplessiva) {
        this.severitaMediaComplessiva = severitaMediaComplessiva;
    }

    /**
     * Ritorna una lista dove se la chiave è in posizione i allora il contatore è
     * in posizione i + 1 e la media in posizione i + 2
     * @return lista rappresentate il report
     */
    public List<String> getReportList(){
        List<String> reportList = new ArrayList<>();
        for ( String key : eventiAvversi.keySet() ) {
            reportList.add(key);
            ReportData rd = eventiAvversi.get(key);
            reportList.add(String.valueOf(rd.getContCasi()));
            reportList.add(String.valueOf(rd.getSeveritaMedia()));
        }
        return reportList;
    }
}
