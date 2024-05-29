package eu.api.application.command.create_service;

import eu.api.domain.modal.Service;
import jakarta.validation.Valid;

public interface ICreateServiceCommandHandler {
    Service handle(@Valid CreateServiceCommand command);
}
