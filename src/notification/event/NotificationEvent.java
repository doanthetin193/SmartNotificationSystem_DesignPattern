package notification.event;

public class NotificationEvent {
    private NotificationType type;
    private String message;

    public NotificationEvent(NotificationType type, String message) {
        this.type = type;
        this.message = message;
    }

    public NotificationType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}