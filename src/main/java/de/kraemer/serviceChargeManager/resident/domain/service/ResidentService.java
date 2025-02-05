package de.kraemer.serviceChargeManager.resident.domain.service;

import de.kraemer.serviceChargeManager.resident.domain.exception.ResidentAlreadyExistsException;
import de.kraemer.serviceChargeManager.resident.domain.model.Resident;

public interface ResidentService {
  Resident addResident(Resident resident) throws ResidentAlreadyExistsException;
}
