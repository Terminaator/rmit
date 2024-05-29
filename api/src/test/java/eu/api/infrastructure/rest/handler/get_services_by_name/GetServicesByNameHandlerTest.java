package eu.api.infrastructure.rest.handler.get_services_by_name;

import eu.api.infrastructure.TestE2EConfig;
import eu.api.application.command.create_app.CreateAppCommand;
import eu.api.application.command.create_app.ICreateAppCommandHandler;
import eu.api.application.command.create_service.CreateServiceCommand;
import eu.api.application.command.create_service.ICreateServiceCommandHandler;
import eu.api.domain.modal.App;
import eu.api.domain.modal.Service;
import eu.api.infrastructure.rest.dto.ServiceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;


class GetServicesByNameHandlerTest extends TestE2EConfig {
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    ICreateAppCommandHandler createApp;
    @Autowired
    ICreateServiceCommandHandler createService;

    @Test
    void givenNameParam_whenHandleCalled_thenReturnOkStatusAndServices() {
        App app = createApp.handle(new CreateAppCommand(
                "THIS is Test application",
                null,
                null,
                null,
                null
        ));

        Service service = createService.handle(new CreateServiceCommand(
                app.getId().code(),
                "Demo",
                null,
                null,
                null
        ));

        String[] values = app.getName().split(" ");

        for (String value : values) {
            EntityExchangeResult<List<ServiceDTO>> result = webTestClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/application/service")
                            .queryParam("name", value)
                            .build())
                    .exchange()
                    .expectStatus().isOk()
                    .expectBodyList(ServiceDTO.class)
                    .returnResult();

            List<ServiceDTO> services = Objects.requireNonNull(result.getResponseBody());

            assertTrue(services.stream().anyMatch(dto -> dto.code().equals(service.getId().code())));
        }
    }
}