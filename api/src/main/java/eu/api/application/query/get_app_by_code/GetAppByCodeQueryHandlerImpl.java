package eu.api.application.query.get_app_by_code;

import eu.api.domain.exception.AppNotFoundException;
import eu.api.domain.modal.App;
import eu.api.domain.modal.AppIdentifier;
import eu.api.domain.repository.IAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
class GetAppByCodeQueryHandlerImpl implements IGetAppByCodeQueryHandler {
    private final IAppRepository repository;

    @Override
    public App handle(UUID appCode) {
        return repository.findById(new AppIdentifier(appCode)).orElseThrow(AppNotFoundException::new);
    }
}
