package notification.channel;

import notification.external.TwilioService;

public class TwilioSMSAdapter implements NotificationChannel {
    private TwilioService twilioService;

    public TwilioSMSAdapter() {
        this.twilioService = new TwilioService();
    }

    @Override
    public void send(String message, String recipient) {
        twilioService.sendSMS(recipient, message);
    }
}