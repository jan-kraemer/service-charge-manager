package de.kraemer.serviceChargeManager;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgresContainer() {
    return new PostgreSQLContainer<>(DockerImageName.parse("postgres:17.2"));
  }

  @Bean
  @ServiceConnection(name = "openzipkin/zipkin")
  GenericContainer<?> zipkinContainer() {
    return new GenericContainer<>(DockerImageName.parse("openzipkin/zipkin:3.4.4"))
        .withExposedPorts(9411);
  }
}
