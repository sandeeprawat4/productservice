package dev.sandeep.productservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(generator = "sandeep")
    @GenericGenerator(name = "Sandeep", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary16", nullable=false, updatable=false)
    private UUID uuid;
}
