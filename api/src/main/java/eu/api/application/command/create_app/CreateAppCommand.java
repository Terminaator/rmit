package eu.api.application.command.create_app;

import eu.api.domain.modal.AppGroup;
import eu.api.domain.modal.AppType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateAppCommand(
        @Size(max = 255) @NotBlank String name,
        AppGroup group,
        AppType type,
        @Size(max = 20000) String description,
        @PositiveOrZero Integer cost
) {
}