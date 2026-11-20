# Implementación de transacciones en un sistema de banca

En un sistema de banca, las transacciones deben garantizar la consistencia de los datos al modificar varias entidades. El sistema debe manejar correctamente los límites transaccionales, la propagación de transacciones y el rollback en caso de fallos. El objetivo es que el candidato defina y pruebe estos conceptos en un caso de uso real.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Manejo de transacciones y consistencia de datos |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Stack

- **Lenguaje:** Java
- **Framework:** Spring Boot 3.5
- **Version:** Java 21

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Tener el proyecto base del reto funcionando en tu máquina: clonar el repositorio, instalar las herramientas y verificar que compila y arranca antes de empezar a practicar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Instala las herramientas para ejecutar el proyecto: JDK 17+ y Maven 3.9+.
- Clona el repositorio del reto (el enlace viene en la entrega del reto y en la sección "Repositorio del Proyecto" del PDF) y ábrelo en tu IDE.
- Instala las dependencias y compila ejecutando `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package` en la raíz del repositorio.
- Confirma que el comando termina sin errores: el proyecto arranca y sus pruebas corren.
- Ubica los archivos de práctica: son los que contienen `TODO(reto)` (búscalos en el repositorio). Lee el README.md y el AGENTS.md antes de empezar.

**Entregable:** El proyecto compila/arranca sin errores con `mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package`.

<details>
<summary>Pistas de conocimiento</summary>

- Los archivos con `TODO(reto)` son el ejercicio: el resto del repositorio ya está resuelto y no hace falta tocarlo.
- Si el comando de verificación falla, revisa la versión de las herramientas instaladas antes de modificar código.
- AGENTS.md describe el reto, el comando de verificación y las reglas del repositorio.

</details>

### Fase 1: Definición de límites transaccionales

**Objetivo:** Definir los límites transaccionales para un caso de uso específico en el sistema de banca.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Explicar por qué el método `transfer` de `TransferService` debe ser transaccional.
- Detallar qué operaciones dentro de `transfer` deben ser atómicas y por qué.
- Mencionar qué excepciones deben disparar rollback y cuáles no, basado en los casos de aceptación de la pieza `TransferService`.

**Entregable:** Documento que describe los límites transaccionales para el caso de uso seleccionado, anclado a la pieza `TransferService` y sus casos de aceptación.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo las transacciones afectan la consistencia de los datos en el sistema de banca.

</details>

### Fase 2: Implementación de la propagación de transacciones

**Objetivo:** Implementar la propagación de transacciones en el caso de uso seleccionado.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar la lógica para manejar la propagación de transacciones en el caso de uso.
- Asegurar que las transacciones se propaguen correctamente entre las entidades involucradas.

**Entregable:** Código que implementa la propagación de transacciones para el caso de uso seleccionado.

<details>
<summary>Pistas de conocimiento</summary>

- Revisa cómo se maneja la propagación de transacciones en sistemas similares.

</details>

### Fase 3: Manejo de rollback ante fallos

**Objetivo:** Implementar el manejo de rollback en caso de fallos durante las transacciones.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar la lógica para manejar el rollback en caso de fallos durante las transacciones.
- Probar diferentes escenarios de fallo y asegurar que el sistema regresa a un estado consistente.

**Entregable:** Código que implementa el manejo de rollback ante fallos durante las transacciones.

<details>
<summary>Pistas de conocimiento</summary>

- Considera diferentes tipos de fallos que podrían ocurrir durante las transacciones y cómo manejarlos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los límites transaccionales y por qué son importantes en un sistema de banca?
- **paraQueSirve**: ¿Para qué sirve la propagación de transacciones en un sistema de banca?
- **comoSeUsa**: ¿Cómo se usa el manejo de rollback ante fallos en un sistema de banca?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar transacciones en un sistema de banca?
- **queDecisionesImplica**: ¿Qué decisiones implica el manejo de transacciones en un sistema de banca?

## Criterios de Evaluacion

- Definición correcta de los límites transaccionales para un caso de uso específico.
- Implementación correcta de la propagación de transacciones en el caso de uso seleccionado.
- Manejo correcto del rollback ante fallos durante las transacciones.

## Mapa de fases

La **Fase 0** es clonar el repositorio, instalar dependencias y correr las pruebas de aceptacion: todas fallan porque la logica de cada pieza esta pendiente.

| Fase | Pieza (skill) | Archivo:simbolo | Criterios | Prueba | Comando |
|------|---------------|-----------------|-----------|--------|---------|
| f1 — Definición de límites transaccionales | Definición de límites transaccionales y propagación | `src/main/java/application/service/TransferService.java:TransferService` | El documento describe los límites transaccionales del caso de uso `transfer` y justifica la atomicidad de las operaciones.; El documento identifica las excepciones que disparan rollback según los casos de aceptación de `TransferService`. | — | — |
| f2 — Implementación de la propagación de transacciones | Implementación de propagación transaccional en adaptador de salida | `src/main/java/infrastructure/adapter/AccountRepositoryImpl.java:AccountRepositoryImpl` | El método `transfer` de `TransferService` usa propagación `REQUIRES_NEW` para operaciones críticas.; Las operaciones anidadas dentro de `transfer` respetan los límites transaccionales definidos en la fase f1. | — | — |
| f3 — Manejo de rollback ante fallos | Manejo de rollback ante fallos parciales | `src/main/java/application/service/TransferServiceRollbackHandler.java:TransferServiceRollbackHandler` | El método `transfer` de `TransferService` hace rollback ante excepciones de negocio (`InsufficientFundsException`).; El método `transfer` de `TransferService` no hace rollback ante excepciones no críticas (`NotificationException`).; El adaptador `AccountRepository` lanza excepciones técnicas que disparan rollback.; El caso de uso `transfer` maneja correctamente fallos parciales (ej: débito exitoso pero fallo en crédito). | — | — |
| f3 — Manejo de rollback ante fallos | Registro de fallos transaccionales para auditoría | `src/main/java/infrastructure/adapter/AuditLogRepositoryImpl.java:AuditLogRepositoryImpl` | El método `transfer` de `TransferService` hace rollback ante excepciones de negocio (`InsufficientFundsException`).; El método `transfer` de `TransferService` no hace rollback ante excepciones no críticas (`NotificationException`).; El adaptador `AccountRepository` lanza excepciones técnicas que disparan rollback.; El caso de uso `transfer` maneja correctamente fallos parciales (ej: débito exitoso pero fallo en crédito). | — | — |

## Pruebas de aceptacion

Las pruebas de aceptacion (columna "Prueba" de la tabla) son de **solo lectura**: definen que significa "listo". No las edites, no las borres y no les agregues `skip`; lo que se resuelve es el codigo de cada pieza.

## Restricciones del pedido

| Restriccion | Donde se cumple | Estado |
|-------------|-----------------|--------|
| Ha usado @Transactional pero no entiende bien la propagación ni cuándo se hace rollback | chequeo estatico (code): exige `@Transactional(propagation = Propagation.REQUIRES_NEW)`, `@Transactional(rollbackFor = {InsufficientFundsException.class})`; prohibe `@Transactional(propagation = Propagation.REQUIRED)` | Restriccion no verificada en chequeo estatico (code): exige `@Transactional(propagation = Propagation.REQUIRES_NEW)`, `@Transactional(rollbackFor = {InsufficientFundsException.class})`; prohibe `@Transactional(propagation = Propagation.REQUIRED)` |

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn -q -B -DskipTests -Dspring-boot.repackage.skip=true package
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
