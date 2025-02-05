package de.kraemer.serviceChargeManager.property.infrastructure.repository;

import de.kraemer.serviceChargeManager.property.domain.model.Property;
import de.kraemer.serviceChargeManager.property.domain.repository.PropertyRepository;
import de.kraemer.serviceChargeManager.property.infrastructure.mapper.PropertyInfrastructureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
class PostgresPropertyRepositoryImpl implements PropertyRepository {
  private final PropertyInfrastructureMapper propertyInfrastructureMapper;
  private final SpringDataPropertyRepository springDataPropertyRepository;

  @Override
  @Transactional
  public Property save(Property property) {
    var propertyEntity = propertyInfrastructureMapper.mapToPropertyEntity(property);
    var savedPropertyEntity = springDataPropertyRepository.save(propertyEntity);
    return propertyInfrastructureMapper.mapToProperty(savedPropertyEntity);
  }

  @Override
  public boolean existsByAddressAndCityAndZipCodeAndCountry(Property property) {
    return springDataPropertyRepository.existsByAddressAndCityAndZipCodeAndCountry(
        property.getAddress(), property.getCity(), property.getZipCode(), property.getCountry());
  }
}
