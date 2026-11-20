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