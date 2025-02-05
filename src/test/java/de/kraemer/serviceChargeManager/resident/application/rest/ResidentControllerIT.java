package de.kraemer.serviceChargeManager.resident.application.rest;

import de.kraemer.serviceChargeManager.resident.application.rest.inbound.AddResidentRequest;
import de.kraemer.serviceChargeManager.resident.application.rest.outbound.AddResidentResponse;
import de.kraemer.serviceChargeManager.resident.infrastructure.repository.SpringDataResidentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResidentControllerIT {
  private static final String RESIDENT_URI = "/api/resident";

  @Autowired private TestRestTemplate restTemplate;

  @AfterEach
  void afterEach(@Autowired SpringDataResidentRepository springDataResidentRepository) {
    springDataResidentRepository.deleteAll();
  }

  @Test
  void createResident_success() {
    // given
    var addResidentRequest = new AddResidentRequest("name", "surname", "email", "phone");

    // when
    var response =
        restTemplate.postForEntity(RESIDENT_URI, addResidentRequest, AddResidentResponse.class);

    // then
    Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    Assertions.assertNotNull(response.getHeaders().getLocation());
  }

  @Test
  void createResident_alreadyExists() {
    // given
    var firstResident = new AddResidentRequest("name", "surname", "email", "phone");
    var secondResident = new AddResidentRequest("name", "surname", "email", "phone");

    // when
    restTemplate.postForEntity(RESIDENT_URI, firstResident, AddResidentResponse.class);
    var response = restTemplate.postForEntity(RESIDENT_URI, secondResident, ProblemDetail.class);

    // then
    var problemDetail = response.getBody();
    Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    Assertions.assertNotNull(problemDetail);
    Assertions.assertEquals("Resident already exists", problemDetail.getDetail());
  }
}
