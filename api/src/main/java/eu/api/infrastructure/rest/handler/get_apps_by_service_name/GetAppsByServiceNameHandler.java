package eu.api.infrastructure.rest.handler.get_apps_by_service_name;

import eu.api.application.query.get_apps_by_service_name.IGetAppsByServiceNameQueryHandler;
import eu.api.infrastructure.rest.dto.AppDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAppsByServiceNameHandler {
    private final IGetAppsByServiceNameQueryHandler handler;

    public Mono<ServerResponse> handle(ServerRequest request) {
        log.info("GetAppsByServiceNameHandler params {}", request.queryParams());
        List<String> names = request.queryParams().get("name");

        String name = !CollectionUtils.isEmpty(names) ? names.getFirst() : "";

        log.info("GetAppsByServiceNameHandler searching {}", name);
        return ok()
                .bodyValue(
                        AppDTO.froms(handler.handle(name))
                );
    }
}
