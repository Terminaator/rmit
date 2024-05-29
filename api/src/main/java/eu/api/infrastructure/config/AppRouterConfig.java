package eu.api.infrastructure.config;

import eu.api.domain.exception.AppNotFoundException;
import eu.api.infrastructure.rest.handler.create_app.CreateAppHandler;
import eu.api.infrastructure.rest.handler.create_service.CreateAppServiceHandler;
import eu.api.infrastructure.rest.handler.get_apps_by_service_name.GetAppsByServiceNameHandler;
import eu.api.infrastructure.rest.handler.get_services_by_name.GetServicesByNameHandler;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
class AppRouterConfig {
    private final CreateAppHandler createApp;
    private final CreateAppServiceHandler createAppService;
    private final GetAppsByServiceNameHandler getAppsByServiceNameHandler;
    private final GetServicesByNameHandler getServicesByNameHandler;

    @Bean
    RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route()
                .GET("/application", getAppsByServiceNameHandler::handle)
                .POST("/application", createApp::handle)
                .GET("/application/service", getServicesByNameHandler::handle)
                .POST("/application/service", createAppService::handle)
                .filter(exceptionFilter())
                .build();
    }

    private HandlerFilterFunction<ServerResponse, ServerResponse> exceptionFilter() {
        return (request, next) -> next.handle(request)
                .onErrorResume(Exception.class, this::fallback);
    }

    private String getExceptionMessage(Exception exp) {
        String message = exp.getMessage();
        return message != null ? message : "unknown error";
    }

    private ProblemDetail createProblemDetail(HttpStatus status, Exception exp) {
        return ProblemDetail.forStatusAndDetail(status, getExceptionMessage(exp));
    }

    private Mono<ServerResponse> createServerResponse(HttpStatus status, Exception exp) {
        return ServerResponse.status(status).bodyValue(createProblemDetail(status, exp));
    }

    private Mono<ServerResponse> fallback(Exception exp) {
        log.error("HANDLER fallback", exp);

        if (exp instanceof ConstraintViolationException) {
            return createServerResponse(HttpStatus.BAD_REQUEST, exp);
        } else if (exp instanceof AppNotFoundException) {
            return createServerResponse(HttpStatus.NOT_FOUND, exp);
        }

        return createServerResponse(HttpStatus.INTERNAL_SERVER_ERROR, exp);
    }
}
