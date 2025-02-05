package de.kraemer.serviceChargeManager.resident.application.rest.inbound;

public record AddResidentRequest(
    String firstName, String sureName, String email, String phoneNumber) {}
