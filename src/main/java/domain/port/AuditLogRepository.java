package domain.port;

import domain.model.AuditLog;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto para el registro de eventos de auditoría del sistema.
 * Define el contrato para persistir y consultar logs de auditoría.
 */
public interface AuditLogRepository {

    /**
     * Persiste un registro de auditoría de forma asíncrona.
     *
     * @param auditLog el registro de auditoría a guardar
     * @return el registro guardado con su ID asignado
     */
    CompletableFuture<AuditLog> save(AuditLog auditLog);

    /**
     * Busca todos los registros de auditoría asociados a una transacción específica.
     *
     * @param transactionId identificador de la transacción
     * @return lista de registros de auditoría para esa transacción
     */
    CompletableFuture<List<AuditLog>> findByTransactionId(UUID transactionId);

    /**
     * Busca todos los registros de auditoría asociados a una entidad específica.
     *
     * @param entityId identificador de la entidad
     * @return lista de registros de auditoría para esa entidad
     */
    CompletableFuture<List<AuditLog>> findByEntityId(UUID entityId);

    /**
     * Registra un fallo transaccional para auditoría.
     * Este método es utilizado por el manejador de rollback para registrar
     * cuando una operación no se completa exitosamente.
     *
     * @param operation nombre de la operación que falló
     * @param details detalles del fallo
     * @param cause excepción que causó el fallo (puede ser null)
     */
    void logFailure(String operation, String details, Exception cause);
}