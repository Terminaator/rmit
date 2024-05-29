package eu.api.domain.modal;

import org.hibernate.search.mapper.pojo.bridge.IdentifierBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeFromDocumentIdentifierContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.IdentifierBridgeToDocumentIdentifierContext;

import java.util.UUID;

public class AppIdentifierBridge implements IdentifierBridge<AppIdentifier> {
    @Override
    public String toDocumentIdentifier(AppIdentifier appIdentifier, IdentifierBridgeToDocumentIdentifierContext identifierBridgeToDocumentIdentifierContext) {
        return appIdentifier.code().toString();
    }

    @Override
    public AppIdentifier fromDocumentIdentifier(String s, IdentifierBridgeFromDocumentIdentifierContext identifierBridgeFromDocumentIdentifierContext) {
        return new AppIdentifier(UUID.fromString(s));
    }
}
