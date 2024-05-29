package eu.api.infrastructure.rest.handler.create_app;

import eu.api.infrastructure.TestE2EConfig;
import eu.api.application.command.create_app.CreateAppCommand;
import eu.api.domain.modal.AppGroup;
import eu.api.domain.modal.AppType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.matchesPattern;

class CreateAppHandlerTest extends TestE2EConfig {
    @Autowired
    WebTestClient webTestClient;

    @Test
    void givenValidRequest_whenHandleCalled_thenReturnCreatedStatusAndAppBody() {
        CreateAppCommand command = new CreateAppCommand(
                "TEST application",
                AppGroup.BUSINESS_LOGIC,
                AppType.JAVA,
                "This is TEST application",
                10
        );

        webTestClient
                .post().uri("/application")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(command)
                .exchange()
                .expectAll(
                        spec -> spec.expectStatus().isCreated(),
                        spec -> spec.expectHeader().value("location", matchesPattern("/application/[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$")),
                        spec -> spec.expectBody()
                                .jsonPath("code").isNotEmpty()
                                .jsonPath("name").isEqualTo(command.name())
                                .jsonPath("group").isEqualTo(command.group().name())
                                .jsonPath("type").isEqualTo(command.type().name())
                                .jsonPath("description").isEqualTo(command.description())
                                .jsonPath("cost").isEqualTo(command.cost())
                                .jsonPath("modified").isNotEmpty()
                                .jsonPath("services").isEmpty()
                                .jsonPath("$.length()").isEqualTo(8)
                )
                .expectBody().consumeWith(System.out::println);
    }

    @Test
    void givenInvalidRequest_whenHandleCalled_thenReturnBadRequest() {
        CreateAppCommand command = new CreateAppCommand(
                "",
                null,
                null,
                "a".repeat(20001),
                -1
        );

        webTestClient
                .post().uri("/application")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(command)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().consumeWith(System.out::println);
    }
}