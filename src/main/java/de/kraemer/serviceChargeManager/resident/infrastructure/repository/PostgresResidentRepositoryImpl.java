package de.kraemer.serviceChargeManager.resident.infrastructure.repository;

import de.kraemer.serviceChargeManager.resident.domain.model.Resident;
import de.kraemer.serviceChargeManager.resident.domain.repository.ResidentRepository;
import de.kraemer.serviceChargeManager.resident.infrastructure.entity.ResidentEntity;
import de.kraemer.serviceChargeManager.resident.infrastructure.mapper.ResidentInfrastructureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresResidentRepositoryImpl implements ResidentRepository {
  private final SpringDataResidentRepository springDataResidentRepository;
  private final ResidentInfrastructureMapper rim;

  @Override
  public boolean existsByFirstNameAndSureNameAndEmailAndPhoneNumber(Resident resident) {
    return springDataResidentRepository.existsByFirstNameAndSureNameAndEmailAndPhoneNumber(
        resident.getFirstName(),
        resident.getSureName(),
        resident.getEmail(),
        resident.getPhoneNumber());
  }

  @Override
  public Resident save(Resident resident) {
    var residentEntity = rim.mapToResidentEntity(resident);
    ResidentEntity savedResidentEntity = springDataResidentRepository.save(residentEntity);
    return rim.mapToResident(savedResidentEntity);
  }
}
