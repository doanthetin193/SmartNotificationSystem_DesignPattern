package notification.user;

import notification.channel.NotificationChannel;
import notification.config.NotificationConfig;
import notification.event.NotificationEvent;
import notification.event.NotificationType;

import java.util.Arrays;
import java.util.List;

public class Administrator implements User {
    private String name;
    private NotificationChannel channel;
    private List<NotificationType> interests;

    public Administrator(String name) {
        this.name = name;
        this.channel = NotificationConfig.getInstance().getDefaultChannel();
        this.interests = Arrays.asList(NotificationType.SYSTEM_ALERT);
    }

    @Override
    public void update(NotificationEvent event) {
        String message = "Quản trị viên " + name + " nhận cảnh báo hệ thống: " + event.getMessage();
        channel.send(message, name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<NotificationType> getInterests() {
        return interests;
    }

    public void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }
}