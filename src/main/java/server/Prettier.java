package server;

/**
 * Classe utilitaria per formattare e normalizzare l'identificativo di una vaccinazione
 * @author Tornaghi Omar
 * @version 1.0
 */
public class Prettier {
    /**
     * Permette di rendere più leggibile un identificativo
     * (es. 12345678 -> 1234-5678)
     * @param key stringa da convertire
     * @return stringa convertita
     */
    public static String makeReadable(String key){
        if(key == null) return "";
        StringBuilder out = new StringBuilder();
        for(int i = 0; i< key.length(); i++){
            if(i != 0 && ((i) % 4) == 0) out.append("-");
            out.append(key.charAt(i));

        }
        return out.toString();
    }

    /**
     * Normalizza la chiave data in input
     * (es. 1234-5678 -> 12345678)
     * @param key stringa da normalizzare
     * @return stringa normalizzata
     */
    public static String normalizeKey(String key){
        if(key == null) return "";
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            out.append(c == '-' ? "" : c);
        }
        return out.toString();
    }
}
