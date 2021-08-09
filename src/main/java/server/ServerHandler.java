package server;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * Classe che gestisce l'avvio del server
 *
 * @author Tornaghi Omar
 * @version 1.0
 */
public class ServerHandler {
    /**
     * Porta in ascolto
     */
    private static final int PORT = 9123;

    private static SSLContext getSslContext() throws Exception {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(ServerHandler.class.getClassLoader().getResourceAsStream("tls/myKeyStore.jks"), "password".toCharArray());
            KeyManagerFactory keyManagerFactory =
                    KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keystore, "password".toCharArray());

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keystore);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(
                    keyManagerFactory.getKeyManagers(),
                    trustManagerFactory.getTrustManagers(),
                    new SecureRandom());
            return sslContext;
    }

    /**
     * Codice di avvio del server
     *
     * @throws Exception ecc.
     */
    public void execute() throws Exception {
        IoAcceptor acceptor = new NioSocketAcceptor();
        SslFilter sslFilter = new SslFilter(getSslContext());
        sslFilter.setNeedClientAuth(true);
        acceptor.getFilterChain().addFirst("sslFilter", sslFilter);
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

        acceptor.setHandler(new ServerConnectionHandler());

        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

        acceptor.bind(new InetSocketAddress(PORT));
        System.out.println("Server avviato");

    }
}
