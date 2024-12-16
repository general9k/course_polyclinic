package ru.rodionov.polyclinic.util.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CoreExceptionAdvice {

    private static final String FORMAT_ARGUMENTS_NOT_VALID = "Некорректный тип данных в параметрах: %s.";
    private static final String FORMAT_ARGUMENTS_IS_EMPTY = "Параметры: %s ,- не могут быть пустыми.";
    private static final String EXCEPTION_AUTHENTICATION = "Ошибка аутентификации.";

    @ExceptionHandler(ServerLogicException.class)
    ResponseEntity<ServerError> handleServerLogicException(final ServerLogicException e) {
        ServerError servererror = toServerError(e.getType(), e);
        return ResponseEntity.status(e.getType().getHttpCode())
                .body(servererror);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        // Отсутствие / невалидность параметров в request в body
    ResponseEntity<ServerError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        ServerLogicExceptionType type = ServerLogicExceptionType.VALIDATION_ERROR;
        StringBuilder errorMessage = new StringBuilder();

        String emptyFields = e.getFieldErrors().stream()
                .filter(error -> error.getCode().equals("NotNull"))
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        String invalidTypeFields = e.getFieldErrors().stream()
                .filter(error -> !error.getCode().equals("NotNull"))
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        if (!emptyFields.isEmpty()) {
            errorMessage.append(String.format(FORMAT_ARGUMENTS_IS_EMPTY, emptyFields));
        }
        if (!invalidTypeFields.isEmpty()) {
            errorMessage.append(String.format(FORMAT_ARGUMENTS_NOT_VALID, invalidTypeFields));
        }

        ServerError servererror = new ServerError(type.getErrorCode(),
                !errorMessage.isEmpty() ? errorMessage.toString() : e.getMessage());
        return ResponseEntity.status(type.getHttpCode()).body(servererror);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<ServerError> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException e) {
        ServerLogicExceptionType type = ServerLogicExceptionType.VALIDATION_ERROR;
        String msg = String.format(FORMAT_ARGUMENTS_NOT_VALID, e.getName());
        ServerLogicException ex = new ServerLogicException(msg, type);
        ServerError servererror = toServerError(type, ex);
        return ResponseEntity.status(type.getHttpCode()).body(servererror);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ServerError> handleHttpMessageNotReadableException(final HttpMessageNotReadableException e) {
        ServerLogicExceptionType type = ServerLogicExceptionType.VALIDATION_ERROR;
        ServerError servererror;

        Throwable cause = e.getCause();
        if (cause instanceof JsonMappingException jsonEx) {
            String fields = jsonEx.getPath()
                    .stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .collect(Collectors.joining(", "));
            String msg = String.format(FORMAT_ARGUMENTS_NOT_VALID, fields);
            servererror = new ServerError(type.getErrorCode(), msg);
        } else {
            servererror = toServerError(type, e);
        }
        return ResponseEntity.status(type.getHttpCode()).body(servererror);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
        // Отсутствие параметра в header
    ResponseEntity<ServerError> handleMissingRequestHeaderException(final MissingRequestHeaderException e) {
        ServerLogicExceptionType type = ServerLogicExceptionType.AUTHENTICATION_ERROR;
        ServerError servererror = new ServerError(type.getErrorCode(), EXCEPTION_AUTHENTICATION);
        return ResponseEntity.status(type.getHttpCode()).body(servererror);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
        // Отсутствие параметра в request в path
    ResponseEntity<ServerError> handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        ServerLogicExceptionType type = ServerLogicExceptionType.VALIDATION_ERROR;
        ServerError servererror = new ServerError(type.getErrorCode(),
                String.format(FORMAT_ARGUMENTS_IS_EMPTY, e.getParameterName()));
        return ResponseEntity.status(type.getHttpCode()).body(servererror);
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    ResponseEntity<ServerError> handleServletRequestBindingException(final ServletRequestBindingException e) {
        ServerLogicExceptionType type = ServerLogicExceptionType.VALIDATION_ERROR;
        ServerError servererror = toServerError(type, e);
        return ResponseEntity.status(type.getHttpCode()).body(servererror);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ServerError> handleConstraintViolationException(final ConstraintViolationException e) {
        ServerLogicExceptionType type = ServerLogicExceptionType.VALIDATION_ERROR;
        ServerError servererror = toServerError(type, e);
        return ResponseEntity.status(type.getHttpCode()).body(servererror);
    }

    private ServerError toServerError(final ServerLogicExceptionType type, final Throwable e) {
        log.warn("", e);
        return new ServerError(type.getErrorCode(), e.getMessage());
    }
}