package server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Classe che gestisce l'avvio del server
 * @author Tornaghi Omar
 * @version 1.0
 */
public class ServerHandler
{
    /**
     * Porta in ascolto
     */
    private static final int PORT = 9123;

    /**
     * Codice di avvio del server
     * @throws IOException ecc.
     */
    public void execute() throws IOException
    {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

        acceptor.setHandler(  new ServerConnectionHandler() );

        acceptor.getSessionConfig().setReadBufferSize( 2048 );
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );

        acceptor.bind( new InetSocketAddress(PORT) );
        
        
    }
}
