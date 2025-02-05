package de.kraemer.serviceChargeManager.property.infrastructure.repository;

import de.kraemer.serviceChargeManager.property.domain.model.Property;
import de.kraemer.serviceChargeManager.property.domain.repository.PropertyRepository;
import de.kraemer.serviceChargeManager.property.infrastructure.mapper.PropertyInfrastructureMapper;
import de.kraemer.serviceChargeManager.property.infrastructure.mapper.PropertyInfrastructureMapperImpl;
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
@Import({PostgresPropertyRepositoryImpl.class, PropertyInfrastructureMapperImpl.class})
@Testcontainers
class PostgresPropertyRepositoryTest {
  @Container @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>(DockerImageName.parse("postgres:17.2"));

  @Autowired PropertyRepository propertyRepository;
  @Autowired PropertyInfrastructureMapper propertyInfrastructureMapper;
  @Autowired SpringDataPropertyRepository springDataPropertyRepository;

  static Stream<Arguments> existsByCityAndZipCodeAndCountryAndAddress_notExisting_params() {
    return Stream.of(
        Arguments.of(
            Property.builder()
                .address("address1")
                .city("city")
                .zipCode("zipCode")
                .country("country")
                .build()),
        Arguments.of(
            Property.builder()
                .address("address")
                .city("city1")
                .zipCode("zipCode")
                .country("country")
                .build()),
        Arguments.of(
            Property.builder()
                .address("address")
                .city("city")
                .zipCode("zipCode1")
                .country("country")
                .build()),
        Arguments.of(
            Property.builder()
                .address("address")
                .city("city")
                .zipCode("zipCode")
                .country("country1")
                .build()),
        Arguments.of(
            Property.builder()
                .address("address1")
                .city("city1")
                .zipCode("zipCode")
                .country("country")
                .build()),
        Arguments.of(
            Property.builder()
                .address("address1")
                .city("city1")
                .zipCode("zipCode1")
                .country("country")
                .build()),
        Arguments.of(
            Property.builder()
                .address("address1")
                .city("city1")
                .zipCode("zipCode1")
                .country("country1")
                .build()));
  }

  @AfterEach
  void afterEach() {
    springDataPropertyRepository.deleteAll();
  }

  @ParameterizedTest
  @MethodSource("existsByCityAndZipCodeAndCountryAndAddress_notExisting_params")
  void existsByCityAndZipCodeAndCountryAndAddress_notExisting(Property property) {
    // given
    var givenProperty =
        Property.builder()
            .address("address")
            .city("city")
            .zipCode("zipCode")
            .country("country")
            .build();
    springDataPropertyRepository.save(
        propertyInfrastructureMapper.mapToPropertyEntity(givenProperty));

    // when
    var exists = propertyRepository.existsByAddressAndCityAndZipCodeAndCountry(property);

    // then
    Assertions.assertFalse(exists);
  }

  @Test
  void existsByCityAndZipCodeAndCountryAndAddress_existing() {
    // given
    var property =
        Property.builder()
            .address("address")
            .city("city")
            .zipCode("zipCode")
            .country("country")
            .build();
    springDataPropertyRepository.save(propertyInfrastructureMapper.mapToPropertyEntity(property));

    // when
    var exists = propertyRepository.existsByAddressAndCityAndZipCodeAndCountry(property);

    // then
    Assertions.assertTrue(exists);
  }
}
