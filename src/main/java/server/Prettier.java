package server;

public class Prettier {
    public static String makeReadable(String key){
        if(key == null) return "";
        StringBuilder out = new StringBuilder();
        for(int i = 0; i< key.length(); i++){
            if(i != 0 && ((i) % 4) == 0) out.append("-");
            out.append(key.charAt(i));

        }
        return out.toString();
    }

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
