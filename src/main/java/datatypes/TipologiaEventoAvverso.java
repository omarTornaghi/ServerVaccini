package datatypes;

import java.io.Serializable;

public class TipologiaEventoAvverso implements Serializable {
    private static final long serialVersionUID = -1340051494982780467L;
    private int id;
    private String nome;

    public TipologiaEventoAvverso() {
    }

    public TipologiaEventoAvverso(String nome) {
        this.nome = nome;
    }

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

    @Override
    public String toString() {
        return "TipologiaEventoAvverso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
