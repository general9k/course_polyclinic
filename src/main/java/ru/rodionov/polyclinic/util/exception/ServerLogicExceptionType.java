package ru.rodionov.polyclinic.util.exception;

import org.springframework.http.HttpStatus;
import ru.rodionov.polyclinic.util.exception.ServerError.CodeEnum;

public enum ServerLogicExceptionType {
    VALIDATION_ERROR(CodeEnum.VALIDATION_ERROR, HttpStatus.BAD_REQUEST, String.format("Параметры: %s не могут быть пустыми; некорректный тип данных в параметре %s", "{field}", "{field}"), "{field}"),
    AUTHENTICATION_ERROR(CodeEnum.AUTHENTICATION_ERROR, HttpStatus.UNAUTHORIZED, String.format("Ошибка в токене: %s", "{object}"), "{object}"),
    FORBIDDEN_ERROR(CodeEnum.ACCESS_DENIED, HttpStatus.FORBIDDEN, "Недостаточно прав", "{reason}"),
    NOT_FOUND(CodeEnum.ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND, String.format("Объект %s не найден", "{object}"), "{object}"),
    ALREADY_EXISTS(CodeEnum.ENTITY_ALREADY_EXISTS, HttpStatus.CONFLICT, String.format("Объект %s уже существует", "{object}"), "{object}"),
    CONFLICT(CodeEnum.ENTITY_ACCESS_CONFLICT, HttpStatus.CONFLICT, String.format("Конфликт обращения к объекту: %s", "{object}"), "{object}"),
    UNSUPPORTED_MEDIA_TYPE_ERROR(CodeEnum.UNSUPPORTED_MEDIA_TYPE, HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Не поддерживаемый тип файла: {mimeType}", "{mimeType}"),
    ENTITY_INVARIABLE(CodeEnum.ENTITY_INVARIABLE, HttpStatus.UNPROCESSABLE_ENTITY, String.format("Объект %s не может быть изменен", "{object}"), "{object}"),
    DELETE_ERROR(CodeEnum.VALIDATION_ERROR, HttpStatus.BAD_REQUEST, String.format("Ошибка удаления: %s", "{object}"), "{object}"),
    REPUTATION_ERROR(CodeEnum.VALIDATION_ERROR, HttpStatus.BAD_REQUEST, String.format("Ошибка репутации: %s", "{object}"), "{object}"),
    ANSWERED_ERROR(CodeEnum.VALIDATION_ERROR, HttpStatus.BAD_REQUEST, String.format("Ошибка: %s", "{object}"), "{object}"),
    GATEWAY_ERROR(CodeEnum.SERVICE_CALL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, String.format("Ошибка при обращении к внешнему сервису: %s", "{reason}"), "{reason}"),
    SERVICE_ERROR(CodeEnum.SERVICE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR, String.format("Внутренняя ошибка сервиса: %s", "{reason}"), "{reason}");

    private final CodeEnum errorCode;
    private final HttpStatus httpCode;
    private final String message;
    private final String replacePattern;

    public CodeEnum getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getHttpCode() {
        return this.httpCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getReplacePattern() {
        return this.replacePattern;
    }

    private ServerLogicExceptionType(final CodeEnum errorCode, final HttpStatus httpCode, final String message, final String replacePattern) {
        this.errorCode = errorCode;
        this.httpCode = httpCode;
        this.message = message;
        this.replacePattern = replacePattern;
    }
}
