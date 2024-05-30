package eu.api.infrastructure.rest.filter;

import eu.api.domain.exception.AppNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
public class ErrorFilter {
    private ErrorFilter() {

    }

    public static HandlerFilterFunction<ServerResponse, ServerResponse> filter() {
        return (request, next) -> next.handle(request)
                .onErrorResume(Exception.class, ErrorFilter::fallback);
    }

    private static String getExceptionMessage(Exception exp) {
        String message = exp.getMessage();
        return message != null ? message : "unknown error";
    }

    private static ProblemDetail createProblemDetail(HttpStatus status, Exception exp) {
        return ProblemDetail.forStatusAndDetail(status, getExceptionMessage(exp));
    }

    private static Mono<ServerResponse> createServerResponse(HttpStatus status, Exception exp) {
        return ServerResponse.status(status).bodyValue(createProblemDetail(status, exp));
    }

    private static Mono<ServerResponse> fallback(Exception exp) {
        log.error("HANDLER fallback", exp);

        if (exp instanceof ConstraintViolationException) {
            return createServerResponse(HttpStatus.BAD_REQUEST, exp);
        } else if (exp instanceof AppNotFoundException) {
            return createServerResponse(HttpStatus.NOT_FOUND, exp);
        }

        return createServerResponse(HttpStatus.INTERNAL_SERVER_ERROR, exp);
    }
}
