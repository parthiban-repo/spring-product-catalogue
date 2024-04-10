package org.example.springproductcatalogue.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO class: Represents an error message.
 *
 * <p>This DTO class is used to encapsulate error messages returned by the application.
 * It contains a single field to store the error message.</p>
 */
@Getter
@Setter
public class ErrorDto {
    private String message;
}
