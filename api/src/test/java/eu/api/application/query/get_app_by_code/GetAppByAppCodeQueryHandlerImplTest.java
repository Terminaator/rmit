package eu.api.application.query.get_app_by_code;

import eu.api.application.command.create_app.CreateAppCommand;
import eu.api.application.command.create_app.ICreateAppCommandHandler;
import eu.api.domain.exception.AppNotFoundException;
import eu.api.domain.modal.App;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


@ActiveProfiles(value = "integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetAppByAppCodeQueryHandlerImplTest {
    @Autowired
    GetAppByCodeQueryHandlerImpl handler;
    @Autowired
    ICreateAppCommandHandler createAppHandler;

    @Test
    void shouldReturnApp_whenHandleCalled_withAppCode() {
        CreateAppCommand createAppCommand = new CreateAppCommand(
                "TEST application",
                null,
                null,
                null,
                0
        );

        App app = createAppHandler.handle(createAppCommand);

        assertDoesNotThrow(() -> handler.handle(app.getId().code()));
    }

    @Test
    void shouldThrowAppNotFoundException_whenHandleCalled_withNonExistingAppCode() {
        UUID appCode = UUID.randomUUID();

        assertThrowsExactly(AppNotFoundException.class, () -> handler.handle(appCode));
    }
}