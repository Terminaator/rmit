package eu.api.infrastructure.persistance;

import eu.api.domain.modal.App;
import eu.api.domain.modal.AppIdentifier;
import eu.api.domain.repository.IAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class AppRepositoryAdapter implements IAppRepository {
    private final AppCrudRepository repository;
    private final EntityManager manager;

    @Override
    public App save(App app) {
        return repository.save(app);
    }

    @Override
    public Optional<App> findById(AppIdentifier appCode) {
        return repository.findById(appCode);
    }

    @Override
    public List<App> findAll() {
        return (List<App>) repository.findAll();
    }

    @Transactional
    @Override
    public List<App> findAllServices(String serviceName) {
        SearchSession searchSession = Search.session(manager);
        return searchSession.search(App.class)
                .where(f -> f.match()
                        .field("services.name")
                        .matching(serviceName))
                .fetchAll()
                .hits();
    }
}
