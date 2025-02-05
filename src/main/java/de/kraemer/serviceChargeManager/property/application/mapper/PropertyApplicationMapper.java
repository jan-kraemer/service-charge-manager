package de.kraemer.serviceChargeManager.property.application.mapper;

import de.kraemer.serviceChargeManager.property.application.rest.inbound.AddPropertyRequest;
import de.kraemer.serviceChargeManager.property.application.rest.outbound.AddPropertyResponse;
import de.kraemer.serviceChargeManager.property.domain.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PropertyApplicationMapper {
  @Mapping(target = "id", ignore = true)
  Property mapToProperty(AddPropertyRequest addPropertyRequest);

  AddPropertyResponse mapToResponse(Property property);
}
