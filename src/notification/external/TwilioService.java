package notification.external;

public class TwilioService {
    public void sendSMS(String phoneNumber, String messageContent) {
        System.out.println("Twilio API: Gửi SMS đến " + phoneNumber + ": " + messageContent);
    }
}