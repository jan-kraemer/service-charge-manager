package de.kraemer.serviceChargeManager.resident.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "resident")
public class ResidentEntity {
  @Id private UUID id;
  private String firstName;
  private String sureName;
  private String email;
  private String phoneNumber;
}
