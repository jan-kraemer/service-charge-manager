package de.kraemer.serviceChargeManager.property.application.rest.outbound;

import java.util.UUID;

public record AddPropertyResponse(
    UUID id,
    String address,
    String city,
    String zipCode,
    String country,
    int quantityResidentialUnits) {}
