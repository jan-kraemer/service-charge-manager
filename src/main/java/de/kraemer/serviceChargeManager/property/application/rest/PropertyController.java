package de.kraemer.serviceChargeManager.property.application.rest;

import de.kraemer.serviceChargeManager.property.application.mapper.PropertyApplicationMapper;
import de.kraemer.serviceChargeManager.property.application.rest.inbound.AddPropertyRequest;
import de.kraemer.serviceChargeManager.property.application.rest.outbound.AddPropertyResponse;
import de.kraemer.serviceChargeManager.property.domain.exception.PropertyAlreadyExistsException;
import de.kraemer.serviceChargeManager.property.domain.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("/api/property")
@RestController
@RequiredArgsConstructor
public class PropertyController {
  private final PropertyService propertyService;
  private final PropertyApplicationMapper pam;

  @PostMapping
  public ResponseEntity<AddPropertyResponse> addProperty(
      @RequestBody AddPropertyRequest addPropertyRequest) throws PropertyAlreadyExistsException {
    var property = propertyService.addProperty(pam.mapToProperty(addPropertyRequest));
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pam.mapToResponse(property).id())
                .toUri())
        .build();
  }
}
