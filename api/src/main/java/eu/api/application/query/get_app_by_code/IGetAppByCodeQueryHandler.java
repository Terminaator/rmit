package eu.api.application.query.get_app_by_code;

import eu.api.domain.modal.App;

import java.util.UUID;

public interface IGetAppByCodeQueryHandler {
    App handle(UUID appCode);
}
