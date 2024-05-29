package eu.api.infrastructure.persistance;

import eu.api.domain.modal.App;
import eu.api.domain.modal.Service;
import eu.api.domain.modal.ServiceIdentifier;
import eu.api.domain.repository.IServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
class ServiceRepositoryAdapter implements IServiceRepository {
    private final ServiceCrudRepository repository;
    private final EntityManager manager;

    @Transactional
    @Override
    public List<Service> findAllApps(String appName) {
        SearchSession searchSession = Search.session(manager);
        List<ServiceIdentifier> identifiers = searchSession.search(App.class)
                .select(f ->
                        f.object("services").as(ServiceProjection.class).multi()
                )
                .where(f -> f.match().field("name").matching(appName))
                .fetchAll()
                .hits()
                .stream()
                .flatMap(Collection::stream)
                .map(ServiceProjection::serviceCode)
                .toList();
        return (List<Service>) repository.findAllById(identifiers);
    }

    @Override
    public List<Service> findAll() {
        return (List<Service>) repository.findAll();
    }
}
