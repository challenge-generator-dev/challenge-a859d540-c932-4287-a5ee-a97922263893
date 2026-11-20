# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de transacciones en un sistema de banca**.

| | |
|---|---|
| Tema | Manejo de transacciones y consistencia de datos |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-starter-test
- com.h2database:h2
- org.mockito:mockito-core
- org.mockito:mockito-junit-jupiter
- org.springframework.boot:spring-boot-starter-actuator
- org.springframework.boot:spring-boot-starter-aop
- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-validation

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de límites transaccionales**: Documento que describe los límites transaccionales para el caso de uso seleccionado, anclado a la pieza `TransferService` y sus casos de aceptación.
- **Fase 2 — Implementación de la propagación de transacciones**: Código que implementa la propagación de transacciones para el caso de uso seleccionado.
- **Fase 3 — Manejo de rollback ante fallos**: Código que implementa el manejo de rollback ante fallos durante las transacciones.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/application/service/TransferService.java` — La pieza p1 (Definición de límites transaccionales y propagación, fase f1) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/infrastructure/adapter/AccountRepositoryImpl.java` — La pieza p2 (Implementación de propagación transaccional en adaptador de salida, fase f2) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/application/service/TransferServiceRollbackHandler.java` — La pieza p3 (Manejo de rollback ante fallos parciales, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).
- [ ] `src/main/java/infrastructure/adapter/AuditLogRepositoryImpl.java` — La pieza p4 (Registro de fallos transaccionales para auditoría, fase f3) vive aqui: este archivo es su unico dueno (blueprint v2).

## Mapa de fases

La **Fase 0** es clonar el repositorio, instalar dependencias y correr las pruebas de aceptacion: todas fallan porque la logica de cada pieza esta pendiente.

| Fase | Pieza (skill) | Archivo:simbolo | Criterios | Prueba | Comando |
|------|---------------|-----------------|-----------|--------|---------|
| f1 — Definición de límites transaccionales | Definición de límites transaccionales y propagación | `src/main/java/application/service/TransferService.java:TransferService` | El documento describe los límites transaccionales del caso de uso `transfer` y justifica la atomicidad de las operaciones.; El documento identifica las excepciones que disparan rollback según los casos de aceptación de `TransferService`. | — | — |
| f2 — Implementación de la propagación de transacciones | Implementación de propagación transaccional en adaptador de salida | `src/main/java/infrastructure/adapter/AccountRepositoryImpl.java:AccountRepositoryImpl` | El método `transfer` de `TransferService` usa propagación `REQUIRES_NEW` para operaciones críticas.; Las operaciones anidadas dentro de `transfer` respetan los límites transaccionales definidos en la fase f1. | — | — |
| f3 — Manejo de rollback ante fallos | Manejo de rollback ante fallos parciales | `src/main/java/application/service/TransferServiceRollbackHandler.java:TransferServiceRollbackHandler` | El método `transfer` de `TransferService` hace rollback ante excepciones de negocio (`InsufficientFundsException`).; El método `transfer` de `TransferService` no hace rollback ante excepciones no críticas (`NotificationException`).; El adaptador `AccountRepository` lanza excepciones técnicas que disparan rollback.; El caso de uso `transfer` maneja correctamente fallos parciales (ej: débito exitoso pero fallo en crédito). | — | — |
| f3 — Manejo de rollback ante fallos | Registro de fallos transaccionales para auditoría | `src/main/java/infrastructure/adapter/AuditLogRepositoryImpl.java:AuditLogRepositoryImpl` | El método `transfer` de `TransferService` hace rollback ante excepciones de negocio (`InsufficientFundsException`).; El método `transfer` de `TransferService` no hace rollback ante excepciones no críticas (`NotificationException`).; El adaptador `AccountRepository` lanza excepciones técnicas que disparan rollback.; El caso de uso `transfer` maneja correctamente fallos parciales (ej: débito exitoso pero fallo en crédito). | — | — |

## Pruebas de aceptacion y ubicacion de la logica

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

La logica de cada pieza va en su archivo; no la muevas a otro.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Ha usado @Transactional pero no entiende bien la propagación ni cuándo se hace rollback | chequeo estatico (code): exige `@Transactional(propagation = Propagation.REQUIRES_NEW)`, `@Transactional(rollbackFor = {InsufficientFundsException.class})`; prohibe `@Transactional(propagation = Propagation.REQUIRED)` | Restriccion no verificada en chequeo estatico (code): exige `@Transactional(propagation = Propagation.REQUIRES_NEW)`, `@Transactional(rollbackFor = {InsufficientFundsException.class})`; prohibe `@Transactional(propagation = Propagation.REQUIRED)` |

## Lo que falta y tenes que completar

### 1. Referencias colgando (1)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/domain/port/AccountRepository.java` — `Account.orElseThrow`
      Se invoca `orElseThrow` sobre `Account`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (19)

- `src/main/java/Application.java`
- `pom.xml`
- `src/main/java/domain/model/Account.java`
- `src/main/java/domain/model/InsufficientFundsException.java`
- `src/main/java/domain/model/NotificationException.java`
- `src/main/java/domain/model/AuditLog.java`
- `src/main/java/domain/port/AccountRepository.java`
- `src/main/java/domain/port/NotificationService.java`
- `src/main/java/application/service/TransferService.java`
- `src/main/java/infrastructure/adapter/AccountRepositoryImpl.java`
- `src/main/java/application/service/TransferServiceRollbackHandler.java`
- `src/main/java/infrastructure/adapter/AuditLogRepositoryImpl.java`
- `src/main/java/domain/port/AuditLogRepository.java`
- `src/main/java/infrastructure/client/JpaAccountRepository.java`
- `src/main/java/infrastructure/client/AccountEntity.java`
- `src/main/java/infrastructure/client/AuditLogEntity.java`
- `src/main/java/infrastructure/client/JpaAuditLogRepository.java`
- `src/main/resources/application.yml`
- `src/main/java/resources/application.yml`

### Capas del patron declarado

Son las capas que la propuesta arquitectonica plantea, marcadas segun existan hoy en el repo. Orientan donde poner el codigo nuevo; no son una condicion para compilar.

- `src/main/java/com/bank/domain/model` (ausente)
- `src/main/java/com/bank/domain/port` (ausente)
- `src/main/java/com/bank/application/service` (ausente)
- `src/main/java/com/bank/infrastructure/adapter` (ausente)
- `src/main/java/com/bank/infrastructure/client` (ausente)
- `src/main/java/com/bank/infrastructure/config` (ausente)
- `src/main/resources` (presente)
- `src/test/java/com/bank/application/service` (ausente)
- `src/test/java/com/bank/infrastructure/adapter` (ausente)

## Verificacion

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollo backend, Tecnología Java, Advanced L2
- Brecha que el reto ataca: Implementa operaciones de negocio que modifican varias entidades garantizando la consistencia: límites transaccionales, propagación y manejo de rollback ante fallos.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
