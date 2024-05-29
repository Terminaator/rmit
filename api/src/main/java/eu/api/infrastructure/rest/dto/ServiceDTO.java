package eu.api.infrastructure.rest.dto;

import eu.api.domain.modal.Service;
import eu.api.domain.modal.ServiceSubType;
import eu.api.domain.modal.ServiceType;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public record ServiceDTO(
        UUID code,
        UUID appCode,
        String name,
        ServiceType type,
        ServiceSubType subType,
        String description,
        Timestamp modified
) {
    public static ServiceDTO from(Service appService) {
        return new ServiceDTO(
                appService.getId().code(),
                appService.getAppCode().getId().code(),
                appService.getName(),
                appService.getType(),
                appService.getSubType(),
                appService.getDescription(),
                appService.getModified()
        );
    }

    public static List<ServiceDTO> froms(List<Service> appServices) {
        if (CollectionUtils.isEmpty(appServices)) {
            return List.of();
        }
        return appServices
                .stream()
                .map(ServiceDTO::from)
                .toList();
    }
}
