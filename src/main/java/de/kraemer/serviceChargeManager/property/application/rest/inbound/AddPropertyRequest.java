package de.kraemer.serviceChargeManager.property.application.rest.inbound;

public record AddPropertyRequest(
    String address, String city, String zipCode, String country, int quantityResidentialUnits) {}
