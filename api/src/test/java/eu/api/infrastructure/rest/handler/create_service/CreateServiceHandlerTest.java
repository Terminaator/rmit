package eu.api.infrastructure.rest.handler.create_service;

import eu.api.infrastructure.TestE2EConfig;
import eu.api.application.command.create_app.CreateAppCommand;
import eu.api.application.command.create_app.ICreateAppCommandHandler;
import eu.api.application.command.create_service.CreateServiceCommand;
import eu.api.domain.modal.App;
import eu.api.domain.modal.ServiceSubType;
import eu.api.domain.modal.ServiceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.hamcrest.Matchers.matchesPattern;

class CreateServiceHandlerTest extends TestE2EConfig {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ICreateAppCommandHandler createAppHandler;

    @Test
    void givenValidRequest_whenHandleCalled_thenReturnCreatedStatusAndAppServiceBody() {
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
                ServiceType.JDBC,
                ServiceSubType.REST,
                "this is test"
        );

        webTestClient
                .post().uri("/application/service")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(command)
                .exchange()
                .expectAll(
                        spec -> spec.expectStatus().isCreated(),
                        spec -> spec.expectHeader().value("location", matchesPattern("/application/service/[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")),
                        spec -> spec.expectBody()
                                .jsonPath("code").isNotEmpty()
                                .jsonPath("appCode").isEqualTo(command.appCode().toString())
                                .jsonPath("name").isEqualTo(command.name())
                                .jsonPath("type").isEqualTo(command.type().name())
                                .jsonPath("subType").isEqualTo(command.subType().name())
                                .jsonPath("description").isEqualTo(command.description())
                                .jsonPath("modified").isNotEmpty()
                                .jsonPath("$.length()").isEqualTo(7)
                )
                .expectBody().consumeWith(System.out::println);
    }

    @Test
    void givenInvalidRequest_whenHandleCalled_thenReturnBadRequest() {
        CreateServiceCommand command = new CreateServiceCommand(
                null,
                null,
                null,
                null,
                "a".repeat(20001)
        );

        webTestClient
                .post().uri("/application/service")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(command)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().consumeWith(System.out::println);
    }

    @Test
    void givenNonExistingAppCodeRequest_whenHandleCalled_thenReturnNotFoundRequest() {
        CreateServiceCommand command = new CreateServiceCommand(
                UUID.randomUUID(),
                "TEST application service",
                null,
                null,
                null
        );

        webTestClient
                .post().uri("/application/service")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(command)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().consumeWith(System.out::println);
    }
}