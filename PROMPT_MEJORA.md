# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener el proyecto completo y arrancable. Guarda los archivos que te devuelva en la
carpeta del repo, respetando exactamente las rutas.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/application/service/TransferService.java` — La pieza p1 (Definición de límites transaccionales y propagación, fase f1) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/infrastructure/adapter/AccountRepositoryImpl.java` — La pieza p2 (Implementación de propagación transaccional en adaptador de salida, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/application/service/TransferServiceRollbackHandler.java` — La pieza p3 (Manejo de rollback ante fallos parciales, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).
- `src/main/java/infrastructure/adapter/AuditLogRepositoryImpl.java` — La pieza p4 (Registro de fallos transaccionales para auditoría, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/domain/port/AccountRepository.java` — `Account.orElseThrow`: Se invoca `orElseThrow` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollo backend, Tecnología Java, Advanced L2

### Brecha de conocimiento
Implementa operaciones de negocio que modifican varias entidades garantizando la consistencia: límites transaccionales, propagación y manejo de rollback ante fallos.

### Reto
- Tema: Manejo de transacciones y consistencia de datos
- Seniority: advanced-l2
- Tipo: practical
- Título: Implementación de transacciones en un sistema de banca
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de límites transaccionales — objetivo: Definir los límites transaccionales para un caso de uso específico en el sistema de banca. — entregable (NO resolver): Documento que describe los límites transaccionales para el caso de uso seleccionado, anclado a la pieza `TransferService` y sus casos de aceptación.
- Fase 2: Implementación de la propagación de transacciones — objetivo: Implementar la propagación de transacciones en el caso de uso seleccionado. — entregable (NO resolver): Código que implementa la propagación de transacciones para el caso de uso seleccionado.
- Fase 3: Manejo de rollback ante fallos — objetivo: Implementar el manejo de rollback en caso de fallos durante las transacciones. — entregable (NO resolver): Código que implementa el manejo de rollback ante fallos durante las transacciones.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (entrega de archivos).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Entrega de archivos
Entregá cada archivo con su ruta completa, respetando exactamente la estructura de rutas
indicada por los marcadores: un bloque por archivo, precedido por su ruta. No generes ZIPs
ni archivos adjuntos.
La entrega debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: Entregá los archivos completos al finalizar. No preguntes si el usuario
quiere recibirlos. Simplemente entregalos; No debes desplegar en el chat el resumen de lo que arreglaste, solo entregá los archivos.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del último archivo entregado
No preguntes si el usuario quiere los archivos: simplemente entregalos siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: src/main/java/Application.java ===
package com.pragma;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import domain.port.NotificationService;

/*
 * Punto de entrada. Arquitectura hexagonal: domain (puertos y modelo),
 * application (casos de uso), infrastructure (adaptadores y persistencia).
 * El escaneo de componentes cubre los paquetes de cada capa; los repositorios
 * JPA y las entidades se registran desde infrastructure.client.
 */
@SpringBootApplication(scanBasePackages = {"com.pragma", "application", "domain", "infrastructure"})
@EnableJpaRepositories(basePackages = "infrastructure.client")
@EntityScan(basePackages = "infrastructure.client")
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*
     * Provider del puerto NotificationService. Sin este registro el contenedor
     * no resuelve la dependencia inyectada en TransferService.
     */
    @Bean
    NotificationService notificationService() {
        return (recipient, subject, message, type, metadata) -> {
            log.info("Notificacion a {} [{}]: {}", recipient, type, subject);
            return CompletableFuture.completedFuture(null);
        };
    }
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.13</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>
    <artifactId>reto</artifactId>
    <version>1.0.0</version>
    <name>reto</name>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/domain/model/Account.java ===
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

// === ARCHIVO: src/main/java/domain/model/InsufficientFundsException.java ===
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

// === ARCHIVO: src/main/java/domain/model/NotificationException.java ===
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

// === ARCHIVO: src/main/java/domain/model/AuditLog.java ===
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

// === ARCHIVO: src/main/java/domain/port/AccountRepository.java ===
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

// === ARCHIVO: src/main/java/domain/port/NotificationService.java ===
package domain.port;

import domain.model.NotificationException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface NotificationService {
    CompletableFuture<Void> sendNotification(
        String recipient,
        String subject,
        String message,
        NotificationType type,
        Map<String, String> metadata
    ) throws NotificationException;

    enum NotificationType {
        EMAIL, SMS, PUSH, IN_APP
    }

    default CompletableFuture<Void> sendEmail(String recipient, String subject, String message) {
        return sendNotification(recipient, subject, message, NotificationType.EMAIL, Map.of());
    }

    default CompletableFuture<Void> sendSms(String recipient, String message) {
        return sendNotification(recipient, "Bank Alert", message, NotificationType.SMS, Map.of());
    }
}

// === ARCHIVO: src/main/java/application/service/TransferService.java ===
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

// === ARCHIVO: src/main/java/infrastructure/adapter/AccountRepositoryImpl.java ===
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

// === ARCHIVO: src/main/java/application/service/TransferServiceRollbackHandler.java ===
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

// === ARCHIVO: src/main/java/infrastructure/adapter/AuditLogRepositoryImpl.java ===
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

// === ARCHIVO: src/main/java/domain/port/AuditLogRepository.java ===
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

// === ARCHIVO: src/main/java/infrastructure/client/JpaAccountRepository.java ===
package infrastructure.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAccountRepository extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findByAccountNumber(String accountNumber);

    @Modifying
    @Query("UPDATE AccountEntity a SET a.balance = a.balance - :amount WHERE a.id = :id AND a.balance >= :amount")
    int debit(@Param("id") UUID id, @Param("amount") BigDecimal amount);

    @Modifying
    @Query("UPDATE AccountEntity a SET a.balance = a.balance + :amount WHERE a.id = :id")
    int credit(@Param("id") UUID id, @Param("amount") BigDecimal amount);

    boolean existsByAccountNumber(String accountNumber);
}

