package de.kraemer.serviceChargeManager.resident.domain.model;

import java.util.UUID;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Resident {
	@Builder.Default
	private final UUID id = UUID.randomUUID();
	private String firstName;
	private String sureName;
	private String email;
	private String phoneNumber;
}
