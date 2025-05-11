import notification.channel.PushChannel;
import notification.channel.TwilioSMSAdapter;
import notification.event.NotificationEvent;
import notification.event.NotificationType;
import notification.factory.NotificationFactory;
import notification.system.NotificationSystem;
import notification.user.*;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo hệ thống thông báo
        NotificationSystem notificationSystem = new NotificationSystem();

        // Tạo người dùng
        User student = new Student("Nguyen Van A");
        User instructor = new Instructor("Tran Thi B");
        User admin = new Administrator("Le Van C");

        // Đăng ký người dùng vào hệ thống
        notificationSystem.subscribe(student);
        notificationSystem.subscribe(instructor);
        notificationSystem.subscribe(admin);

        // Thay đổi kênh gửi cho sinh viên và giảng viên
        ((Student) student).setChannel(new TwilioSMSAdapter());
        ((Instructor) instructor).setChannel(new PushChannel());

        // Gửi thông báo ASSIGNMENT_DEADLINE
        System.out.println("Gửi thông báo ASSIGNMENT_DEADLINE:");
        String message = NotificationFactory.createNotificationMessage(NotificationType.ASSIGNMENT_DEADLINE);
        NotificationEvent event = new NotificationEvent(NotificationType.ASSIGNMENT_DEADLINE, message);
        notificationSystem.notify(event);

        // Gửi thông báo SYSTEM_ALERT
        System.out.println("\nGửi thông báo SYSTEM_ALERT:");
        message = NotificationFactory.createNotificationMessage(NotificationType.SYSTEM_ALERT);
        event = new NotificationEvent(NotificationType.SYSTEM_ALERT, message);
        notificationSystem.notify(event);
    }
}