// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.Account;
import domain.model.InsufficientFundsException;
import domain.port.AccountRepository;
import infrastructure.client.JpaAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private static final Logger log = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    private final JpaAccountRepository jpaRepository;

    public AccountRepositoryImpl(JpaAccountRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public CompletableFuture<Optional<Account>> findById(UUID id) {
      throw new UnsupportedOperationException("TODO(reto): findById");
    }

    @Override
    public CompletableFuture<Account> save(Account account) {
      throw new UnsupportedOperationException("TODO(reto): save");
    }

    @Override
    public CompletableFuture<Void> delete(UUID id) {
      throw new UnsupportedOperationException("TODO(reto): delete");
    }

    @Override
    public CompletableFuture<Optional<Account>> findByAccountNumber(String accountNumber) {
      throw new UnsupportedOperationException("TODO(reto): findByAccountNumber");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account debit(String accountId, BigDecimal amount) throws InsufficientFundsException {
      throw new UnsupportedOperationException("TODO(reto): Transactional");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account credit(String accountId, BigDecimal amount) {
      throw new UnsupportedOperationException("TODO(reto): Transactional");
    }

    private Account mapToAccount(infrastructure.client.AccountEntity entity) {
      throw new UnsupportedOperationException("TODO(reto): mapToAccount");
    }

    private infrastructure.client.AccountEntity mapToEntity(Account account) {
      throw new UnsupportedOperationException("TODO(reto): mapToEntity");
    }
}