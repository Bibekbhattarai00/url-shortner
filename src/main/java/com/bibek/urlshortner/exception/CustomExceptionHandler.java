package com.bibek.urlshortner.exception;


import com.bibek.urlshortner.constants.MessageConstants;
import com.bibek.urlshortner.generic.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

    private final CustomMessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public GenericResponse<Map<String, String>> invalidArgumentHandler(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        String errorMessage = errors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        map.put("errorMessage", errorMessage);
        return GenericResponse.<Map<String, String>>builder()
                .success(false)
                .message(messageSource.get(MessageConstants.METHOD_INVALID.getCode()))
                .data(map)
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public GenericResponse<Map<String, String>> userNotFoundException(NotFoundException e) {
        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", e.message);
        return GenericResponse.<Map<String, String>>builder()
                .success(false)
                .message(messageSource.get(MessageConstants.NOT_FOUND_EXCEPTION.getCode()))
                .data(map)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public GenericResponse<Map<String, String>> globalException(Exception e) {
        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", e.getMessage());
        return GenericResponse.<Map<String, String>>builder()
                .success(false)
                .message(messageSource.get(MessageConstants.EXCEPTION.getCode()))
                .data(map)
                .build();
    }

}
