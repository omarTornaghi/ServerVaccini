package datatypes;

import java.io.Serializable;
import java.util.Date;

public class Vaccinazione implements Serializable {
    private static final long serialVersionUID = -6514125346962267213L;
    private String id;
    private Vaccinato vaccinato;
    private CentroVaccinale centroVaccinale;
    private Vaccino vaccino;
    private Date dataVaccinazione;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vaccinato getVaccinato() {
        return vaccinato;
    }

    public void setVaccinato(Vaccinato vaccinato) {
        this.vaccinato = vaccinato;
    }

    public CentroVaccinale getCentroVaccinale() {
        return centroVaccinale;
    }

    public void setCentroVaccinale(CentroVaccinale centroVaccinale) {
        this.centroVaccinale = centroVaccinale;
    }

    public Vaccino getVaccino() {
        return vaccino;
    }

    public void setVaccino(Vaccino vaccino) {
        this.vaccino = vaccino;
    }

    public Date getDataVaccinazione() {
        return dataVaccinazione;
    }

    public void setDataVaccinazione(Date dataVaccinazione) {
        this.dataVaccinazione = dataVaccinazione;
    }
}
