package eu.api.domain.modal;

import jakarta.persistence.Column;
import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

public record ServiceIdentifier(@Column(name = "service_code") UUID code) implements Identifier {
}
