package de.kraemer.serviceChargeManager.resident.domain.exception;

import de.kraemer.serviceChargeManager.exception.ServiceChargeManagerException;
import org.springframework.http.HttpStatus;

public class ResidentAlreadyExistsException extends ServiceChargeManagerException {
  private static final HttpStatus STATUS = HttpStatus.CONFLICT;
  private static final String MESSAGE = "Resident already exists";

  public ResidentAlreadyExistsException() {
    super(STATUS, MESSAGE);
  }
}
