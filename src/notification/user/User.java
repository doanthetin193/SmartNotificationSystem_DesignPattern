package notification.user;

import notification.event.NotificationEvent;
import notification.event.NotificationType;

import java.util.List;

public interface User {
    void update(NotificationEvent event);
    String getName();
    List<NotificationType> getInterests();
}