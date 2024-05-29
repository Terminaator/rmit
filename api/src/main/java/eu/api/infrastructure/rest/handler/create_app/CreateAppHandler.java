package eu.api.infrastructure.rest.handler.create_app;

import eu.api.application.command.create_app.CreateAppCommand;
import eu.api.application.command.create_app.ICreateAppCommandHandler;
import eu.api.infrastructure.rest.dto.AppDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Component
@RequiredArgsConstructor
public class CreateAppHandler {
    private final ICreateAppCommandHandler handler;

    public Mono<ServerResponse> handle(ServerRequest request) {
        return request
                .bodyToMono(CreateAppCommand.class)
                .map(handler::handle)
                .map(AppDTO::from)
                .flatMap(app -> created(URI.create("/application/" + app.code())).bodyValue(app));
    }
}
