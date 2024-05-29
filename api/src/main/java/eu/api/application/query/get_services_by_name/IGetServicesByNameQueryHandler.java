package eu.api.application.query.get_services_by_name;

import eu.api.domain.modal.Service;

import java.util.List;

public interface IGetServicesByNameQueryHandler {
    List<Service> handle(String name);
}
