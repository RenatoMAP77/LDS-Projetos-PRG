package prg.lab.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


import prg.lab.main.Util.Email;



@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    

    
    public void enviarBonusEmail(String emailAlunoDestinatario, String nomeProfessor , Double quantidadeMoedas,String descricao) {
        Email emailToSend = new Email();
        emailToSend.setTo(emailAlunoDestinatario);
        emailToSend.setSubject("Você foi premiado!");
        emailToSend.setBody("<h1>Parabéns!</h1><p>Você foi premiado com " + quantidadeMoedas +
         " moedas pelo professor " + nomeProfessor + "</p>"
         + "<p>Descrição: " + descricao + "</p>");
        sendEmail(emailToSend);
       
    }
    public void enviarVantagemEmail(String emailAlunoDestinatario, String nomeVantagem , Double quantidadeMoedas , String codigoCupom) {
        Email emailToSend = new Email();
        emailToSend.setTo(emailAlunoDestinatario);
        emailToSend.setSubject("Você resgatou uma vantagem!");
        emailToSend.setBody("<h1>Parabéns!</h1><p>Você resgatou a vantagem " + nomeVantagem +
         " por " + quantidadeMoedas + " moedas</p>"
         + "<p>Seu código de cupom é: " + codigoCupom + "</p>");
        sendEmail(emailToSend);
       
    }

    private void sendEmail(Email email) {
        if (email == null ||email.getTo() == null || email.getTo().isEmpty() || email.getSubject() == null || email.getSubject().isEmpty() || email.getBody() == null || email.getBody().isEmpty()) {
            throw new IllegalArgumentException("Os parâmetros de e-mail não podem ser nulos ou vazios");
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("meritocracyPRG@gmail.com");
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getBody(), true); // true indica que o corpo do email é HTML

           

            mailSender.send(message);

            
            
        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar e-mail", e);
        }
    }
}
