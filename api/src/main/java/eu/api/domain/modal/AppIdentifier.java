package eu.api.domain.modal;

import jakarta.persistence.Column;
import org.jmolecules.ddd.types.Identifier;

import java.util.UUID;

public record AppIdentifier(@Column(name = "app_code") UUID code) implements Identifier {
}
