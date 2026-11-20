package domain.model;

public class NotificationException extends RuntimeException {
    private final String recipient;
    private final String notificationType;
    private final String errorDetails;

    public NotificationException(String recipient, String notificationType, String errorDetails) {
        super(String.format(
            "Failed to send %s notification to %s. Error: %s",
            notificationType, recipient, errorDetails
        ));
        this.recipient = recipient;
        this.notificationType = notificationType;
        this.errorDetails = errorDetails;
    }

    public NotificationException(String recipient, String notificationType, String errorDetails, Throwable cause) {
        super(String.format(
            "Failed to send %s notification to %s. Error: %s",
            notificationType, recipient, errorDetails
        ), cause);
        this.recipient = recipient;
        this.notificationType = notificationType;
        this.errorDetails = errorDetails;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public boolean isRetryable() {
        return errorDetails != null &&
               (errorDetails.contains("timeout") ||
                errorDetails.contains("connection refused") ||
                errorDetails.contains("temporary failure"));
    }
}