package prg.lab.main.Services.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import prg.lab.main.Services.EmailService.EmailContentProvider.IEmailContentProvider;
import prg.lab.main.Util.Email;

public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String recipient, IEmailContentProvider contentProvider) {
        Email emailToSend = new Email();
        emailToSend.setTo(recipient);
        emailToSend.setSubject(contentProvider.getSubject());
        emailToSend.setBody(contentProvider.getBody());
        sendEmail(emailToSend);
    }

    public void sendEmail(Email email) {
        if (email == null || email.getTo() == null || email.getTo().isEmpty() || 
            email.getSubject() == null || email.getSubject().isEmpty() || 
            email.getBody() == null || email.getBody().isEmpty()) {
            throw new IllegalArgumentException("Os parâmetros de e-mail não podem ser nulos ou vazios");
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("meritocracyPRG@gmail.com");
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(), true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar e-mail", e);
        }
    }

}