// === ARCHIVO: src/main/java/infrastructure/client/AccountEntity.java ===
package infrastructure.client;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public enum AccountStatus {
        ACTIVE, BLOCKED, CLOSED
    }

    public AccountEntity() {
    }

    public AccountEntity(UUID id, String accountNumber, String ownerName, BigDecimal balance,
                         AccountStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

// === ARCHIVO: src/main/java/infrastructure/client/AuditLogEntity.java ===
package infrastructure.client;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_log")
public class AuditLogEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "transaction_id", nullable = false)
    private UUID transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AuditLogType type;

    @Column(name = "entity_type", nullable = false)
    private String entityType;

    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @Column(name = "operation", nullable = false)
    private String operation;

    @Column(name = "old_value", columnDefinition = "TEXT")
    private String oldValue;

    @Column(name = "new_value", columnDefinition = "TEXT")
    private String newValue;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "additional_info", columnDefinition = "TEXT")
    private String additionalInfo;

    public AuditLogEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public AuditLogType getType() {
        return type;
    }

    public void setType(AuditLogType type) {
        this.type = type;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public enum AuditLogType {
        TRANSACTION, SECURITY, SYSTEM
    }
}

// === ARCHIVO: src/main/java/infrastructure/client/JpaAuditLogRepository.java ===
package infrastructure.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAuditLogRepository extends JpaRepository<AuditLogEntity, UUID> {
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: reto-banca
  datasource:
    url: jdbc:h2:mem:bankdb
    driver-class-name: org.h2.Driver
    username: sa
    password: 
    hikari:
      maximum-pool-size: 10
      minimum-idle: 5
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.H2Dialect
        jdbc:
          time_zone: UTC
    open-in-view: false
  sql:
    init:
      mode: always
      schema-locations: classpath:schema.sql
      data-locations: classpath:data.sql

server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
      base-path: /actuator
  endpoint:
    health:
      show-details: always
      show-components: always
  health:
    db:
      enabled: true
    diskspace:
      enabled: true
  metrics:
    export:
      prometheus:
        enabled: true

logging:
  level:
    root: INFO
    com.example: DEBUG
    org.springframework.transaction: DEBUG
    org.springframework.orm.jpa: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/application.log
    max-size: 10MB
    max-history: 30

transaction:
  default-timeout: 30
  rollback-on-commit-failure: true
  propagation:
    required: REQUIRED
    requires-new: REQUIRES_NEW

app:
  transfer:
    max-amount: 100000.00
    min-amount: 0.01
    daily-limit: 500000.00
  notification:
    retry-attempts: 3
    retry-delay: 1000
  audit:
    enabled: true
    retention-days: 90

// === ARCHIVO: src/main/java/resources/application.yml ===
spring:
  application:
    name: reto
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.H2Dialect
    open-in-view: false
  sql:
    init:
      mode: always

server:
  port: 8080

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always

logging:
  level:
    root: INFO
    com.example: DEBUG
    org.springframework.transaction: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
```
