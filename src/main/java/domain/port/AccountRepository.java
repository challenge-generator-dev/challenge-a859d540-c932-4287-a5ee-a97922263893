package domain.port;

import domain.model.Account;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface AccountRepository {
    CompletableFuture<Optional<Account>> findById(UUID id);
    CompletableFuture<Account> save(Account account);
    CompletableFuture<Void> delete(UUID id);

    default CompletableFuture<Account> getById(UUID id) {
        return findById(id).thenApply(account -> account.orElseThrow(() ->
            new IllegalArgumentException("Account not found with id: " + id)
        ));
    }

    CompletableFuture<Optional<Account>> findByAccountNumber(String accountNumber);
}