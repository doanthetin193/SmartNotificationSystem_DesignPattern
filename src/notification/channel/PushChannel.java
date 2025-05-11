package notification.channel;

public class PushChannel implements NotificationChannel {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi qua Push Notification đến " + recipient + ": " + message);
    }
}