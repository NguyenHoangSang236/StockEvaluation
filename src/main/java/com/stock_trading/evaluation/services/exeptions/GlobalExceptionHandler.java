package com.stock_trading.evaluation.services.exeptions;


import com.stock_trading.evaluation.entities.api.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .code("FAILED")
                        .error(ex.getMessage())
                        .message("Forbidden")
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }

    @ResponseBody
    @ExceptionHandler(value = {DataNotFoundException.class})
    protected ResponseEntity<ApiResponse> handleDataNotFoundException(DataNotFoundException ex) {
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .code("FAILED")
                        .error(ex.getMessage())
                        .message("Data not found")
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .code("FAILED")
                        .error(ex.getMessage())
                        .message("Missing some request parameter")
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ApiResponse> handleException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .code("FAILED")
                        .error(ex.getMessage())
                        .message("Internal server error")
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
