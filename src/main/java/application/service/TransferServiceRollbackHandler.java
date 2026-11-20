// TODO(reto): archivo stubbeado; implementa los cuerpos.
package application.service;

import domain.port.AccountRepository;
import domain.port.AuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class TransferServiceRollbackHandler {

    private static final Logger log = LoggerFactory.getLogger(TransferServiceRollbackHandler.class);

    private final AccountRepository accountRepository;
    private final AuditLogRepository auditLogRepository;

    public TransferServiceRollbackHandler(AccountRepository accountRepository,
                                         AuditLogRepository auditLogRepository) {
        this.accountRepository = accountRepository;
        this.auditLogRepository = auditLogRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handlePartialFailure(String fromAccountId, String toAccountId, 
                                     BigDecimal amount, Exception cause) {
                                       throw new UnsupportedOperationException("TODO(reto): Transactional");
                                     }

    private void revertDebit(String accountId, BigDecimal amount) {
      throw new UnsupportedOperationException("TODO(reto): revertDebit");
    }

    private void logFailureForAudit(String fromAccountId, String toAccountId, 
                                    BigDecimal amount, Exception cause) {
                                      throw new UnsupportedOperationException("TODO(reto): logFailureForAudit");
                                    }
}