package de.kraemer.serviceChargeManager.property.infrastructure.mapper;

import de.kraemer.serviceChargeManager.property.domain.model.Property;
import de.kraemer.serviceChargeManager.property.infrastructure.entity.PropertyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PropertyInfrastructureMapper {
  PropertyEntity mapToPropertyEntity(Property property);

  Property mapToProperty(PropertyEntity propertyEntity);
}
