package de.kraemer.serviceChargeManager.property.infrastructure.repository;

import de.kraemer.serviceChargeManager.property.infrastructure.entity.PropertyEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataPropertyRepository extends ListCrudRepository<PropertyEntity, UUID> {
  boolean existsByAddressAndCityAndZipCodeAndCountry(
      String address, String city, String zipCode, String country);
}
