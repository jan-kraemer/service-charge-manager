package de.kraemer.serviceChargeManager.resident.infrastructure.configuration;

import de.kraemer.serviceChargeManager.resident.domain.repository.ResidentRepository;
import de.kraemer.serviceChargeManager.resident.domain.service.ResidentService;
import de.kraemer.serviceChargeManager.resident.domain.service.ResidentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ResidentBeanConfiguration {

  @Bean
  ResidentService residentService(final ResidentRepository residentRepository) {
    return new ResidentServiceImpl(residentRepository);
  }
}
