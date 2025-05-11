package notification.channel;

public interface NotificationChannel {
    void send(String message, String recipient);
}