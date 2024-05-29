package eu.api.application.command.create_app;

import eu.api.domain.modal.App;
import eu.api.domain.repository.IAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@RequiredArgsConstructor
class CreateAppCommandHandlerImpl implements ICreateAppCommandHandler {
    private final IAppRepository repository;

    @Override
    public App handle(CreateAppCommand command) {
        //create new Application
        App app = new App(
                command.name(),
                command.group(),
                command.type(),
                command.description(),
                command.cost()
        );
        //save
        return repository.save(app);
    }
}
