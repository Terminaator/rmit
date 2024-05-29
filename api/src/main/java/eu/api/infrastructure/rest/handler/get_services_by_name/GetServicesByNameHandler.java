package eu.api.infrastructure.rest.handler.get_services_by_name;

import eu.api.application.query.get_services_by_name.IGetServicesByNameQueryHandler;
import eu.api.infrastructure.rest.dto.ServiceDTO;
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
public class GetServicesByNameHandler {
    private final IGetServicesByNameQueryHandler handler;

    public Mono<ServerResponse> handle(ServerRequest request) {
        log.info("GetServicesByNameHandler params {}", request.queryParams());
        List<String> names = request.queryParams().get("name");

        String name = !CollectionUtils.isEmpty(names) ? names.getFirst() : "";

        log.info("GetServicesByNameHandler searching {}", name);
        return ok()
                .bodyValue(
                        ServiceDTO.froms(handler.handle(name))
                );
    }
}
