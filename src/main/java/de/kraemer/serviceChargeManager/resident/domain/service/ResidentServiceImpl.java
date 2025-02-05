package de.kraemer.serviceChargeManager.resident.domain.service;

import de.kraemer.serviceChargeManager.resident.domain.exception.ResidentAlreadyExistsException;
import de.kraemer.serviceChargeManager.resident.domain.model.Resident;
import de.kraemer.serviceChargeManager.resident.domain.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {
  private final ResidentRepository residentRepository;

  @Override
  public Resident addResident(Resident resident) throws ResidentAlreadyExistsException {
    var residentExists =
        residentRepository.existsByFirstNameAndSureNameAndEmailAndPhoneNumber(resident);
    if (residentExists) {
      throw new ResidentAlreadyExistsException();
    }
    return residentRepository.save(resident);
  }
}
