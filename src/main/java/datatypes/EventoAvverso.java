package datatypes;

import java.io.Serializable;

/**
 * Classe model per salvare i dati del db in memoria
 * @author Tornaghi Omar
 * @version 1.0
 */
public class EventoAvverso implements Serializable {
    private static final long serialVersionUID = 1844985672063253560L;
    private int id;
    private int severita;
    private String note;
    private CentroVaccinale centroVaccinale;
    private TipologiaEventoAvverso tipologia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeverita() {
        return severita;
    }

    public void setSeverita(int severita) {
        this.severita = severita;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CentroVaccinale getCentroVaccinale() {
        return centroVaccinale;
    }

    public void setCentroVaccinale(CentroVaccinale centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    public TipologiaEventoAvverso getTipologia() {
        return tipologia;
    }

    public void setTipologia(TipologiaEventoAvverso tipologia) {
        this.tipologia = tipologia;
    }
}
