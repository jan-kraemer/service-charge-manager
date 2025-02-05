package de.kraemer.serviceChargeManager.property.domain.repository;

import de.kraemer.serviceChargeManager.property.domain.model.Property;

public interface PropertyRepository {

  Property save(Property property);

  boolean existsByAddressAndCityAndZipCodeAndCountry(Property property);
}
