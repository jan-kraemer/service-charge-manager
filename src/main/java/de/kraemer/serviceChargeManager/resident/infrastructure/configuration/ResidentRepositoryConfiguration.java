package de.kraemer.serviceChargeManager.resident.infrastructure.configuration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
    basePackages = "de.kraemer.serviceChargeManager.resident.infrastructure.repository")
class ResidentRepositoryConfiguration {}
