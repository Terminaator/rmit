package eu.api.domain.repository;

import eu.api.domain.modal.App;
import eu.api.domain.modal.AppIdentifier;

import java.util.List;
import java.util.Optional;

public interface IAppRepository {
    App save(App app);

    Optional<App> findById(AppIdentifier appCode);

    List<App> findAll();

    List<App> findAllServices(String serviceName);
}
