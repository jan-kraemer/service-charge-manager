package de.kraemer.serviceChargeManager.property.domain.service;

import de.kraemer.serviceChargeManager.property.domain.exception.PropertyAlreadyExistsException;
import de.kraemer.serviceChargeManager.property.domain.model.Property;

public interface PropertyService {
  Property addProperty(Property property) throws PropertyAlreadyExistsException;
}
