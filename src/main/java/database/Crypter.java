package database;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Classe utilitaria per cifrare password
 * @author Tornaghi Omar
 * @version 1.0
 */
public class Crypter {
    /**
     * Trasforma la stringa di input in sha256
     * @param base stringa da trasformare
     * @return stringa in sha256
     */
    public static String sha256(final String base) {
        if(base == null) return "";
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
