package notification.user;

import notification.channel.NotificationChannel;
import notification.config.NotificationConfig;
import notification.event.NotificationEvent;
import notification.event.NotificationType;

import java.util.Arrays;
import java.util.List;

public class Student implements User {
    private String name;
    private NotificationChannel channel;
    private List<NotificationType> interests;

    public Student(String name) {
        this.name = name;
        this.channel = NotificationConfig.getInstance().getDefaultChannel();
        this.interests = Arrays.asList(NotificationType.COURSE_UPDATE, NotificationType.ASSIGNMENT_DEADLINE);
    }

    @Override
    public void update(NotificationEvent event) {
        String message = "Sinh viên " + name + " nhận thông báo: " + event.getMessage();
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