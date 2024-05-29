package eu.api.domain.exception;

abstract class AppException extends RuntimeException {
    AppException(String message) {
        super(message);
    }
}
