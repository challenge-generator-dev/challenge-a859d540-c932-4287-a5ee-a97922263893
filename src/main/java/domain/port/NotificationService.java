package domain.port;

import domain.model.NotificationException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface NotificationService {
    CompletableFuture<Void> sendNotification(
        String recipient,
        String subject,
        String message,
        NotificationType type,
        Map<String, String> metadata
    ) throws NotificationException;

    enum NotificationType {
        EMAIL, SMS, PUSH, IN_APP
    }

    default CompletableFuture<Void> sendEmail(String recipient, String subject, String message) {
        return sendNotification(recipient, subject, message, NotificationType.EMAIL, Map.of());
    }

    default CompletableFuture<Void> sendSms(String recipient, String message) {
        return sendNotification(recipient, "Bank Alert", message, NotificationType.SMS, Map.of());
    }
}