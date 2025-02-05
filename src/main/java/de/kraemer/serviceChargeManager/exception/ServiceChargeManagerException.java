package de.kraemer.serviceChargeManager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceChargeManagerException extends Exception {
  private final HttpStatus status;

  public ServiceChargeManagerException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }
}
