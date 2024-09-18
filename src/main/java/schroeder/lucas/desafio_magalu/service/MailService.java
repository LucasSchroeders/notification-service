package schroeder.lucas.desafio_magalu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarMailTexto(String destination, String message){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setFrom("from@example.com");
            simpleMailMessage.setTo(destination);
            simpleMailMessage.setSubject("Teste");
            simpleMailMessage.setText(message);
            javaMailSender.send(simpleMailMessage);
            return "Email enviado";
        }catch (Exception e){
            return "Erro ao tentar email" + e.getLocalizedMessage();
        }
    }

}
