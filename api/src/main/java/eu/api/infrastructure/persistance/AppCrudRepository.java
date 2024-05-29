package eu.api.infrastructure.persistance;

import eu.api.domain.modal.App;
import eu.api.domain.modal.AppIdentifier;
import org.springframework.data.repository.CrudRepository;

interface AppCrudRepository extends CrudRepository<App, AppIdentifier> {
}
