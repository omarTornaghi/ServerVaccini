package datatypes;

import java.io.Serializable;

/**
 * Classe model per salvare i dati del db in memoria
 * @author Tornaghi Omar
 * @version 1.0
 */
public class Vaccino implements Serializable {
    private static final long serialVersionUID = -3867755526472896352L;
    private int id;
    private String nome;

    public Vaccino(){}

    public Vaccino(String nome){ this.nome = nome; }

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
        return "Vaccino{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
