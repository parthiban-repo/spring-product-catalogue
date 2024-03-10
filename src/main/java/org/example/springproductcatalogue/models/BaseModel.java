package org.example.springproductcatalogue.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

/**
 * Base model that has common properties
 */
@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    private Long id;
    private Date createdOn;
    private Date lastUpdatedOn;
    private Boolean isDeleted;
}
