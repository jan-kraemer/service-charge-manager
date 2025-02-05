package de.kraemer.serviceChargeManager.resident.infrastructure.repository;

import de.kraemer.serviceChargeManager.resident.infrastructure.entity.ResidentEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataResidentRepository extends ListCrudRepository<ResidentEntity, UUID> {
  boolean existsByFirstNameAndSureNameAndEmailAndPhoneNumber(
      String firstName, String sureName, String email, String phoneNumber);
}
