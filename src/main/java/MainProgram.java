import database.Crypter;
import database.DBHelper;
import datatypes.*;
import server.Initializer;
import server.ServerHandler;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class MainProgram {
    public static void main(String[] args) throws Exception {
        boolean inizializzazione = Initializer.initialize();
        if(!inizializzazione){
            System.err.println("Impossibile inizializzare le risorse");
            return;
        }
        ServerHandler server = new ServerHandler();
        server.execute();
    }


}
