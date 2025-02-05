package de.kraemer.serviceChargeManager.resident.domain.repository;

import de.kraemer.serviceChargeManager.resident.domain.model.Resident;

public interface ResidentRepository {

  boolean existsByFirstNameAndSureNameAndEmailAndPhoneNumber(Resident resident);

  Resident save(Resident resident);
}
