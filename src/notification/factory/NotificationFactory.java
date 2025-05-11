package notification.factory;

import notification.event.NotificationType;

public class NotificationFactory {
    public static String createNotificationMessage(NotificationType type) {
        switch (type) {
            case COURSE_UPDATE:
                return "Khóa học đã được cập nhật.";
            case ASSIGNMENT_DEADLINE:
                return "Hạn nộp bài tập: 12/05/2025.";
            case SYSTEM_ALERT:
                return "Cảnh báo: Hệ thống bảo trì lúc 2:00 AM.";
            default:
                return "Thông báo không xác định.";
        }
    }
}