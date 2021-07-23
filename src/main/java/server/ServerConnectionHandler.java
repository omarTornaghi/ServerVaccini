package server;

import database.DBHelper;
import datatypes.Vaccinato;
import datatypes.Vaccinazione;
import datatypes.protocolmessages.*;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.sql.SQLException;

public class ServerConnectionHandler extends IoHandlerAdapter
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
        /* OPERAZIONE LIBERA */
        if(pacchetto instanceof RegistrationCVRequest){
            RegistrationCVRequest req = (RegistrationCVRequest) pacchetto;
            try {
                session.write(new RegistrationCVResponse(db.insertCV(req.getCv())));
            }
            catch(SQLException ex){ session.write(new RegistrationCVResponse(false)); }
            return;
        }
        /* OPERAZIONE LIBERA */
        if(pacchetto instanceof RegistrationVaccinatedRequest){
            RegistrationVaccinatedRequest req = (RegistrationVaccinatedRequest) pacchetto;
            Vaccinazione v = req.getVaccinazione();
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
        /* OPERAZIONE LIBERA */
        if(pacchetto instanceof UserRegistrationRequest){
            UserRegistrationRequest req = (UserRegistrationRequest) pacchetto;
            UserRegistrationResponse response;
            try{
                boolean esito = db.registerUser(req.getVaccinato(), req.getKey());
                if(esito) setClientAuthenticated(session);
                response = new UserRegistrationResponse(esito);
            }
            catch (SQLException ex){ response = new UserRegistrationResponse(false); }
            session.write(response);
            return;
        }
        /* OPERAZIONE LIBERA */
        if(pacchetto instanceof UserLoginRequest){
            UserLoginRequest req = (UserLoginRequest) pacchetto;
            try{
                Vaccinato v = db.login(req.getUsername(), req.getPassword());
                if(v != null){
                    session.write(new UserLoginResponse(true, v));
                    setClientAuthenticated(session);
                }
                else session.write(new UserLoginResponse(false));
            }
            catch (SQLException ex) { session.write(new UserLoginResponse(false)); }
            return;
        }
        /* OPERAZIONE VINCOLATA A LOGIN */
        if(pacchetto instanceof RegistrationEVRequest){
            if(!isClientAuthenticated(session)) {
                session.write(new RegistrationEVResponse(false));
                return;
            }
            RegistrationEVRequest req = (RegistrationEVRequest) pacchetto;
            RegistrationEVResponse response;
            try{
                boolean esito = db.insertEvent(req.getEventoAvverso(), req.getVaccinato());
                response = new RegistrationEVResponse(esito);
            }
            catch(SQLException ex){ response = new RegistrationEVResponse(false); }
            session.write(response);
            return;
        }
    }

    private void setClientAuthenticated(IoSession session){
        session.setAttribute("login", true);
    }

    private boolean isClientAuthenticated(IoSession session){
        return session != null && session.getAttribute("login") != null && (boolean) session.getAttribute("login");
    }

    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }
}
