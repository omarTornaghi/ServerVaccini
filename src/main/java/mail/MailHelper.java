package mail;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Classe utilitaria per l'invio di email
 * @author Tornaghi Omar
 * @version 1.0
 */
public class MailHelper {
    /**
     * Invia un email alla mail specificata da fromEmail
     * @param fromEmail email destinatario
     */
    public static void sendEmail(String fromEmail, String subject, String text){
        final String username = "progettolabbinsubria@gmail.com";
        final String password = "Vaccini2021!";
        Properties prop = new Properties();
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        //session.addProvider(new Provider(Provider.Type.TRANSPORT, "smtps", "com.sun.mail.smtp.SMTPSSLTransport", "", ""));
        System.out.println("Ho preso la sessione");
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(fromEmail)
            );
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
