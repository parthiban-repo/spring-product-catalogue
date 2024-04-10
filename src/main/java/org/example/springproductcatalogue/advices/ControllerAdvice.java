package org.example.springproductcatalogue.advices;

import org.example.springproductcatalogue.dtos.ErrorDto;
import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller Advice to handle exceptions globally in the application.
 * This advice provides centralized exception handling for controllers.
 * It catches specific exceptions and returns appropriate error responses.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    /**
     * Exception handler method to handle ProductNotFoundException.
     * This method handles ProductNotFoundException and returns a ResponseEntity
     * with an appropriate HTTP status code and error message.
     *
     * @param exception ProductNotFoundException that occurred.
     * @return ResponseEntity containing an ErrorDto with the error message.
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

    }

    /**
     * Exception handler method to handle all other exceptions.
     * This method handles any other exception that occurs within controllers.
     * It returns a ResponseEntity with an appropriate HTTP status code
     * and error message for generic exceptions.
     *
     * @param exception Exception that occurred.
     * @return ResponseEntity containing an ErrorDto with the error message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}