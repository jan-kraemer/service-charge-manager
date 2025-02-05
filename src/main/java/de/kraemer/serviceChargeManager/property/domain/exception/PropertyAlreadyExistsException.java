package de.kraemer.serviceChargeManager.property.domain.exception;

import de.kraemer.serviceChargeManager.exception.ServiceChargeManagerException;
import org.springframework.http.HttpStatus;

public class PropertyAlreadyExistsException extends ServiceChargeManagerException {
  private static final HttpStatus STATUS = HttpStatus.CONFLICT;
  private static final String MESSAGE = "Property already exists";

  public PropertyAlreadyExistsException() {
    super(STATUS, MESSAGE);
  }
}
