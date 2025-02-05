package de.kraemer.serviceChargeManager.property.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "property")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyEntity {
  @Id private UUID id;
  private String address;
  private String city;
  private String zipCode;
  private String country;
  private int quantityResidentialUnits;
}
