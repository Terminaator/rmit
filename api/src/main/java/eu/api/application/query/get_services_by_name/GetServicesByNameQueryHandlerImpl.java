package eu.api.application.query.get_services_by_name;

import eu.api.domain.modal.Service;
import eu.api.domain.repository.IServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
class GetServicesByNameQueryHandlerImpl implements IGetServicesByNameQueryHandler {
    private final IServiceRepository repository;

    @Override
    public List<Service> handle(String appName) {
        if (StringUtils.hasText(appName)) {
            return repository.findAllApps(appName);
        }
        return repository.findAll();
    }
}
