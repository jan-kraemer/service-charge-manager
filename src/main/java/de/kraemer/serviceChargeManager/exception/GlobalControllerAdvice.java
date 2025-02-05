package de.kraemer.serviceChargeManager.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class GlobalControllerAdvice {
  @ExceptionHandler(ServiceChargeManagerException.class)
  ResponseEntity<ProblemDetail> handleServiceChargeManagerException(
      ServiceChargeManagerException e) {
    var problemDetail = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
    return ResponseEntity.status(e.getStatus()).body(problemDetail);
  }
}
