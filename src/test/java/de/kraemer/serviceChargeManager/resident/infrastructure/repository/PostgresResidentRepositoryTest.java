package de.kraemer.serviceChargeManager.resident.infrastructure.repository;

import de.kraemer.serviceChargeManager.resident.domain.model.Resident;
import de.kraemer.serviceChargeManager.resident.domain.repository.ResidentRepository;
import de.kraemer.serviceChargeManager.resident.infrastructure.mapper.ResidentInfrastructureMapper;
import de.kraemer.serviceChargeManager.resident.infrastructure.mapper.ResidentInfrastructureMapperImpl;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@DataJpaTest
@Testcontainers
@Import({PostgresResidentRepositoryImpl.class, ResidentInfrastructureMapperImpl.class})
class PostgresResidentRepositoryTest {
  @Container @ServiceConnection
  static PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>(DockerImageName.parse("postgres:17.2"));

  @Autowired ResidentRepository residentRepository;
  @Autowired SpringDataResidentRepository springDataResidentRepository;
  @Autowired ResidentInfrastructureMapper rim;

  static Stream<Arguments> existsByFirstNameAndSureNameAndEmailAndPhoneNumber_notExisting_params() {
    return Stream.of(
        Arguments.of(
            Resident.builder()
                .firstName("name1")
                .sureName("surname")
                .email("email")
                .phoneNumber("phone")
                .build()),
        Arguments.of(
            Resident.builder()
                .firstName("name")
                .sureName("surname1")
                .email("email")
                .phoneNumber("phone")
                .build()),
        Arguments.of(
            Resident.builder()
                .firstName("name")
                .sureName("surname")
                .email("email1")
                .phoneNumber("phone")
                .build()),
        Arguments.of(
            Resident.builder()
                .firstName("name")
                .sureName("surname")
                .email("email")
                .phoneNumber("phone1")
                .build()),
        Arguments.of(
            Resident.builder()
                .firstName("name1")
                .sureName("surname1")
                .email("email")
                .phoneNumber("phone")
                .build()),
        Arguments.of(
            Resident.builder()
                .firstName("name1")
                .sureName("surname1")
                .email("email1")
                .phoneNumber("phone")
                .build()),
        Arguments.of(
            Resident.builder()
                .firstName("name1")
                .sureName("surname1")
                .email("email1")
                .phoneNumber("phone1")
                .build()));
  }

  @AfterEach
  void afterEach(@Autowired SpringDataResidentRepository springDataResidentRepository) {
    springDataResidentRepository.deleteAll();
  }

  @Test
  void existsByFirstNameAndSureNameAndEmailAndPhoneNumber_exists() {
    // given
    var resident =
        Resident.builder()
            .firstName("name")
            .sureName("surname")
            .email("email")
            .phoneNumber("phone")
            .build();
    springDataResidentRepository.save(rim.mapToResidentEntity(resident));

    // when
    var exists = residentRepository.existsByFirstNameAndSureNameAndEmailAndPhoneNumber(resident);

    // then
    Assertions.assertTrue(exists);
  }

  @ParameterizedTest
  @MethodSource("existsByFirstNameAndSureNameAndEmailAndPhoneNumber_notExisting_params")
  void existsByFirstNameAndSureNameAndEmailAndPhoneNumber_notExists(Resident resident) {
    // given
    var givenResident =
        Resident.builder()
            .firstName("name")
            .sureName("surname")
            .email("email")
            .phoneNumber("phone")
            .build();
    springDataResidentRepository.save(rim.mapToResidentEntity(givenResident));

    // when
    var exists = residentRepository.existsByFirstNameAndSureNameAndEmailAndPhoneNumber(resident);

    // then
    Assertions.assertFalse(exists);
  }
}
