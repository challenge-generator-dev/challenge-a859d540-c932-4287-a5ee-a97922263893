package domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Cuenta bancaria inmutable. Las transiciones devuelven una nueva instancia y reciben
 * la marca de tiempo de actualizacion desde fuera, para no depender del reloj del sistema.
 */
public record Account(
    @NotNull UUID id,
    @NotNull String accountNumber,
    @NotNull String ownerName,
    @NotNull @Positive BigDecimal balance,
    @NotNull AccountStatus status,
    @NotNull LocalDateTime createdAt,
    @NotNull LocalDateTime updatedAt
) {
    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }

    public Account {
        if (balance == null) {
            throw new IllegalArgumentException("El saldo es obligatorio");
        }
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty");
        }
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty");
        }
    }

    public Account withBalance(BigDecimal newBalance, LocalDateTime updatedAt) {
        return new Account(
            this.id,
            this.accountNumber,
            this.ownerName,
            newBalance,
            this.status,
            this.createdAt,
            updatedAt
        );
    }

    public Account block(LocalDateTime updatedAt) {
        return new Account(
            this.id,
            this.accountNumber,
            this.ownerName,
            this.balance,
            AccountStatus.BLOCKED,
            this.createdAt,
            updatedAt
        );
    }

    public Account activate(LocalDateTime updatedAt) {
        return new Account(
            this.id,
            this.accountNumber,
            this.ownerName,
            this.balance,
            AccountStatus.ACTIVE,
            this.createdAt,
            updatedAt
        );
    }
}