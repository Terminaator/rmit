package eu.api.infrastructure.rest.handler.create_service;

import eu.api.application.command.create_service.CreateServiceCommand;
import eu.api.application.command.create_service.ICreateServiceCommandHandler;
import eu.api.infrastructure.rest.dto.ServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Component
@RequiredArgsConstructor
public class CreateServiceHandler {
    private final ICreateServiceCommandHandler handler;

    public Mono<ServerResponse> handle(ServerRequest request) {
        return request
                .bodyToMono(CreateServiceCommand.class)
                .map(handler::handle)
                .map(ServiceDTO::from)
                .flatMap(appService -> created(URI.create("/application/service/" + appService.code())).bodyValue(appService));
    }
}
