package de.kraemer.serviceChargeManager.resident.infrastructure.mapper;

import de.kraemer.serviceChargeManager.resident.domain.model.Resident;
import de.kraemer.serviceChargeManager.resident.infrastructure.entity.ResidentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResidentInfrastructureMapper {
  ResidentEntity mapToResidentEntity(Resident resident);

  Resident mapToResident(ResidentEntity residentEntity);
}
