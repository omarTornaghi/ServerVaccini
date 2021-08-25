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
import java.util.Locale;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean inizializzazione = Initializer.initialize();
        while(!inizializzazione){
            System.err.println("Impossibile inizializzare le risorse");
            System.out.print("Riprovare?[S/N]");
            String c = scanner.next();
            if(c.toLowerCase(Locale.ROOT).equals("s"))
                inizializzazione = Initializer.initialize();
            else
                System.exit(0);
        }
        ServerHandler server = new ServerHandler();
        server.execute();
    }
}
