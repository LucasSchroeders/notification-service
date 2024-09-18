package schroeder.lucas.desafio_magalu.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import schroeder.lucas.desafio_magalu.factory.sms.TwilioSms;



@Service
public class SmsService {
    @Value("${SMS_SENDER}")
    private String smsSender;

    public SmsService() {
    }

    public void enviarSms(String toPhoneNumber, String message){
        switch (smsSender){
            case "twilio":
                TwilioSms client = new TwilioSms();
                client.sendSms(toPhoneNumber, message);
            break;
            default:
                System.out.println("Não implementado");
        }
    }

}
