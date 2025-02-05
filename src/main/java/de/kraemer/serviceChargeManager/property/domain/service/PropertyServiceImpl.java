package de.kraemer.serviceChargeManager.property.domain.service;

import de.kraemer.serviceChargeManager.property.domain.exception.PropertyAlreadyExistsException;
import de.kraemer.serviceChargeManager.property.domain.model.Property;
import de.kraemer.serviceChargeManager.property.domain.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
  private final PropertyRepository propertyRepository;

  @Override
  @Transactional
  public Property addProperty(Property property) throws PropertyAlreadyExistsException {
    var propertyExists = propertyRepository.existsByAddressAndCityAndZipCodeAndCountry(property);
    if (propertyExists) {
      throw new PropertyAlreadyExistsException();
    }
    return propertyRepository.save(property);
  }
}
