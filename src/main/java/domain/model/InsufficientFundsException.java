package domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class InsufficientFundsException extends RuntimeException {
    private final UUID accountId;
    private final BigDecimal currentBalance;
    private final BigDecimal attemptedAmount;

    public InsufficientFundsException(UUID accountId, BigDecimal currentBalance, BigDecimal attemptedAmount) {
        super(String.format(
            "Insufficient funds in account %s. Current balance: %s, attempted amount: %s",
            accountId, currentBalance, attemptedAmount
        ));
        this.accountId = accountId;
        this.currentBalance = currentBalance;
        this.attemptedAmount = attemptedAmount;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public BigDecimal getAttemptedAmount() {
        return attemptedAmount;
    }

    public BigDecimal getDeficit() {
        return attemptedAmount.subtract(currentBalance);
    }
}