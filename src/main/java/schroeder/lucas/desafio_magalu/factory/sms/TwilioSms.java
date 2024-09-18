package schroeder.lucas.desafio_magalu.factory.sms;

import com.twilio.type.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.info.ProcessInfo;

public class TwilioSms implements Sms{
    private static Dotenv dotenv = Dotenv.load();
    private static final String ACCOUNT_SID = dotenv.get("TWILLIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = dotenv.get("TWILLIO_AUTH_TOKEN");
    private static final String FROM_PHONE_NUMBER = dotenv.get("TWILLIO_FROM_PHONE_NUMBER");

    public TwilioSms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public void sendSms(String toPhoneNumber, String message) {
//        if(!toPhoneNumber.contains("+")){
//            toPhoneNumber = "+" + toPhoneNumber;
//        }
        Message msg = Message.creator(
                        new com.twilio.type.PhoneNumber(toPhoneNumber),
                        new com.twilio.type.PhoneNumber(FROM_PHONE_NUMBER),
                        message)
                .create();
    }
}
