package eu.api.domain.modal;

import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeFromIndexedValueContext;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

import java.util.UUID;

public class ServiceIdentifierBridge implements ValueBridge<ServiceIdentifier, String> {
    @Override
    public String toIndexedValue(ServiceIdentifier value, ValueBridgeToIndexedValueContext context) {
        return value.code().toString();
    }

    @Override
    public ServiceIdentifier fromIndexedValue(String value, ValueBridgeFromIndexedValueContext context) {
        return new ServiceIdentifier(UUID.fromString(value));
    }
}