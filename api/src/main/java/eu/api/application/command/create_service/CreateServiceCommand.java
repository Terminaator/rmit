package eu.api.application.command.create_service;

import eu.api.domain.modal.ServiceSubType;
import eu.api.domain.modal.ServiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateServiceCommand(
        @NotNull UUID appCode,
        @Size(max = 255) @NotBlank String name,
        ServiceType type,
        ServiceSubType subType,
        @Size(max = 20000) String description
) {
}
