package datatypes.protocolmessages;
/**
 * Classe model per scambio di messaggi
 * richiede la verifica di un id
 * @author Tornaghi Omar
 * @version 1.0
 */
public class CheckUserIdRequest extends Packet{
    private static final long serialVersionUID = -1762669504550408640L;
    private String userId;

    public CheckUserIdRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getPacketName() {
        return this.getClass().toString();
    }
}
