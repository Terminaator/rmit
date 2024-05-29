package eu.api.domain.modal;

import eu.api.domain.exception.ServiceDuplicateException;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import org.hibernate.search.mapper.pojo.automaticindexing.ReindexOnUpdate;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.IdentifierBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.*;
import org.jmolecules.ddd.types.AggregateRoot;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Indexed
@Table(name = "application")
public class App implements AggregateRoot<App, AppIdentifier> {
    @DocumentId(identifierBridge = @IdentifierBridgeRef(type = AppIdentifierBridge.class))
    private final AppIdentifier id;

    @FullTextField
    @NotBlank
    @Size(max = 255)
    private final String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_group")
    private final AppGroup group;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_type")
    private final AppType type;

    @Size(max = 20000)
    private final String description;

    @PositiveOrZero
    @Column(name = "app_cost")
    private final Integer cost;

    @NotNull
    @Column(name = "last_modified")
    private final Timestamp modified;

    @IndexedEmbedded
    @IndexingDependency(reindexOnUpdate = ReindexOnUpdate.SHALLOW)
    @JoinColumn(name = "appCode")
    private final List<Service> services = new ArrayList<>();

    public App(
            String name,
            AppGroup group,
            AppType type,
            String description,
            Integer cost
    ) {
        this.id = new AppIdentifier(UUID.randomUUID());
        this.name = name;
        this.group = group;
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.modified = Timestamp.from(Instant.now());
    }

    public App addService(Service service) {
        if (services.stream().anyMatch(existingAppService -> existingAppService.getId().equals(service.getId()))) {
            throw new ServiceDuplicateException();
        }

        services.add(service);
        return this;
    }
}
