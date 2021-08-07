package database;

import datatypes.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CLasse singleton utilitaria. Permette di dialogare con il database postgreSQL.
 */
public class DBHelper {
    /**
     * Istanza della classe
     */
    private static DBHelper instance = null;
    /**
     * Connessione al database
     */
    private static Connection connection;

    private DBHelper() {
        connect();
    }

    /**
     * Fornisce l'istanza del dbHelper se presente, altrimenti lo inizializza
     * @return istanza del dbHelper
     */
    public static synchronized DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    /**
     * Si connette al database
     */
    private void connect() {
        try {
            String user = "admin";
            String password = "admin";
            String url = "jdbc:postgresql://localhost/VacciniDB";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connessione al DB riuscita");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inserisce un nuovo centro vaccinale
     * @param cv centro vaccinale da inserire
     * @return true se inserito, false altrimenti
     * @throws SQLException eccezione sql
     */
    public boolean insertCV(CentroVaccinale cv) throws SQLException {
        String sql = "INSERT INTO public.centrovaccinale(\n" +
                "\tnome, nomeindirizzo, comune, numero, qualificatore, siglaprovincia, cap, tipologia)\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cv.getNome());
        statement.setString(2, cv.getNomeIndirizzo());
        statement.setString(3, cv.getComune());
        statement.setString(4, cv.getNumero());
        statement.setString(5, cv.getQualificatore());
        statement.setString(6, cv.getSiglaProvincia());
        statement.setString(7, cv.getCap());
        statement.setString(8, cv.getTipologia());
        return statement.executeUpdate() > 0;
    }

    /**
     * Inserisce una nuova vaccinazione
     * @param vaccinazione vaccinazione da inserire
     * @return true se inserita, false altrimenti
     * @throws SQLException eccezione SQL
     */
    public String insertVaccination(Vaccinazione vaccinazione) throws SQLException {
        //Prima controllo se devo creare il vaccinato
        Vaccinato v = vaccinazione.getVaccinato();
        String sql = "SELECT * FROM Vaccinato WHERE CodiceFiscale = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, v.getCodiceFiscale());
        ResultSet result = statement.executeQuery();
        if (!result.next()) {
            //Non ho trovato un vaccinato, lo creo
            statement = connection.prepareStatement("INSERT INTO vaccinato(\n" +
                    "\tcodicefiscale, nome, cognome, userid, email, password)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?);");
            statement.setString(1, v.getCodiceFiscale());
            statement.setString(2, v.getNome());
            statement.setString(3, v.getCognome());
            statement.setString(4, null);
            statement.setString(5, null);
            statement.setString(6, null);
            if (statement.executeUpdate() < 1) return null;
        }
        //Creo la vaccinazione
        String codice = RandomStringUtils.randomAlphanumeric(16);
        statement = connection.prepareStatement("INSERT INTO vaccinazione(\n" +
                "\tid, datavaccinazione, vaccinoid, centrovaccinaleid, vaccinatocodicefiscale)\n" +
                "\tVALUES (?, ?, ?, ?, ?);");
        statement.setString(1, codice);
        statement.setDate(2, (Date) vaccinazione.getDataVaccinazione());
        statement.setInt(3, vaccinazione.getVaccino().getId());
        statement.setInt(4, vaccinazione.getCentroVaccinale().getId());
        statement.setString(5, v.getCodiceFiscale());
        if (statement.executeUpdate() > 0) return codice;
        else return null;
    }

    /**
     * Inserisce un nuovo vaccino
     * @param v vaccino da inserire
     * @return true se inserito, false altrimenti
     * @throws SQLException eccezione SQL
     */
    public boolean insertVaccine(Vaccino v) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO vaccino(nome) VALUES (?);");
        statement.setString(1, v.getNome());
        return statement.executeUpdate() > 0;
    }

    /**
     * Inserisce una nuova tipologia di evento avverso
     * @param ta tipologia da inserire
     * @return true se inserito, false altrimenti
     * @throws SQLException eccezione SQL
     */
    public boolean insertEventTypology(TipologiaEventoAvverso ta) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO tipologiaeventoavverso(nome)\n" +
                "\tVALUES (?);");
        statement.setString(1, ta.getNome());
        return statement.executeUpdate() > 0;
    }

    /**
     * Inserisce un nuovo evento avverso
     * @param ev evento avverso da inserire
     * @param vaccinato vaccinato che lo richiede
     * @return true se inserito, false altrimenti
     * @throws SQLException eccezione SQL
     */
    public boolean insertEvent(EventoAvverso ev, Vaccinato vaccinato) throws SQLException {
        try {
            Vaccinazione ultimaVaccinazione = getLastVaccination(vaccinato);
            if (ultimaVaccinazione == null || !(ultimaVaccinazione.getCentroVaccinale().equals(ev.getCentroVaccinale())))
                return false; //Tentativo di registrare un ev avv su un cv diverso da dove ci si è vaccinati
            PreparedStatement statement = connection.prepareStatement("INSERT INTO eventoavverso(\n" +
                    "\tseverita, note, tipologiaeventoavversoid, centrovaccinaleid)\n" +
                    "\tVALUES (?, ?, ?, ?);");
            statement.setInt(1, ev.getSeverita());
            statement.setString(2, ev.getNote());
            statement.setInt(3, ev.getTipologia().getId());
            statement.setInt(4, ev.getCentroVaccinale().getId());
            return statement.executeUpdate() > 0;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Registra un nuovo utente(cittadino)
     * @param vaccinato da registrare
     * @param chiave identificativo della vaccinazione
     * @return true se inserito, false altrimenti
     * @throws SQLException eccezione SQL
     */
    public boolean registerUser(Vaccinato vaccinato, String chiave) throws SQLException {
        //Controllo che la chiave sia la stessa della vaccinazione
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM vaccinazione WHERE id=? AND vaccinatocodicefiscale = ?");
        statement.setString(1, chiave);
        statement.setString(2, vaccinato.getCodiceFiscale());
        ResultSet result = statement.executeQuery();
        if (!result.next()) return false; //Tentativo di registrare un cittadino senza avere fatto una vaccinazione
        //Registro l'utente solo se non si è già registrato prima
        statement = connection.prepareStatement("SELECT * FROM vaccinato WHERE codicefiscale = ? AND userid IS NOT NULL");
        statement.setString(1, vaccinato.getCodiceFiscale());
        result = statement.executeQuery();
        if (result.next()) return false; //Cittadino già registrato
        statement = connection.prepareStatement("UPDATE vaccinato SET userid = ?, email = ?, password = ? WHERE codicefiscale = ?");
        statement.setString(1, vaccinato.getUserId());
        statement.setString(2, vaccinato.getEmail());
        statement.setString(3, Crypter.sha256(vaccinato.getPassword()));
        statement.setString(4, vaccinato.getCodiceFiscale());
        try {
            statement.executeUpdate();
            return true;
        } catch (SQLException sqlExcp) {
            return false;
        }
    }

    /**
     * Login cittadino
     * @param username username del cittadino
     * @param password password del cittadino
     * @return true se login superato, false altrimenti
     * @throws SQLException eccezione SQL
     */
    public Vaccinato login(String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM vaccinato WHERE userid = ? AND password = ?");
        statement.setString(1, username);
        statement.setString(2, Crypter.sha256(password));
        ResultSet result = statement.executeQuery();
        if (!result.next()) return null;
        return getVaccinatedFromResult(result);
    }

    private CentroVaccinale getCVFromResult(ResultSet result) throws SQLException {
        CentroVaccinale cv = new CentroVaccinale();
        cv.setId(result.getInt(1));
        cv.setNome(result.getString(2));
        cv.setNomeIndirizzo(result.getString(3));
        cv.setComune(result.getString(4));
        cv.setNumero(result.getString(5));
        cv.setQualificatore(result.getString(6));
        cv.setSiglaProvincia(result.getString(7));
        cv.setCap(result.getString(8));
        cv.setTipologia(result.getString(9));
        return cv;
    }

    private Vaccinato getVaccinatedFromResult(ResultSet result) throws SQLException{
        Vaccinato v = new Vaccinato();
        v.setCodiceFiscale(result.getString(1));
        v.setNome(result.getString(2));
        v.setCognome(result.getString(3));
        v.setUserId(result.getString(4));
        v.setEmail(result.getString(5));
        return v;
    }

    /**
     * Ottiene tutti i centri vaccinali
     * @return lista con centri vaccinali
     * @throws SQLException eccezione SQL
     */
    public List<CentroVaccinale> getCV() throws SQLException {
        List<CentroVaccinale> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM centrovaccinale;");
        while (result.next()) {
            CentroVaccinale cv = getCVFromResult(result);
            list.add(cv);
        }
        return list;
    }

    /**
     * Ottiene i centri vaccinali con nome uguale
     * @param nome del centro vaccinale da ricercare
     * @return lista dei centri vaccinali con nome uguale
     * @throws SQLException eccezione SQL
     */
    public List<CentroVaccinale> getCV(String nome) throws SQLException {
        List<CentroVaccinale> list = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM centrovaccinale WHERE nome LIKE '%' || ? || '%'");
        statement.setString(1, nome);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            CentroVaccinale cv = getCVFromResult(result);
            list.add(cv);
        }
        return list;
    }

    /**
     * Ottiene i centri vaccinali con comune e tipologia uguali
     * @param comune comune da ricercare
     * @param tipologia tipologia da ricercare
     * @return lista dei centri vaccinali trovati
     * @throws SQLException eccezione SQL
     */
    public List<CentroVaccinale> getCV(String comune, String tipologia) throws SQLException {
        List<CentroVaccinale> list = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM centrovaccinale WHERE comune = ? AND tipologia = ?");
        statement.setString(1, comune);
        statement.setString(2, tipologia);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            CentroVaccinale cv = getCVFromResult(result);
            list.add(cv);
        }
        return list;
    }

    /**
     * Ottiene il centro vaccinale con identificativo uguale a @id
     * @param id id da ricercare
     * @return centro vaccinale trovato, null altrimenti
     * @throws SQLException eccezione SQL
     */
    public CentroVaccinale getCVById(int id) throws SQLException {
        CentroVaccinale cv;
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM centrovaccinale WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (!result.next()) return null;
        cv = getCVFromResult(result);
        return cv;
    }

    /**
     * Ottiene il vaccino con identificativo uguale a @id
     * @param id id da ricercare
     * @return vaccino trovato, null altrimenti
     * @throws SQLException eccezione SQL
     */
    public Vaccino getVaccinoById(int id) throws SQLException {
        Vaccino v = new Vaccino();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM vaccino WHERE id = ?;");
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (!result.next()) return null;
        v.setId(id);
        v.setNome(result.getString(2));
        return v;
    }

    /**
     * Ottiene il vaccinato con codice fiscale uguale a @cf
     * @param cf codice fiscale da trovare
     * @return Vaccinato se esiste cf, null altrimenti
     * @throws SQLException eccezione SQL
     */
    public Vaccinato getVaccinatedById(String cf) throws SQLException{
        Vaccinato v = new Vaccinato();
        PreparedStatement statement = connection.prepareStatement("select * from vaccinato where codicefiscale = ?");
        statement.setString(1, cf);
        ResultSet result = statement.executeQuery();
        if(!result.next()) return null;
        return getVaccinatedFromResult(result);
    }

    /**
     * Ottiene una vaccinazione con identificativo uguale a @key
     * @param key identificativo da ricercare
     * @return vaccinazione se esiste key, null altrimenti
     * @throws SQLException eccezione SQL
     */
    public Vaccinazione getVaccinationById(String key) throws SQLException{
        Vaccinazione vaccinazione = new Vaccinazione();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM vaccinazione WHERE Id = ?");
        statement.setString(1, key);
        ResultSet result = statement.executeQuery();
        if (!result.next()) return null;
        vaccinazione.setId(result.getString(1));
        vaccinazione.setDataVaccinazione(result.getDate(2));
        vaccinazione.setVaccino(getVaccinoById(result.getInt(3)));
        vaccinazione.setCentroVaccinale(getCVById(result.getInt(4)));
        vaccinazione.setVaccinato(getVaccinatedById(result.getString(5)));
        return vaccinazione;
    }

    /**
     * Ottiene l'ultima vaccinazione di un vaccinato
     * @param vaccinato il vaccinato di cui interessa l'ultima vaccinazione
     * @return vaccinazione se esiste, null altrimenti
     * @throws SQLException eccezione SQL
     */
    public Vaccinazione getLastVaccination(Vaccinato vaccinato) throws SQLException {
        Vaccinazione vaccinazione = new Vaccinazione();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM vaccinazione WHERE vaccinatocodicefiscale = ? AND datavaccinazione >= ALL(SELECT datavaccinazione FROM vaccinazione WHERE vaccinatocodicefiscale = ?);");
        statement.setString(1, vaccinato.getCodiceFiscale());
        statement.setString(2, vaccinato.getCodiceFiscale());
        ResultSet result = statement.executeQuery();
        if (!result.next()) return null;
        vaccinazione.setId(result.getString(1));
        vaccinazione.setDataVaccinazione(result.getDate(2));
        vaccinazione.setVaccino(getVaccinoById(result.getInt(3)));
        vaccinazione.setCentroVaccinale(getCVById(result.getInt(4)));
        vaccinazione.setVaccinato(vaccinato);
        return vaccinazione;
    }

    /**
     * Ottiene tutti i vaccini
     * @return tutti i vaccini
     * @throws SQLException eccezione SQL
     */
    public List<Vaccino> getVaccines() throws SQLException {
        List<Vaccino> vaccini = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM vaccino;");
        while (result.next()) {
            Vaccino v = new Vaccino();
            v.setId(result.getInt(1));
            v.setNome(result.getString(2));
            vaccini.add(v);
        }
        return vaccini;
    }

    /**
     * Ottiene tutte le tipologie di eventi avversi
     * @return tutte le tipologie di eventi avversi
     * @throws SQLException
     */
    public List<TipologiaEventoAvverso> getEventTypes() throws SQLException {
        List<TipologiaEventoAvverso> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM tipologiaeventoavverso");
        while (result.next()) {
            TipologiaEventoAvverso ev = new TipologiaEventoAvverso();
            ev.setId(result.getInt(1));
            ev.setNome(result.getString(2));
            list.add(ev);
        }
        return list;
    }

    /**
     * Genera un report del centro vaccinale dato come parametro
     * @param cv centro vaccinale interessato
     * @return report per quel centro vaccinale
     * @throws SQLException eccezione SQL
     */
    public ReportCV generateReport(CentroVaccinale cv) throws SQLException {
        ReportCV report = new ReportCV();
        report.setCentroVaccinale(cv);
        PreparedStatement statement = connection.prepareStatement("SELECT tipologiaeventoavverso.Nome, COUNT(eventoavverso.Id)\n" +
                "FROM tipologiaeventoavverso INNER JOIN eventoavverso\n" +
                "ON tipologiaeventoavverso.Id = eventoavverso.TipologiaEventoAvversoId\n" +
                "WHERE eventoavverso.CentroVaccinaleId = ?\n" +
                "GROUP BY tipologiaeventoavverso.nome");
        statement.setInt(1, cv.getId());
        ResultSet result = statement.executeQuery();
        int numEv = 0;
        while (result.next()) {
            String nome = result.getString(1);
            int count = result.getInt(2);
            numEv += count;
            report.setCountEV(nome, count);
        }
        report.setNumEventiAvversi(numEv);
        //Severita media
        statement = connection.prepareStatement("SELECT AVG(severita) FROM eventoavverso WHERE centrovaccinaleid = ?");
        statement.setInt(1, cv.getId());
        result = statement.executeQuery();
        if (!result.next()) return null;
        report.setSeveritaMedia(result.getDouble(1));
        return report;
    }

}
