package eu.api.application.command.create_service;

import eu.api.application.query.get_app_by_code.IGetAppByCodeQueryHandler;
import eu.api.domain.modal.App;
import eu.api.domain.modal.Service;
import eu.api.domain.repository.IAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
class CreateServiceCommandHandlerImpl implements ICreateServiceCommandHandler {
    private final IGetAppByCodeQueryHandler query;
    private final IAppRepository repository;

    @Override
    public Service handle(CreateServiceCommand command) {
        App app = query.handle(command.appCode());
        Service appService = new Service(
                app.getId().code(),
                command.name(),
                command.type(),
                command.subType(),
                command.description()
        );
        repository.save(app.addService(appService));
        return appService;
    }
}
