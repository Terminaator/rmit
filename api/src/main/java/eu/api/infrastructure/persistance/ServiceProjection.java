package eu.api.infrastructure.persistance;

import eu.api.domain.modal.ServiceIdentifier;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.ProjectionConstructor;

@ProjectionConstructor
public record ServiceProjection(ServiceIdentifier serviceCode) {
}