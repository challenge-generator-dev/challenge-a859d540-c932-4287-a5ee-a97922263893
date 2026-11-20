// TODO(reto): archivo stubbeado; implementa los cuerpos.
package infrastructure.adapter;

import domain.model.AuditLog;
import domain.port.AuditLogRepository;
import infrastructure.client.JpaAuditLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Repository
public class AuditLogRepositoryImpl implements AuditLogRepository {

    private static final Logger log = LoggerFactory.getLogger(AuditLogRepositoryImpl.class);

    private final JpaAuditLogRepository jpaRepository;

    public AuditLogRepositoryImpl(JpaAuditLogRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public CompletableFuture<AuditLog> save(AuditLog auditLog) {
      throw new UnsupportedOperationException("TODO(reto): save");
    }

    @Override
    public CompletableFuture<java.util.List<AuditLog>> findByTransactionId(UUID transactionId) {
      throw new UnsupportedOperationException("TODO(reto): findByTransactionId");
    }

    @Override
    public CompletableFuture<java.util.List<AuditLog>> findByEntityId(UUID entityId) {
      throw new UnsupportedOperationException("TODO(reto): findByEntityId");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logFailure(String operation, String details, Exception cause) {
      throw new UnsupportedOperationException("TODO(reto): Transactional");
    }

    private AuditLog mapToAuditLog(infrastructure.client.AuditLogEntity entity) {
      throw new UnsupportedOperationException("TODO(reto): mapToAuditLog");
    }

    private infrastructure.client.AuditLogEntity mapToEntity(AuditLog auditLog) {
      throw new UnsupportedOperationException("TODO(reto): mapToEntity");
    }
}