package vermeg.com.applicationbancaire.camunda.delegate;

import jakarta.inject.Named;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Named("SendMailPaiementEncaissPHONE")
@Component
public class SendMailPaiementEncaissPHONE implements JavaDelegate {
    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger logger = LoggerFactory.getLogger(SendMailRejectToClient.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        try {
            String recipientAddress = (String) execution.getVariable("email");
            String nom = (String) execution.getVariable("nom");
            String prenom = (String) execution.getVariable("prenom");


            String msg = String.format(
                    "<html>" +
                            "<body>" +
                            "<h2>Notification de paiement réussi via EncaissPHONE</h2>" +
                            "<p>Bonjour %s %s,</p>" +
                            "<p>Nous vous informons que votre paiement via EncaissPHONE a été effectué avec succès.</p>" +
                            "<p>Si vous avez des questions ou des préoccupations, veuillez contacter notre service client.</p>" +
                            "<p>Cordialement,</p>" +
                            "<p>L'équipe de Vermeg</p>" +
                            "</body>" +
                            "</html>",
                    prenom, nom
            );

            logger.info("Recipient email: {}", recipientAddress);

            String taskName = execution.getCurrentActivityName();

            sendHtmlEmail(recipientAddress, "Notification from Validator: " + taskName, msg);
            logger.info("Email sent successfully to {}", recipientAddress);

        } catch (Exception e) {
            logger.error("Error sending email", e);
            throw e;
        }
    }

    private void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("dahmennourhene@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        javaMailSender.send(message);
    }
}
