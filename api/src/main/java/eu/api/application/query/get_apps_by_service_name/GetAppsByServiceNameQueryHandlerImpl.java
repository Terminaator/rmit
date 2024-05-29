package eu.api.application.query.get_apps_by_service_name;

import eu.api.domain.modal.App;
import eu.api.domain.repository.IAppRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
class GetAppsByServiceNameQueryHandlerImpl implements IGetAppsByServiceNameQueryHandler {
    private final IAppRepository repository;

    @Override
    public List<App> handle(String serviceName) {
        log.info("GetAppsByServiceNameQueryHandlerImpl searching {}", serviceName);

        if (StringUtils.hasText(serviceName)) {
            return repository.findAllServices(serviceName);
        }
        return repository.findAll();
    }
}
