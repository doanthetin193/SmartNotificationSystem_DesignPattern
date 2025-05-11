package notification.channel;

public class EmailChannel implements NotificationChannel {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi qua Email đến " + recipient + ": " + message);
    }
}