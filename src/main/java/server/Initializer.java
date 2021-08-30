package server;

import database.DBHelper;

import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe per inizializzare i vari componenti
 * @author Tornaghi Omar
 * @version 1.0
 */
public class Initializer {
    /**
     * Inizializza il software
     * @return true se la procedura termina correttamente, false altrimenti
     * @throws IOException eccezioni input/output
     */
    public static boolean initialize() throws IOException {
        boolean inizializzazioneCompletata;
        String user;
        String password;
        String host;
        String nomeDB;

        final String directoryPath = "config";
        final String filePath = directoryPath + "/dbInitialized.config";
        File file = new File(filePath);
        if(!file.exists()) {
            try {
                Scanner in = new Scanner(System.in);
                System.out.print("User: ");
                user = in.next();
                System.out.print("Password: ");
                password = in.next();
                System.out.print("Host: ");
                host = in.next();
                System.out.print("Nome del database: ");
                nomeDB = in.next();
                host = host.toLowerCase(Locale.ROOT);
                nomeDB = nomeDB.toLowerCase(Locale.ROOT);
                inizializzazioneCompletata = DBHelper.getInstance().initialize(user, password, host, nomeDB);
                if(inizializzazioneCompletata){
                    File directory = new File(directoryPath);
                    boolean created = true;
                    if(!directory.exists()) created = directory.mkdir();
                    created = created && file.createNewFile();
                    if (!created) {
                        System.out.println("Impossibile creare file di inizializzazione db");
                        return false;
                    }
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                    writer.write(user);
                    writer.newLine();
                    writer.write(password);
                    writer.newLine();
                    writer.write(host);
                    writer.newLine();
                    writer.write(nomeDB);
                    writer.newLine();
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    writer.write("Initialized at: " + timestamp.toString());
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
            BufferedReader br = new BufferedReader(new FileReader(file));
            user = br.readLine();
            password = br.readLine();
            host = br.readLine();
            nomeDB = br.readLine();
            inizializzazioneCompletata = DBHelper.getInstance().connect(user,password,host,nomeDB);
        }
        if(inizializzazioneCompletata)
            System.out.println("Procedura di inizializzazione terminata correttamente");
        return inizializzazioneCompletata;
    }





}
