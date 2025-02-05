package de.kraemer.serviceChargeManager.property.infrastructure.configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
    basePackages = "de.kraemer.serviceChargeManager.property.infrastructure.repository")
public class PropertyRepositoryConfiguration {}
