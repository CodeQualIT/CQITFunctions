package nl.cqit.function.poc.java.boxedhello.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class BoxedHelloControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("Unhandled exception", ex);
        if (ex instanceof JsonParseException) {
            return getExceptionProblemDetail(
                    HttpStatus.BAD_REQUEST,
                    "/errors/validation-failed",
                    "Validation failed",
                    ex
            );
        }

        return getExceptionProblemDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "/errors/unhandled-exception",
                "Encountered an unhandled exception",
                ex
        );
    }

    private ProblemDetail getExceptionProblemDetail(
            HttpStatus status,
            String problemType, String problemDetail, RuntimeException ex) {
        ProblemDetail body = ProblemDetail.forStatusAndDetail(
                HttpStatusCode.valueOf(status.value()),
                ex.getLocalizedMessage()
        );
        body.setType(URI.create(problemType));
        body.setTitle(problemDetail);
        body.setProperty("exceptionType", ex.getClass().getName());
        body.setProperty("stacktrace", ExceptionUtils.getStackTrace(ex));
        if (ex.getCause() != null && ex.getCause() != ex) {
            body.setProperty("cause", getCauseMap(ex.getCause()));
        }
        return body;
    }

    private Map<String, Object> getCauseMap(Throwable cause) {
        Map<String, Object> causeMap = new HashMap<>();
        causeMap.put("exceptionType", cause.getClass().getName());
        causeMap.put("message", cause.getLocalizedMessage());
        causeMap.put("stacktrace", ExceptionUtils.getStackTrace(cause));
        if (cause.getCause() != null && cause.getCause() != cause) {
            causeMap.put("cause", getCauseMap(cause.getCause()));
        }
        return Map.copyOf(causeMap);
    }
}
