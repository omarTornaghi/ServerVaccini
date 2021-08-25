package server;

import database.DBHelper;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;

/**
 * Classe per inizializzare i vari componenti
 * @author Tornaghi Omar
 * @version 1.0
 */
public class Initializer {
    public static boolean initialize() throws IOException {
        boolean inizializzazioneCompletata = true;
        String user;
        String password;
        String host;
        String nomeDB;
        //TODO Prendo in input questi campi
        user = "admin";
        password = "admin";
        host = "localhost";
        nomeDB = "VacciniDB";

        host = host.toLowerCase(Locale.ROOT);
        nomeDB = nomeDB.toLowerCase(Locale.ROOT);

        final String filePath = "config/dbInitialized.txt";
        File file = new File(filePath);
        if(!file.exists()) {
            try {
                inizializzazioneCompletata = DBHelper.getInstance().initialize(user, password, host, nomeDB);
                if(inizializzazioneCompletata){
                    boolean created = false;
                    created = file.createNewFile();
                    if (!created) {
                        System.out.println("Impossibile creare file di inizializzazione db");
                        return false;
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                    writer.write("DB Initialized by " + user);
                    writer.newLine();
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    writer.write(timestamp.toString());
                    writer.close();
                }
            } catch (SQLException sqlExcp) {
                System.out.println("Impossibile inizializzare il db");
                sqlExcp.printStackTrace();
                return false;
            }
        }
        else{
            //Il database esiste già, mi connetto
            inizializzazioneCompletata = DBHelper.getInstance().connect(user,password,host,nomeDB);
        }
        if(inizializzazioneCompletata)
            System.out.println("Procedura di inizializzazione terminata correttamente");
        return inizializzazioneCompletata;
    }





}
