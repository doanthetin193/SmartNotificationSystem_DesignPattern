package notification.channel;

public class SMSChannel implements NotificationChannel {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi qua SMS đến " + recipient + ": " + message);
    }
}