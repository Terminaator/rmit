package eu.api.domain.exception;

public class AppNotFoundException extends AppException {
    public AppNotFoundException() {
        super("App not found");
    }
}
