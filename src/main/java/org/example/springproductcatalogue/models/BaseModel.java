package org.example.springproductcatalogue.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.io.Serializable;
import java.util.Date;

/**
 * Base model containing common properties shared by other models.
 *
 * <p>This base model class serves as a foundation for other model classes in the application. It defines common
 * properties such as ID, creation timestamp, last update timestamp, and a flag indicating whether the entity
 * has been marked as deleted. These properties are inherited by subclasses and provide essential functionality
 * for data management and tracking.</p>
 */
@Getter
@Setter
@MappedSuperclass
public class BaseModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /* @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;*/
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date lastUpdatedOn;
    private boolean isDeleted;

}
