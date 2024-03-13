package org.example.springproductcatalogue.advices;

import org.example.springproductcatalogue.dtos.ErrorDto;
import org.example.springproductcatalogue.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controller Advice to handle exceptions
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    /**
     * Controller Advice method to handle ProductNotFoundException
     *
     * @param exception ProductNotFoundException
     * @return ResponseEntity
     */
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception) {

        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);

    }

    /**
     * Controller Advice method to handle all exceptions
     *
     * @param exception Exception
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}