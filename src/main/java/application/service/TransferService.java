// TODO(reto): archivo stubbeado; implementa los cuerpos.
package application.service;

import domain.model.Account;
import domain.model.InsufficientFundsException;
import domain.model.NotificationException;
import domain.port.AccountRepository;
import domain.port.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {

    private static final Logger log = LoggerFactory.getLogger(TransferService.class);

    private final AccountRepository accountRepository;
    private final NotificationService notificationService;
    private final TransferServiceRollbackHandler rollbackHandler;

    public TransferService(AccountRepository accountRepository,
                          NotificationService notificationService,
                          TransferServiceRollbackHandler rollbackHandler) {
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
        this.rollbackHandler = rollbackHandler;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount)
            throws InsufficientFundsException, NotificationException {
              throw new UnsupportedOperationException("TODO(reto): Transactional");
            }

    private void sendTransferNotification(Account from, Account to, BigDecimal amount) {
      throw new UnsupportedOperationException("TODO(reto): sendTransferNotification");
    }
}