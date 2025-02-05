package de.kraemer.serviceChargeManager.resident.application.mapper;

import de.kraemer.serviceChargeManager.resident.application.rest.inbound.AddResidentRequest;
import de.kraemer.serviceChargeManager.resident.application.rest.outbound.AddResidentResponse;
import de.kraemer.serviceChargeManager.resident.domain.model.Resident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResidentApplicationMapper {
  @Mapping(target = "id", ignore = true)
  Resident mapToResident(AddResidentRequest addResidentRequest);

  AddResidentResponse mapToResidentResponse(Resident resident);
}
