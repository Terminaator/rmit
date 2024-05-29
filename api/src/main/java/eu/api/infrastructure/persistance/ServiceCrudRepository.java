package eu.api.infrastructure.persistance;

import eu.api.domain.modal.Service;
import eu.api.domain.modal.ServiceIdentifier;
import org.springframework.data.repository.CrudRepository;

interface ServiceCrudRepository extends CrudRepository<Service, ServiceIdentifier> {
}
