package com.web.ya_pro.web.controller.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

public class GlobalExceptionHandler {
    @ExceptionHandler
    public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NoteNotFoundException.class)
    protected ResponseEntity<ApiError> handleMeetNotFoundException(NoteNotFoundException ex, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(errors), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Обработчик исключений по-умолчанию
     *
     * @param ex      Общее исключение
     * @param body    Список ошибок
     * @param headers HTTP заголовки
     * @param status  HTTP статус
     * @param request Запрос
     * @return возвращаемая клиенту ResponseEntity
     */
    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError body,
                                                               HttpHeaders headers, HttpStatus status,
                                                               WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, RequestAttributes.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(body, headers, status);
    }
}