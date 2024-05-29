package eu.api.infrastructure.config;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnExpression("${spring.jpa.properties.hibernate.search.enabled:false}")
class ElasticInitialisingConfig implements ApplicationListener<ApplicationReadyEvent> {
    private final EntityManager entityManager;

    @SneakyThrows
    @Transactional
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Initialising ElasticInitialisingConfig...");
        SearchSession searchSession = Search.session(entityManager);
        searchSession.massIndexer().startAndWait();
    }
}
