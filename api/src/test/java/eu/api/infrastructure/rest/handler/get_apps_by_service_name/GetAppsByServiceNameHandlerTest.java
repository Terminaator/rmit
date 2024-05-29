package eu.api.infrastructure.rest.handler.get_apps_by_service_name;

import eu.api.infrastructure.TestE2EConfig;
import eu.api.application.command.create_app.CreateAppCommand;
import eu.api.application.command.create_app.ICreateAppCommandHandler;
import eu.api.application.command.create_service.CreateServiceCommand;
import eu.api.application.command.create_service.ICreateServiceCommandHandler;
import eu.api.domain.modal.App;
import eu.api.domain.modal.Service;
import eu.api.infrastructure.rest.dto.AppDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GetAppsByServiceNameHandlerTest extends TestE2EConfig {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ICreateAppCommandHandler createApp;
    @Autowired
    ICreateServiceCommandHandler createService;

    @Test
    void givenNameParam_whenHandleCalled_thenReturnOkStatusAndApps() {
        App app = createApp.handle(new CreateAppCommand(
                "DEMO",
                null,
                null,
                null,
                null
        ));

        Service service = createService.handle(new CreateServiceCommand(
                app.getId().code(),
                "this iS DemO serVICe",
                null,
                null,
                null
        ));

        String[] values = service.getName().split(" ");

        for (String value : values) {
            EntityExchangeResult<List<AppDTO>> result = webTestClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/application")
                            .queryParam("name", value)
                            .build())
                    .exchange()
                    .expectStatus().isOk()
                    .expectBodyList(AppDTO.class)
                    .returnResult();

            List<AppDTO> services = Objects.requireNonNull(result.getResponseBody());

            assertTrue(services.stream().anyMatch(dto -> dto.code().equals(app.getId().code())));
        }
    }
}