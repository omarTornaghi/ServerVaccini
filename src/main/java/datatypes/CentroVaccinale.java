package datatypes;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe model per salvare i dati del db in memoria
 * @author Tornaghi Omar
 * @version 1.0
 */
public class CentroVaccinale implements Serializable {
    private static final long serialVersionUID = 5479954396078784236L;
    private int id;
    private String nome;
    private String nomeIndirizzo;
    private String comune;
    private String numero;
    private String qualificatore;
    private String siglaProvincia;
    private String cap;
    private String tipologia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeIndirizzo() {
        return nomeIndirizzo;
    }

    public void setNomeIndirizzo(String nomeIndirizzo) {
        this.nomeIndirizzo = nomeIndirizzo;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getQualificatore() {
        return qualificatore;
    }

    public void setQualificatore(String qualificatore) {
        this.qualificatore = qualificatore;
    }

    public String getSiglaProvincia() {
        return siglaProvincia;
    }

    public void setSiglaProvincia(String siglaProvincia) {
        this.siglaProvincia = siglaProvincia;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentroVaccinale that = (CentroVaccinale) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CentroVaccinale{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", nomeIndirizzo='" + nomeIndirizzo + '\'' +
                ", comune='" + comune + '\'' +
                ", numero='" + numero + '\'' +
                ", qualificatore='" + qualificatore + '\'' +
                ", siglaProvincia='" + siglaProvincia + '\'' +
                ", cap='" + cap + '\'' +
                ", tipologia='" + tipologia + '\'' +
                '}';
    }
}
