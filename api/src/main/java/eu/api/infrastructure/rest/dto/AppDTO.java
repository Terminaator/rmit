package eu.api.infrastructure.rest.dto;

import eu.api.domain.modal.App;
import eu.api.domain.modal.AppGroup;
import eu.api.domain.modal.AppType;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public record AppDTO(
        UUID code,
        String name,
        AppGroup group,
        AppType type,
        String description,
        Integer cost,
        Timestamp modified,
        List<ServiceDTO> services
) {
    public static AppDTO from(App app) {
        return new AppDTO(
                app.getId().code(),
                app.getName(),
                app.getGroup(),
                app.getType(),
                app.getDescription(),
                app.getCost(),
                app.getModified(),
                ServiceDTO.froms(app.getServices())
        );
    }

    public static List<AppDTO> froms(List<App> apps) {
        if (CollectionUtils.isEmpty(apps)) {
            return List.of();
        }
        return apps
                .stream()
                .map(AppDTO::from)
                .toList();
    }
}
