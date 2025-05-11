package notification.system;

import notification.event.NotificationEvent;
import notification.user.User;

import java.util.ArrayList;
import java.util.List;

public class NotificationSystem {
    private List<User> subscribers;

    public NotificationSystem() {
        this.subscribers = new ArrayList<>();
    }

    public void subscribe(User user) {
        subscribers.add(user);
    }

    public void unsubscribe(User user) {
        subscribers.remove(user);
    }

    public void notify(NotificationEvent event) {
        for (User user : subscribers) {
            if (user.getInterests().contains(event.getType())) { // Chỉ gửi thông báo đến người dùng quan tâm
                user.update(event);
            }
        }
    }
}