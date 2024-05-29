package eu.api.domain.exception;

public class ServiceDuplicateException extends AppException {
    public ServiceDuplicateException() {
        super("Service already exists");
    }
}
