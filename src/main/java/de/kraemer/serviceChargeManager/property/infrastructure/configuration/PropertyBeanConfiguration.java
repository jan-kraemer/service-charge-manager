package de.kraemer.serviceChargeManager.property.infrastructure.configuration;

import de.kraemer.serviceChargeManager.property.domain.repository.PropertyRepository;
import de.kraemer.serviceChargeManager.property.domain.service.PropertyService;
import de.kraemer.serviceChargeManager.property.domain.service.PropertyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PropertyBeanConfiguration {
  @Bean
  PropertyService propertyService(final PropertyRepository propertyRepository) {
    return new PropertyServiceImpl(propertyRepository);
  }
}
