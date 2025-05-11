# Báo cáo: Hệ thống Thông báo Thông minh cho Nền tảng Học Trực Tuyến

## 1. Giới thiệu
Hệ thống Thông báo Thông minh được thiết kế để hỗ trợ việc gửi thông báo tự động đến các loại người dùng (*Sinh viên*, *Giảng viên*, *Quản trị viên*) trên nền tảng học trực tuyến. Hệ thống cho phép tùy chỉnh thông báo theo loại sự kiện (**Cập nhật khóa học**, **Hạn nộp bài tập**, **Cảnh báo hệ thống**) và sử dụng nhiều kênh gửi (**Email**, **SMS**, **Push Notification**, **Twilio**). Báo cáo này trình bày cấu trúc và hoạt động của hệ thống thông qua biểu đồ UML, kèm theo phân tích cách các mẫu thiết kế được áp dụng để đáp ứng **8 yêu cầu** của đề bài.

![Biểu đồ UML](https://uml.planttext.com/plantuml/png/rLRDQjj04BuBz0vBBhKXDlHgWgc9rHAmdP1r3gMKi9R6ye8q4hih5vFc4Ve8VO6UscF9CT3to9kKrVzAifhqr5eNC_FjDtyxC_j6AYPK4WQsPLklo1os785mL22sp0LfM-L_ieeKttANAHxX-OuXGa3kR8ledmJq1Y58oNoO4Ae4H_-K271vp05L8HfEo3xYdctTQtiLzOq4KN8biSSK362V7gnRTRIev4Z1FgWL2s4mB4nKyWjD3r99hLnmgSxgNEj33DFSaJ9mDs1IWYGCFGAOXBQLIipG8znol_1sn54gXYvCEa0vn8CjImALvwm1p8IvrtTICSNT-ypzZ5XxVunQu-O6UmjjyCiMtErnognMEnDfESf4g4FSI89ESQviN8tAUurf-boTquXbThHLuPOdWsuFJad3-30FI1UuXIx2ER-ykQRExStLVBPsCj6CqejtgwMpMj_EdTbySR7A5VGzNJlBszd2kLuRoLf_uW6FA8WzTy7e5RgaWtWN8QoIS0EYQfdSr_C8rT6-QLVYBNDL90wbYV8UkGAOWZgqI6Tticf9FcAA7gI2iAowJ3RI5Nm3GkPDfdjvMeHQQ0U9r55gdTc_2VuPWzhyuNa30IC5H71_f_B3X935qyCNbw3_w_lJmrTElCUVw1FlwU478qeyViF6i21AHEWNLeSP3w2NSXjveIhnwYs0B6nSyL5VVvKJ93TzV4eLpVktNQjdYKZq7VdtVPrv8KSkbM3_XRjEo7XGMuSrrLKYTzqQkgJTYdnYBEdCOx6gbk98rKV9n9mi1iEISIpdGCWu3fZmPP6w3n_D6ChD9o188vKx7alRwjhrPv_7OoFS7bWjz1vKbOOUK3CbsktqlP1HP2rO5rMNlIurhfNcw1XgqMWq9ISXmyD9zYIPL1DCoEQX_26G7Nfzqla-cX1p-MkELerBXaPLRQiOC7qsRAlMtVr0ixNwiTdNjMJ6usd7kcu_33hvCx2-U2izgRUH81v8xYE9cL8WKDfMDSD9j3twzg4S_G1KXEGgEwatUZ6a-qXeMkoCv78ZGUn1l9J363DK9nJymqj9yeHc94Lsbw1saVSSgdPZfA-K_59r4hm1z98m-0q)

## 2. Tổng quan về hệ thống và biểu đồ UML
Hệ thống Thông báo Thông minh được xây dựng để gửi thông báo tùy chỉnh đến các loại người dùng (*Sinh viên*, *Giảng viên*, *Quản trị viên*) trên nền tảng học trực tuyến. Hệ thống hỗ trợ **ba loại thông báo** (*Cập nhật khóa học*, *Hạn nộp bài tập*, *Cảnh báo hệ thống*) và sử dụng nhiều kênh gửi (**Email**, **SMS**, **Push Notification**, **Twilio**). Biểu đồ UML cung cấp cái nhìn tổng quan về cấu trúc tĩnh của hệ thống, bao gồm các **lớp**, **interface**, **mối quan hệ** giữa chúng, và các **mẫu thiết kế** được áp dụng (*Singleton*, *Observer*, *Strategy*, *Factory Method*, *Adapter*).

**Biểu đồ UML** được chia thành các thành phần chính:
- **Interfaces**: `NotificationChannel` và `User` định nghĩa các giao diện trừu tượng.
- **Classes**: Các lớp cụ thể như `Student`, `Instructor`, `NotificationSystem`, v.v.
- **Enum**: `NotificationType` định nghĩa các loại thông báo.
- **Mối quan hệ**: Mô tả cách các lớp tương tác (*kế thừa*, *triển khai*, *liên kết*).
- **Mẫu thiết kế**: Được ghi chú để làm rõ cách hệ thống giải quyết các yêu cầu.

## 3. Chi tiết từng thành phần trong biểu đồ UML
### 3.1. Interface và Enum
#### Interface NotificationChannel
- **Mô tả**: Định nghĩa giao diện cho các kênh gửi thông báo với phương thức `send(message: String, recipient: String)`.
- **Vai trò**: Là trung tâm của mẫu **Strategy Pattern**, cho phép thay đổi kênh gửi tại runtime.
- **Mối quan hệ**: Được triển khai bởi `EmailChannel`, `PushChannel`, `SMSChannel`, và `TwilioSMSAdapter`.

#### Interface User
- **Mô tả**: Định nghĩa giao diện cho người dùng với các phương thức:
  - `update(event: NotificationEvent)`: Xử lý thông báo khi nhận được sự kiện.
  - `getName()`: Lấy tên người dùng.
  - `getInterests()`: Lấy danh sách loại thông báo mà người dùng quan tâm.
- **Vai trò**: Là trung tâm của mẫu **Observer Pattern**, cho phép các người dùng nhận thông báo từ `NotificationSystem`.
- **Mối quan hệ**: Được triển khai bởi `Student`, `Instructor`, và `Administrator`.

#### Enum NotificationType
- **Mô tả**: Định nghĩa **ba loại thông báo**: `COURSE_UPDATE`, `ASSIGNMENT_DEADLINE`, `SYSTEM_ALERT`.
- **Vai trò**: Cung cấp các giá trị cố định cho loại thông báo, được sử dụng trong `NotificationEvent` và `NotificationFactory`.

### 3.2. Các lớp chính
#### Lớp NotificationConfig
- **Mô tả**: Quản lý cấu hình hệ thống với:
  - `instance: NotificationConfig`: Instance duy nhất.
  - `defaultChannel: NotificationChannel`: Kênh gửi mặc định.
  - `getInstance()`: Truy cập instance duy nhất.
  - `getDefaultChannel()`, `setDefaultChannel()`: Quản lý kênh mặc định.
- **Mẫu thiết kế**: **Singleton Pattern**, đảm bảo chỉ có một instance cấu hình.
- **Ứng với yêu cầu**:
  - *Yêu cầu 7*: Cấu hình hệ thống được quản lý duy nhất.
  - *Yêu cầu 3*: Cung cấp kênh mặc định (mặc định là `EmailChannel`).
- **Mối quan hệ**: Liên kết với một `NotificationChannel` (mối quan hệ `defaultChannel`).

#### Lớp NotificationEvent
- **Mô tả**: Đại diện cho một sự kiện thông báo với:
  - `type: NotificationType`: Loại thông báo.
  - `message: String`: Nội dung thông báo.
  - `getType()`, `getMessage()`: Truy xuất thông tin.
- **Vai trò**: Đóng vai trò là dữ liệu được gửi từ `NotificationSystem` đến `User`.
- **Mối quan hệ**: Liên kết với `NotificationType` để xác định loại thông báo.

#### Lớp NotificationFactory
- **Mô tả**: Tạo nội dung thông báo với phương thức tĩnh `createNotificationMessage(type: NotificationType)`.
- **Mẫu thiết kế**: **Factory Method Pattern**, tạo nội dung thông báo một cách nhất quán.
- **Ứng với yêu cầu**:
  - *Yêu cầu 2*: Tạo nội dung cho ba loại thông báo.
  - *Yêu cầu 8*: Dễ mở rộng khi thêm loại thông báo mới.
- **Mối quan hệ**: Sử dụng `NotificationType` để ánh xạ loại thông báo với nội dung.

#### Lớp NotificationSystem
- **Mô tả**: Quản lý danh sách người dùng và gửi thông báo với:
  - `subscribers: List<User>`: Danh sách người dùng.
  - `subscribe(user: User)`, `unsubscribe(user: User)`: Quản lý người dùng.
  - `notify(event: NotificationEvent)`: Gửi thông báo đến người dùng quan tâm (dựa trên `getInterests()`).
- **Mẫu thiết kế**: **Observer Pattern**, cho phép gửi thông báo đồng loạt.
- **Ứng với yêu cầu**:
  - *Yêu cầu 4*: Gửi thông báo đến người dùng quan tâm loại sự kiện đó.
  - *Yêu cầu 8*: Dễ mở rộng bằng cách thêm người dùng mới.
- **Mối quan hệ**: Liên kết với nhiều `User` (mối quan hệ `subscribers`).

#### Lớp Student, Instructor, Administrator
- **Mô tả**: Đại diện cho các loại người dùng với:
  - `name: String`: Tên người dùng.
  - `channel: NotificationChannel`: Kênh gửi thông báo.
  - `interests: List<NotificationType>`: Danh sách loại thông báo quan tâm.
  - `update(event: NotificationEvent)`: Xử lý thông báo theo cách riêng.
  - `setChannel(channel: NotificationChannel)`: Thay đổi kênh gửi.
- **Ứng với yêu cầu**:
  - *Yêu cầu 1*: Hỗ trợ ba loại người dùng.
  - *Yêu cầu 3*: Sử dụng kênh gửi linh hoạt.
  - *Yêu cầu 4*: Lọc dựa trên `interests`.
  - *Yêu cầu 6*: Tùy chỉnh nội dung thông báo.
- **Mối quan hệ**: Liên kết với một `NotificationChannel` và triển khai `User`.

#### Lớp EmailChannel, PushChannel, SMSChannel
- **Mô tả**: Triển khai `NotificationChannel` để gửi thông báo qua **Email**, **Push**, hoặc **SMS**.
- **Ứng với yêu cầu**:
  - *Yêu cầu 3*: Hỗ trợ nhiều kênh gửi.
  - *Yêu cầu 8*: Dễ mở rộng bằng cách thêm kênh mới.

#### Lớp TwilioSMSAdapter và TwilioService
- **Mô tả**:
  - `TwilioService`: Giả lập API bên ngoài với `sendSMS(phoneNumber: String, messageContent: String)`.
  - `TwilioSMSAdapter`: Triển khai `NotificationChannel`, bọc `TwilioService` để tích hợp vào hệ thống.
- **Mẫu thiết kế**: **Adapter Pattern**, chuyển đổi giao diện của `TwilioService` thành `NotificationChannel`.
- **Ứng với yêu cầu**:
  - *Yêu cầu 5*: Tích hợp dịch vụ bên ngoài (*Twilio*).
  - *Yêu cầu 8*: Dễ thêm dịch vụ mới.
- **Mối quan hệ**: `TwilioSMSAdapter` liên kết với một `TwilioService`.

#### Lớp Main
- **Mô tả**: Lớp chính để chạy chương trình, khởi tạo và điều khiển luồng thực thi.
- **Mối quan hệ**: Sử dụng `NotificationSystem`, `Student`, `Instructor`, `Administrator`, `TwilioSMSAdapter`, `PushChannel`.

## 4. Mối quan hệ giữa các lớp
- **Kế thừa và triển khai**:
  - `NotificationChannel` được triển khai (`<|..`) bởi `EmailChannel`, `PushChannel`, `SMSChannel`, và `TwilioSMSAdapter`.
  - `User` được triển khai (`<|..`) bởi `Student`, `Instructor`, và `Administrator`.
- **Liên kết**:
  - `NotificationSystem` liên kết với nhiều `User` (mối quan hệ `subscribers`).
  - `NotificationConfig` liên kết với một `NotificationChannel` (mối quan hệ `defaultChannel`).
  - `Student`, `Instructor`, `Administrator` liên kết với một `NotificationChannel`.
  - `TwilioSMSAdapter` liên kết với một `TwilioService`.
  - `NotificationEvent` liên kết với một `NotificationType`.
- **Sử dụng**:
  - `Main` sử dụng (`..>`) `NotificationSystem`, `Student`, `Instructor`, `Administrator`, `TwilioSMSAdapter`, `PushChannel`.
  - `NotificationFactory` sử dụng `NotificationType`.

## 5. Luồng hoạt động của hệ thống (dựa trên Main)
Lớp `Main` là điểm khởi đầu, mô phỏng cách hệ thống hoạt động:
1. **Khởi tạo hệ thống**: Tạo `NotificationSystem` để quản lý người dùng.
2. **Tạo người dùng**:
   - `Student("Nguyen Van A")`: Quan tâm đến `COURSE_UPDATE` và `ASSIGNMENT_DEADLINE`.
   - `Instructor("Tran Thi B")`: Quan tâm đến `COURSE_UPDATE` và `SYSTEM_ALERT`.
   - `Administrator("Le Van C")`: Quan tâm đến `SYSTEM_ALERT`.
3. **Đăng ký người dùng**: Thêm tất cả người dùng vào `NotificationSystem`.
4. **Thay đổi kênh gửi**:
   - Sinh viên dùng `TwilioSMSAdapter`.
   - Giảng viên dùng `PushChannel`.
   - Quản trị viên dùng kênh mặc định (`EmailChannel`).
5. **Gửi thông báo**:
   - `ASSIGNMENT_DEADLINE`: Chỉ sinh viên nhận được.
   - `SYSTEM_ALERT`: Giảng viên và quản trị viên nhận được.

**Kết quả đầu ra**:
```
Gửi thông báo ASSIGNMENT_DEADLINE:
Twilio API: Gửi SMS đến Nguyen Van A: Sinh viên Nguyen Van A nhận thông báo: Hạn nộp bài tập: 12/05/2025.

Gửi thông báo SYSTEM_ALERT:
Gửi qua Push Notification đến Tran Thi B: Giảng viên Tran Thi B nhận thông báo chi tiết: Cảnh báo: Hệ thống bảo trì lúc 2:00 AM.
Gửi qua Email đến Le Van C: Quản trị viên Le Van C nhận cảnh báo hệ thống: Cảnh báo: Hệ thống bảo trì lúc 2:00 AM.
```

## 6. Cách các mẫu thiết kế đáp ứng yêu cầu
| **Yêu cầu** | **Mẫu thiết kế** | **Giải thích** |
|-------------|------------------|---------------|
| 1. Loại người dùng | - | `Student`, `Instructor`, `Administrator` triển khai `User` với hành vi tùy chỉnh. |
| 2. Loại thông báo | Factory Method | `NotificationFactory` tạo nội dung thông báo dựa trên `NotificationType`. |
| 3. Kênh gửi | Strategy | `NotificationChannel` và các lớp con cho phép thay đổi kênh gửi linh hoạt. |
| 4. Gửi theo loại sự kiện | Observer | `NotificationSystem` lọc người dùng dựa trên `getInterests()` và gửi thông báo. |
| 5. Chuyển đổi dịch vụ | Adapter | `TwilioSMSAdapter` tích hợp *Twilio* vào hệ thống. |
| 6. Tùy chỉnh hành vi | - | Hành vi được tùy chỉnh qua `update()` trong các lớp người dùng. |
| 7. Cấu hình Singleton | Singleton | `NotificationConfig` đảm bảo một instance duy nhất. |
| 8. Dễ mở rộng | Strategy, Factory Method | Dễ thêm kênh, người dùng, hoặc thông báo mới. |

## 7. Kết luận
Biểu đồ UML cung cấp cái nhìn toàn diện về **Hệ thống Thông báo Thông minh**. Hệ thống đáp ứng đầy đủ **8 yêu cầu** của đề bài, với tính linh hoạt và khả năng mở rộng cao nhờ các mẫu thiết kế **Singleton**, **Observer**, **Strategy**, **Factory Method**, và **Adapter**. Các mẫu này được áp dụng hiệu quả, đảm bảo hệ thống dễ bảo trì và tích hợp với dịch vụ bên ngoài. Luồng hoạt động trong `Main` minh chứng cho tính đúng đắn của thiết kế, với kết quả đầu ra phản ánh chính xác logic lọc và tùy chỉnh thông báo.
