package eu.api.domain.modal;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.search.mapper.pojo.bridge.mapping.annotation.ValueBridgeRef;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import org.jmolecules.ddd.types.Association;
import org.jmolecules.ddd.types.Entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Getter
@Table(name = "app_service")
public class Service implements Entity<App, ServiceIdentifier> {
    @KeywordField(name = "serviceCode", valueBridge = @ValueBridgeRef(type = ServiceIdentifierBridge.class))
    private final ServiceIdentifier id;

    private final Association<App, AppIdentifier> appCode;

    @FullTextField
    @NotBlank
    @Size(max = 255)
    private final String name;

    @Enumerated(EnumType.STRING)
    private final ServiceType type;

    @Enumerated(EnumType.STRING)
    private final ServiceSubType subType;

    @Size(max = 20000)
    private final String description;

    @NotNull
    @Column(name = "last_modified")
    private final Timestamp modified;

    public Service(
            UUID appCode,
            String name,
            ServiceType type,
            ServiceSubType subType,
            String description
    ) {
        this.id = new ServiceIdentifier(UUID.randomUUID());
        this.appCode = Association.forId(new AppIdentifier(appCode));
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.description = description;
        this.modified = Timestamp.from(Instant.now());
    }
}
