package eu.api.application.query.get_apps_by_service_name;

import eu.api.domain.modal.App;

import java.util.List;

public interface IGetAppsByServiceNameQueryHandler {
    List<App> handle(String serviceName);
}
