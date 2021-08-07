package datatypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe model per salvare i dati del db in memoria
 * @author Tornaghi Omar
 * @version 1.0
 */
public class ReportCV implements Serializable {
    private CentroVaccinale centroVaccinale; // Forse inutile
    private final Map<String, Integer> eventiAvversi;
    private int numEventiAvversi;
    private double severitaMedia;

    public ReportCV(){eventiAvversi = new HashMap<>(); }

    public CentroVaccinale getCentroVaccinale() {
        return centroVaccinale;
    }

    public void setCentroVaccinale(CentroVaccinale centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    public void setCountEV(String tipo, int count){
        eventiAvversi.put(tipo, count);
    }

    public int getNumEventiAvversi() {
        return numEventiAvversi;
    }

    public void setNumEventiAvversi(int numEventiAvversi) {
        this.numEventiAvversi = numEventiAvversi;
    }

    public double getSeveritaMedia() {
        return severitaMedia;
    }

    public void setSeveritaMedia(double severitaMedia) {
        this.severitaMedia = severitaMedia;
    }

    public List<String> getReportString(){
        /* Ritorna una lista dove se la chiave è in posizione i */
        /* allora il contatore è in posizione i + 1 */
        List<String> reportList = new ArrayList<>();
        for ( String key : eventiAvversi.keySet() ) {
            reportList.add(key);
            reportList.add(eventiAvversi.get(key).toString());
        }
        return reportList;
    }
}
