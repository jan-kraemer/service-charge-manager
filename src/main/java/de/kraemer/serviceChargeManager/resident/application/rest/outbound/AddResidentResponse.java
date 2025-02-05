package de.kraemer.serviceChargeManager.resident.application.rest.outbound;

import java.util.UUID;

public record AddResidentResponse(
    UUID id, String firstName, String sureName, String email, String phoneNumber) {}
