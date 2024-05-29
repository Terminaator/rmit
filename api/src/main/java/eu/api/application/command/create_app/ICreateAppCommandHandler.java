package eu.api.application.command.create_app;

import eu.api.domain.modal.App;
import jakarta.validation.Valid;

public interface ICreateAppCommandHandler {
    App handle(@Valid CreateAppCommand command);
}
