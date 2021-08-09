package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * @author Tornaghi Omar
 * @version 1.0
 */
public class CheckEmailRequest extends Packet{
    private static final long serialVersionUID = 7516262597610827033L;
    private String email;

    public CheckEmailRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
