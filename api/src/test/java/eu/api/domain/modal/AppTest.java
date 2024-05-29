package eu.api.domain.modal;

import eu.api.domain.exception.ServiceDuplicateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void addExistingService_shouldThrowServiceDuplicateException() {
        App app = new App(
                null,
                null,
                null,
                null,
                null
        );

        Service service = new Service(
                app.getId().code(),
                null,
                null,
                null,
                null
        );

        assertDoesNotThrow(() -> app.addService(service));
        assertThrowsExactly((ServiceDuplicateException.class), () -> app.addService(service));
    }
}