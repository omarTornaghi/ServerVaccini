package server;

import database.DBHelper;
import datatypes.CentroVaccinale;
import datatypes.Vaccinato;
import datatypes.Vaccinazione;
import datatypes.protocolmessages.*;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import java.sql.SQLException;
import java.util.List;

public class ServerConnectionHandler extends IoHandlerAdapter
{
    private final DBHelper db = DBHelper.getInstance();

    @Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception
    {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception {
        Packet pacchetto = (Packet) message;
        /* OPERAZIONE LIBERA */
        if (pacchetto instanceof RegistrationCVRequest) {
            RegistrationCVRequest req = (RegistrationCVRequest) pacchetto;
            try {
                session.write(new RegistrationCVResponse(db.insertCV(req.getCv())));
            } catch (SQLException ex) {
                session.write(new RegistrationCVResponse(false));
            }
            return;
        }
        /* OPERAZIONE LIBERA */
        if (pacchetto instanceof RegistrationVaccinatedRequest) {
            RegistrationVaccinatedRequest req = (RegistrationVaccinatedRequest) pacchetto;
            Vaccinazione v = req.getVaccinazione();
            String codVaccinazione;
            try {
                codVaccinazione = db.insertVaccination(v);
            } catch (SQLException ex) {
                codVaccinazione = null;
            }
            RegistrationVaccinatedResponse response;
            if (codVaccinazione == null)
                response = new RegistrationVaccinatedResponse(false, null);
            else
                response = new RegistrationVaccinatedResponse(true, Prettier.makeReadable(codVaccinazione));
            session.write(response);
            return;
        }
        /* OPERAZIONE LIBERA */
        if (pacchetto instanceof UserRegistrationRequest) {
            UserRegistrationRequest req = (UserRegistrationRequest) pacchetto;
            UserRegistrationResponse response;
            try {
                boolean esito = db.registerUser(req.getVaccinato(), Prettier.normalizeKey(req.getKey()));
                if (esito) setClientAuthenticated(session, req.getVaccinato().getCodiceFiscale());
                response = new UserRegistrationResponse(esito);
            } catch (SQLException ex) {
                response = new UserRegistrationResponse(false);
            }
            session.write(response);
            return;
        }
        /* OPERAZIONE LIBERA */
        if (pacchetto instanceof UserLoginRequest) {
            UserLoginRequest req = (UserLoginRequest) pacchetto;
            try {
                Vaccinato v = db.login(req.getUsername(), req.getPassword());
                if (v != null) {
                    session.write(new UserLoginResponse(true, v));
                    setClientAuthenticated(session, v.getCodiceFiscale());
                } else session.write(new UserLoginResponse(false));
            } catch (SQLException ex) {
                session.write(new UserLoginResponse(false));
            }
            return;
        }
        /* OPERAZIONE VINCOLATA A LOGIN */
        if (pacchetto instanceof RegistrationEVRequest) {
            if (!isClientAuthenticated(session)) {
                session.write(new RegistrationEVResponse(false));
                return;
            }
            RegistrationEVRequest req = (RegistrationEVRequest) pacchetto;
            RegistrationEVResponse response;
            try {
                boolean esito = db.insertEvent(req.getEventoAvverso(), getAuthVaccinated(session));
                response = new RegistrationEVResponse(esito);
            } catch (SQLException ex) {
                response = new RegistrationEVResponse(false);
            }
            session.write(response);
            return;
        }
        /* OPERAZIONE LIBERA */
        if (pacchetto instanceof GetCVByNameRequest) {
            GetCVByNameRequest req = (GetCVByNameRequest) pacchetto;
            List<CentroVaccinale> list = null;
            boolean esito = false;
            try {
                list = db.getCV(req.getNome());
                esito = true;
            } catch (SQLException ignored) {
            } finally {
                session.write(new GetCVResponse(esito, list));
            }
            return;
        }
        /* OPERAZIONE LIBERA */
        if (pacchetto instanceof GetCVByMunicipalityTypologyRequest) {
            GetCVByMunicipalityTypologyRequest req = (GetCVByMunicipalityTypologyRequest) pacchetto;
            List<CentroVaccinale> list = null;
            boolean esito = false;
            try {
                list = db.getCV(req.getMunicipality(), req.getTypology());
                esito = true;
            } catch (SQLException ignored) {
            } finally {
                session.write(new GetCVResponse(esito, list));
            }
            return;
        }
        /* OPERAZIONE LIBERA */
        if (pacchetto instanceof GetAllCVRequest) {
            GetAllCVRequest req = (GetAllCVRequest) pacchetto;
            List<CentroVaccinale> list = null;
            boolean esito = false;
            try {
                list = db.getCV();
                esito = true;
            } catch (SQLException ignored) {
            } finally {
                session.write(new GetCVResponse(esito, list));
            }
            return;
        }
        /* OPERAZIONE LIBERA */
        if(pacchetto instanceof GetVaccinationByKeyRequest){
            GetVaccinationByKeyRequest req = (GetVaccinationByKeyRequest) pacchetto;
            try{
                session.write(new GetVaccinationByKeyResponse(true, db.getVaccinationById(Prettier.normalizeKey(req.getKey()))));
            }
            catch(SQLException sqlexcp){ session.write(new GetVaccinationByKeyResponse(false, null)); }
            return;
        }
        /* OPERAZIONE LIBERA */
        if (pacchetto instanceof GetVaccinesRequest) {
            try {
                session.write(new GetVaccinesResponse(true, db.getVaccines()));
            } catch (SQLException excp) {
                session.write(new GetVaccinesResponse(false, null));
            }
            return;
        }
        /* OPERAZIONE LIBERA */
        if(pacchetto instanceof GetEVTypologiesRequest){
            try{
                session.write(new GetEvTypologiesResponse(true, db.getEventTypes()));
            }
            catch(SQLException excp){
                session.write(new GetEvTypologiesResponse(false, null));
            }
            return;
        }
        /* OPERAZIONE LIBERA */
        if(pacchetto instanceof GetReportRequest){
            GetReportRequest req = (GetReportRequest) pacchetto;
            try{
                session.write(new GetReportResponse(true, db.generateReport(req.getCv())));
            }
            catch(SQLException excp){ new GetReportResponse(false, null); }
        }


    }
    private void setClientAuthenticated(IoSession session, String cf){
        try {
            session.setAttribute("vaccinato", db.getVaccinatedById(cf));
            session.setAttribute("login", true);
        }
        catch(SQLException sqlExcp){
            session.setAttribute("vaccinato", null);
        }
    }

    private boolean isClientAuthenticated(IoSession session){
        return session != null && session.getAttribute("login") != null && (boolean) session.getAttribute("login");
    }

    private Vaccinato getAuthVaccinated(IoSession session){
        if(session != null) return (Vaccinato) session.getAttribute("vaccinato"); else return null;
    }

    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception
    {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }
}
