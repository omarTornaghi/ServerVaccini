package server;

import database.DBHelper;
import datatypes.Vaccinazione;
import datatypes.protocolmessages.*;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.sql.SQLException;

public class ConnectionHandler extends IoHandlerAdapter
{
    private final DBHelper db = DBHelper.getInstance();
    @Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception
    {
        Packet pacchetto = (Packet) message;
        if(pacchetto instanceof RegistrationCVRequest){
            RegistrationCVRequest req = (RegistrationCVRequest) pacchetto;
            try {
                session.write(new RegistrationCVResponse(db.insertCV(req.getCv())));
            }
            catch(SQLException ex){ session.write(new RegistrationCVResponse(false)); }
            return;
        }
        if(pacchetto instanceof RegistrationVaccinatedRequest){
            RegistrationVaccinatedRequest req = (RegistrationVaccinatedRequest) pacchetto;
            Vaccinazione v = req.getVaccinazione();
            if(v.getId() != null)
                v.setId(Prettier.normalizeKey(v.getId()));
            String codVaccinazione;
            try {
                    codVaccinazione = db.insertVaccination(v);
            }
            catch(SQLException ex){ codVaccinazione = null; }
            RegistrationVaccinatedResponse response;
            if(codVaccinazione == null)
                response = new RegistrationVaccinatedResponse(false,null);
            else
                response = new RegistrationVaccinatedResponse(true, Prettier.makeReadable(codVaccinazione));
            session.write(response);
            return;
        }
        if(pacchetto instanceof LoginRequest){
            return;
        }
    }

    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }
}
