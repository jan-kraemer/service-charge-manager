package de.kraemer.serviceChargeManager.property.domain.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {
  @Builder.Default private final UUID id = UUID.randomUUID();
  private String address;
  private String city;
  private String zipCode;
  private String country;
  @Builder.Default private int quantityResidentialUnits = 1;
}
