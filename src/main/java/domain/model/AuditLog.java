package domain.model;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Registro de auditoria inmutable. No genera identificadores ni marcas de tiempo:
 * quien crea el registro debe proveerlos, asi el modelo de dominio queda determinista.
 */
public record AuditLog(
    @NotNull UUID id,
    @NotNull UUID transactionId,
    @NotNull AuditLogType type,
    @NotNull String entityType,
    @NotNull UUID entityId,
    @NotNull String operation,
    String oldValue,
    String newValue,
    @NotNull LocalDateTime timestamp,
    String additionalInfo
) {
    public enum AuditLogType {
        TRANSACTION, SECURITY, SYSTEM
    }

    public AuditLog {
        if (id == null) {
            throw new IllegalArgumentException("El identificador del registro de auditoria es obligatorio");
        }
        if (transactionId == null) {
            throw new IllegalArgumentException("El identificador de la transaccion es obligatorio");
        }
        if (type == null) {
            throw new IllegalArgumentException("El tipo de registro de auditoria es obligatorio");
        }
        if (entityType == null || entityType.isBlank()) {
            throw new IllegalArgumentException("El tipo de entidad es obligatorio");
        }
        if (entityId == null) {
            throw new IllegalArgumentException("El identificador de la entidad es obligatorio");
        }
        if (operation == null || operation.isBlank()) {
            throw new IllegalArgumentException("La operacion es obligatoria");
        }
        if (timestamp == null) {
            throw new IllegalArgumentException("La marca de tiempo es obligatoria");
        }
    }

    public static AuditLog createTransactionLog(
        UUID id,
        UUID transactionId,
        String entityType,
        UUID entityId,
        String operation,
        String oldValue,
        String newValue,
        LocalDateTime timestamp,
        String additionalInfo
    ) {
        return new AuditLog(
            id,
            transactionId,
            AuditLogType.TRANSACTION,
            entityType,
            entityId,
            operation,
            oldValue,
            newValue,
            timestamp,
            additionalInfo
        );
    }

    public static AuditLog createSecurityLog(
        UUID id,
        UUID transactionId,
        UUID entityId,
        String operation,
        LocalDateTime timestamp,
        String additionalInfo
    ) {
        return new AuditLog(
            id,
            transactionId,
            AuditLogType.SECURITY,
            "Account",
            entityId,
            operation,
            null,
            null,
            timestamp,
            additionalInfo
        );
    }

    public boolean isSuccessful() {
        return additionalInfo == null || !additionalInfo.contains("FAILED");
    }
}