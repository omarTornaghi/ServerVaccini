package datatypes;

import java.io.Serializable;

/**
 * Classe ausiliaria a @ReportCV
 * @author Tornaghi Omar
 * @version 1.0
 */
public class ReportData implements Serializable {
    private static final long serialVersionUID = 7278429111156466601L;
    private int contCasi;
    private double severitaMedia;

    public ReportData(int contCasi, double severitaMedia) {
        this.contCasi = contCasi;
        this.severitaMedia = severitaMedia;
    }

    public int getContCasi() {
        return contCasi;
    }

    public void setContCasi(int contCasi) {
        this.contCasi = contCasi;
    }

    public double getSeveritaMedia() {
        return severitaMedia;
    }

    public void setSeveritaMedia(double severitaMedia) {
        this.severitaMedia = severitaMedia;
    }
}
