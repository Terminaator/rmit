package eu.api.application.command.create_app;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ActiveProfiles(value = "integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateAppCommandHandlerImplTest {
    @Autowired
    CreateAppCommandHandlerImpl handler;

    @Test
    void shouldCreateApp_whenHandleCalled_withValidCommand() {
        CreateAppCommand command = new CreateAppCommand(
                "TEST Application",
                null,
                null,
                null,
                null
        );

        assertDoesNotThrow(() -> handler.handle(command));
    }

    @Test
    void shouldThrowConstraintViolationException_whenHandleCalled_withInvalidCommand() {
        CreateAppCommand command = new CreateAppCommand(
                "",
                null,
                null,
                "a".repeat(20001),
                -1
        );

        assertThrowsExactly(ConstraintViolationException.class, () -> handler.handle(command));
    }
}