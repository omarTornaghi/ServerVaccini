import database.Crypter;
import database.DBHelper;
import datatypes.*;
import server.ServerHandler;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MainProgram {
    public static void main(String[] args) throws IOException, SQLException, NoSuchAlgorithmException {
        ServerHandler server = new ServerHandler();
        server.execute();
    }

    /* Inizializzazione tipologia ev
    db.insertEventTypology(new TipologiaEventoAvverso("Mal di testa"));
        db.insertEventTypology(new TipologiaEventoAvverso("Febbre"));
        db.insertEventTypology(new TipologiaEventoAvverso("Dolori muscolari e articolari"));
        db.insertEventTypology(new TipologiaEventoAvverso("Linfoadenopatia"));
        db.insertEventTypology(new TipologiaEventoAvverso("Tachicardia"));
        db.insertEventTypology(new TipologiaEventoAvverso("Crisi ipertensiva"));
     */

}
