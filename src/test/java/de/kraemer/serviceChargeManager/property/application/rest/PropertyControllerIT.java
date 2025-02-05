package de.kraemer.serviceChargeManager.property.application.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import de.kraemer.serviceChargeManager.property.application.rest.inbound.AddPropertyRequest;
import de.kraemer.serviceChargeManager.property.application.rest.outbound.AddPropertyResponse;
import de.kraemer.serviceChargeManager.property.infrastructure.repository.SpringDataPropertyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertyControllerIT {
  private static final String PROPERTY_URI = "/api/property";

  @Autowired private TestRestTemplate restTemplate;

  @AfterEach
  void afterEach(@Autowired SpringDataPropertyRepository springDataPropertyRepository) {
    springDataPropertyRepository.deleteAll();
  }

  @Test
  void createProperty_success() {
    // given
    var addPropertyRequest =
        new AddPropertyRequest("Test address", "Test city", "12345", "Test country", 10);

    // when
    var response =
        restTemplate.postForEntity(PROPERTY_URI, addPropertyRequest, AddPropertyResponse.class);

    // then
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getHeaders().getLocation()).isNotNull();
  }

  @Test
  void createProperty_alreadyExists() {
    // given
    var firstProperty =
        new AddPropertyRequest("Test address", "Test city", "12345", "Test country", 10);

    var secondProperty =
        new AddPropertyRequest("Test address", "Test city", "12345", "Test country", 10);

    // when
    restTemplate.postForEntity(PROPERTY_URI, firstProperty, AddPropertyResponse.class);
    var response = restTemplate.postForEntity(PROPERTY_URI, secondProperty, ProblemDetail.class);

    // then
    var problemDetail = response.getBody();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    assertNotNull(problemDetail);
    assertThat("Property already exists").isEqualTo(problemDetail.getDetail());
  }
}
