package org.example.restserver.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ConfigurationProperties("error-trace")
@RestControllerAdvice
@Slf4j
public class GlobalException extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    private boolean stackTrace;

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.error("에러 ::: [BAD_REQUEST] ", ex);

        return ResponseEntity.status(status)
                .body(new ErrorResponse(null, ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleIOExceptions(Exception ex, WebRequest request) {
        List<StackTraceElement> stackTraces = null;
        if (stackTrace) {
            stackTraces = Arrays.asList(ex.getStackTrace());
        }
        logger.error("에러 ::: [IOException] ", ex);

        return new ErrorResponse(stackTraces, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
        List<StackTraceElement> stackTraces = null;
        if (stackTrace) {
            stackTraces = Arrays.asList(ex.getStackTrace());
        }
        logger.error("에러 ::: [AllException] ", ex);

        return new ErrorResponse(stackTraces, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleDatabaseExceptions(DataAccessException ex, WebRequest request) {
        List<StackTraceElement> stackTraces = null;
        if (stackTrace) {
            stackTraces = Arrays.asList(ex.getStackTrace());
        }
        logger.error("에러 ::: [DatabaseException] ", ex);

        return new ErrorResponse(stackTraces, "서버 에러용", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleRuntimeException(RuntimeException ex, WebRequest request) {
        List<StackTraceElement> stackTraces = null;

        if (stackTrace) {
            stackTraces = Arrays.asList(ex.getStackTrace());
            logger.error("에러 ::: [AllException] {}", ex.getMessage(), ex);
        } else {
            logger.error("에러 ::: [AllException] {}", ex.getMessage());
        }

        return new ErrorResponse(stackTraces, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}