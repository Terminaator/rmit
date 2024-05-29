package eu.api.application.command.create_service;

import eu.api.application.command.create_app.CreateAppCommand;
import eu.api.application.command.create_app.ICreateAppCommandHandler;
import eu.api.domain.modal.App;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ActiveProfiles(value = "integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateServiceCommandHandlerImplTest {
    @Autowired
    CreateServiceCommandHandlerImpl createAppServiceHandler;
    @Autowired
    ICreateAppCommandHandler createAppHandler;

    @Test
    void shouldCreateService_whenHandleCalled_withValidCommand() {
        CreateAppCommand createAppCommand = new CreateAppCommand(
                "TEST application",
                null,
                null,
                null,
                0
        );

        App app = createAppHandler.handle(createAppCommand);

        CreateServiceCommand command = new CreateServiceCommand(
                app.getId().code(),
                "TEST application service",
                null,
                null,
                null
        );

        assertDoesNotThrow(() -> createAppServiceHandler.handle(command));
    }

    @Test
    void shouldThrowConstraintViolationException_whenHandleCalled_withInvalidCommand() {
        CreateServiceCommand command = new CreateServiceCommand(
                null,
                null,
                null,
                null,
                "a".repeat(20001)
        );

        assertThrowsExactly(ConstraintViolationException.class, () -> createAppServiceHandler.handle(command));
    }
}