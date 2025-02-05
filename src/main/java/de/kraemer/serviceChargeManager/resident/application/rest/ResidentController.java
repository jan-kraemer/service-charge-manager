package de.kraemer.serviceChargeManager.resident.application.rest;

import de.kraemer.serviceChargeManager.resident.application.mapper.ResidentApplicationMapper;
import de.kraemer.serviceChargeManager.resident.application.rest.inbound.AddResidentRequest;
import de.kraemer.serviceChargeManager.resident.domain.exception.ResidentAlreadyExistsException;
import de.kraemer.serviceChargeManager.resident.domain.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequestMapping("api/resident")
@RestController
@RequiredArgsConstructor
public class ResidentController {
  private final ResidentService residentService;
  private final ResidentApplicationMapper ram;

  @PostMapping
  public ResponseEntity<?> addResident(@RequestBody AddResidentRequest addResidentRequest)
      throws ResidentAlreadyExistsException {
    var resident = residentService.addResident(ram.mapToResident(addResidentRequest));
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ram.mapToResidentResponse(resident).id())
                .toUri())
        .build();
  }
}
