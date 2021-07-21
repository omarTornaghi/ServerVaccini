package datatypes.protocolmessages;

import java.io.Serializable;

public abstract class Packet implements Serializable {

    private static final long serialVersionUID = -2683731630367166754L;

    public abstract String getPacketName();
}
