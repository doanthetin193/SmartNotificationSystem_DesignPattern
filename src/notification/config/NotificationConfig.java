package notification.config;

import notification.channel.*;

public class NotificationConfig {
    private static NotificationConfig instance;
    private NotificationChannel defaultChannel;

    private NotificationConfig() {
        // Mặc định sử dụng EmailChannel
        this.defaultChannel = new EmailChannel();
    }

    public static synchronized NotificationConfig getInstance() {
        if (instance == null) {
            instance = new NotificationConfig();
        }
        return instance;
    }

    public NotificationChannel getDefaultChannel() {
        return defaultChannel;
    }

    public void setDefaultChannel(NotificationChannel channel) {
        this.defaultChannel = channel;
    }
}